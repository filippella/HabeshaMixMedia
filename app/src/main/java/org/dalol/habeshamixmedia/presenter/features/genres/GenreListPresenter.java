package org.dalol.habeshamixmedia.presenter.features.genres;

import android.support.annotation.NonNull;

import org.dalol.habeshamixmedia.data.model.vo.Genre;
import org.dalol.habeshamixmedia.data.features.genres.GenreListUiData;
import org.dalol.habeshamixmedia.data.model.vo.Genre;
import org.dalol.habeshamixmedia.presenter.BasePresenter;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 15:29.
 */
public class GenreListPresenter extends BasePresenter<GenreListUiView, GenreListUiData> {

    public GenreListPresenter(@NonNull GenreListUiView uiView, @NonNull GenreListUiData uiData) {
        super(uiView, uiData);
    }

    public void getGenres() {
        subscribeWith(getUiData().getGenres(), new DisposableSingleObserver<List<Genre>>() {
            @Override
            public void onSuccess(List<Genre> genres) {
                getUiView().onLoadGenres(genres);
            }

            @Override
            public void onError(Throwable e) {
                getUiView().onShowToast(e.getMessage());
            }
        });
    }
}
