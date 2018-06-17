package org.dalol.habeshamixmedia.data.features.albums;

import android.support.annotation.NonNull;

import org.dalol.habeshamixmedia.data.api.artists.ArtistAlbumApi;
import org.dalol.habeshamixmedia.data.base.UiData;
import org.dalol.habeshamixmedia.data.model.Albums;
import org.dalol.habeshamixmedia.data.model.ArtistAlbumsResponse;
import org.dalol.habeshamixmedia.data.model.Tracks;
import org.dalol.habeshamixmedia.data.model.request.ArtistRequest;
import org.dalol.habeshamixmedia.data.model.vo.Album;
import org.dalol.habeshamixmedia.data.model.vo.TrackVO;
import org.dalol.habeshamixmedia.data.model.vo.TracksVO;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 21:15.
 */
public class AlbumListUiData extends UiData {

    private final ArtistAlbumApi mAlbumListApi;

    public AlbumListUiData(@NonNull ArtistAlbumApi genreMusicApi) {
        mAlbumListApi = genreMusicApi;
    }

    public Single<List<Album>> getArtistAlbums(int artistId) {
        return mAlbumListApi.getAlbums(new ArtistRequest(artistId))
                .map(new Function<ArtistAlbumsResponse, List<Album>>() {
                    @Override
                    public List<Album> apply(ArtistAlbumsResponse artistAlbumsResponse) throws Exception {
                        String artistName = artistAlbumsResponse.getName();
                        Albums[] albums = artistAlbumsResponse.getAlbums();
                        List<Album> albumList = new ArrayList<>();
                        for (Albums album : albums) {
                            Tracks[] tracks = album.getTracks();

                            String albumName = album.getName();
                            String albumImage = album.getImage();

                            List<TrackVO> trackVOS = new ArrayList<>();

                            for (Tracks track : tracks) {
                                trackVOS.add(new TrackVO(albumName, albumImage, track.getAlbum_name(), Long.valueOf(track.getDuration()), track.getYoutube_id()));
                            }

                            Integer albumId = Integer.valueOf(album.getId());
                            Integer artistId = Integer.valueOf(album.getArtist_id());

                            Album a = new Album(albumId, albumName, albumImage, artistId, new TracksVO(trackVOS));
                            albumList.add(a);
                        }
                        return albumList;
                    }
                });
    }
}
