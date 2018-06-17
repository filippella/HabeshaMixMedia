package org.dalol.habeshamixmedia.ui.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.dalol.habeshamixmedia.presenter.BasePresenter;
import org.dalol.habeshamixmedia.data.base.UiData;
import org.dalol.habeshamixmedia.presenter.BasePresenter;
import org.dalol.habeshamixmedia.presenter.base.UiView;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 22/04/2018 at 21:00.
 */
public abstract class BaseFragment<P extends BasePresenter<? extends UiView, ? extends UiData>> extends Fragment {

    @Nullable private P mPresenter;
    private View mRootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getParentFragment() == null) {
            setRetainInstance(true);
        }
        mPresenter = onCreatePresenter();
        if (mPresenter != null) {
            mPresenter.onUiViewAttached();
        }
    }

    protected P onCreatePresenter() {
        return null;
    }

    @Nullable
    public P getPresenter() {
        return mPresenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
            container.clearDisappearingChildren();
        }

        System.out.println("Created " + getClass().getSimpleName());

       /// View view = getView();

        if (mRootView == null) {
            mRootView = inflater.inflate(getContentView(), container, false);
            bindView(mRootView);
        }
        //else {
//            ViewGroup parent = (ViewGroup) view.getParent();
//            if (parent != null) {
//                parent.removeView(view);
//                System.out.println("Already Created " + getClass().getSimpleName() + " has parent and removed");
//            }
       // }
        return mRootView;
    }

    protected void setTitle(String title) {
        ActionBar actionBar = ((BaseActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    @CallSuper
    protected void bindView(View view) {
    }

    protected void showProgress(String message, boolean cancelable) {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            activity.showDialog(message, cancelable);
        }
    }

    protected void hideProgress() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            activity.hideDialog();
        }
    }

    public void onShowToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        View view = getView();
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
            System.out.println("Already Created " + getClass().getSimpleName() + " has parent and removed");
        }
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onUiViewDetached();
        }
        super.onDestroy();
    }

    protected abstract int getContentView();
}
