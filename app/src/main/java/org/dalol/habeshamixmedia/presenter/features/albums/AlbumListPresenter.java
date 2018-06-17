package org.dalol.habeshamixmedia.presenter.features.albums;

import android.support.annotation.NonNull;

import org.dalol.habeshamixmedia.data.features.albums.AlbumListUiData;
import org.dalol.habeshamixmedia.data.model.vo.Album;
import org.dalol.habeshamixmedia.presenter.BasePresenter;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 21:17.
 */
public class AlbumListPresenter extends BasePresenter<AlbumListUiView, AlbumListUiData> {

    public AlbumListPresenter(@NonNull AlbumListUiView uiView, @NonNull AlbumListUiData uiData) {
        super(uiView, uiData);
    }

    public void getAlbums(int id) {
        subscribeWith(getUiData().getArtistAlbums(id), new DisposableSingleObserver<List<Album>>() {
            @Override
            public void onSuccess(List<Album> albums) {
                getUiView().onLoadAlbums(albums);
            }

            @Override
            public void onError(Throwable e) {
                getUiView().onShowToast(e.getMessage());
            }
        });
    }
}
