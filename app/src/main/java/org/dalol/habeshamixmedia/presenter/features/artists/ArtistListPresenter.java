package org.dalol.habeshamixmedia.presenter.features.artists;

import android.support.annotation.NonNull;

import org.dalol.habeshamixmedia.data.features.artists.ArtistListUiData;
import org.dalol.habeshamixmedia.data.model.vo.GenreArtists;
import org.dalol.habeshamixmedia.presenter.BasePresenter;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 15:52.
 */
public class ArtistListPresenter extends BasePresenter<ArtistListUiView, ArtistListUiData> {

    public ArtistListPresenter(@NonNull ArtistListUiView uiView, @NonNull ArtistListUiData uiData) {
        super(uiView, uiData);
    }

    public void getArtists(String genreName, int pageNumber) {
        subscribeWith(getUiData().getArtists(genreName, pageNumber), new DisposableSingleObserver<GenreArtists>() {
            @Override
            public void onSuccess(GenreArtists genreArtists) {
                getUiView().onLoadArtists(genreArtists.getArtists(), genreArtists.isAbleToLoadMore());
            }

            @Override
            public void onError(Throwable e) {
                getUiView().onShowToast(e.getMessage());
            }
        });
    }
}
