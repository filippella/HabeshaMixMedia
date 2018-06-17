package org.dalol.habeshamixmedia.presenter;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import org.dalol.habeshamixmedia.common.rx.DefaultRXScheduler;
import org.dalol.habeshamixmedia.common.rx.RXScheduler;
import org.dalol.habeshamixmedia.data.base.UiData;
import org.dalol.habeshamixmedia.presenter.base.UiView;

import java.lang.ref.WeakReference;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 15:27.
 */

public abstract class BasePresenter<UV extends UiView, UD extends UiData> {

    @NonNull private final WeakReference<UV> mUiView;
    @NonNull private final WeakReference<UD> mUiData;

    private RXScheduler mRxScheduler = new DefaultRXScheduler();

    private CompositeDisposable mCompositeDisposable;

    protected BasePresenter(@NonNull UV uiView, @NonNull UD uiData) {
        mUiView = new WeakReference<>(uiView);
        mUiData = new WeakReference<>(uiData);
    }

    public void setRxScheduler(@NonNull RXScheduler rxScheduler) {
        mRxScheduler = rxScheduler;
    }

    protected UV getUiView() {
        return mUiView.get();
    }

    protected UD getUiData() {
        return mUiData.get();
    }

    public void onUiViewAttached() {
        mCompositeDisposable = new CompositeDisposable();
        UD uiData = getUiData();
        if (uiData != null) {
            uiData.initialize();
        }
    }

    protected <O> void subscribe(Observable<O> observable) {
        Disposable disposable = observable
                .subscribeOn(mRxScheduler.io())
                .observeOn(mRxScheduler.main())
                .unsubscribeOn(mRxScheduler.computation())
                .cache()
                .subscribe();
        getCompositeDisposable().add(disposable);
    }

    protected <O> void subscribe(Observable<O> observable, Observer<O> observer) {
        observable
                .subscribeOn(mRxScheduler.io())
                .observeOn(mRxScheduler.main())
                .unsubscribeOn(mRxScheduler.computation())
                .cache()
                .subscribe(observer);
        //Make sure to manually unsubscribe observer
    }

    protected <O> void subscribeWith(Observable<O> observable, DisposableObserver<O> disposableObserver) {
        //maybe.retryWhen(new AuthenticationTokenRefreshService());
        getCompositeDisposable().add(observable
                //.retryWhen(new RefreshAuthToken())
                .subscribeOn(mRxScheduler.io())
                .observeOn(mRxScheduler.main())
                .unsubscribeOn(mRxScheduler.computation())
                .cache()
                .subscribeWith(disposableObserver));
    }

    protected void subscribeWith(Completable completable, DisposableCompletableObserver completableObserver) {
        getCompositeDisposable().add(completable
                .subscribeOn(mRxScheduler.io())
                .observeOn(mRxScheduler.main())
                .unsubscribeOn(mRxScheduler.computation())
                .cache()
                .subscribeWith(completableObserver));
    }

    protected <D> void subscribeWith(Single<D> single, DisposableSingleObserver<D> singleObserver) {
        getCompositeDisposable().add(single
                .subscribeOn(mRxScheduler.io())
                .observeOn(mRxScheduler.main())
                .unsubscribeOn(mRxScheduler.computation())
                .cache()
                .subscribeWith(singleObserver));
    }

    protected <D> void subscribeWith(Maybe<D> maybe, DisposableMaybeObserver<D> maybeObserver) {
        getCompositeDisposable().add(maybe
                .subscribeOn(mRxScheduler.io())
                .observeOn(mRxScheduler.main())
                .unsubscribeOn(mRxScheduler.computation())
                .cache()
                .subscribeWith(maybeObserver));
    }

    private CompositeDisposable getCompositeDisposable() {
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        return mCompositeDisposable;
    }

    protected void clearSubscriptions() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    @CallSuper
    public void onUiViewDetached() {
        clearSubscriptions();
        UV uiView = getUiView();
        if (uiView != null) {
            mUiView.clear();
        }
        UD uiData = getUiData();
        if (uiData != null) {
            uiData.cleanup();
            mUiData.clear();
        }
        mRxScheduler = null;
    }
}
