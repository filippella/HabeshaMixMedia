package org.dalol.habeshamixmedia.data.features.artists;

import android.support.annotation.NonNull;

import org.dalol.habeshamixmedia.data.api.artists.ArtistListApi;
import org.dalol.habeshamixmedia.data.base.UiData;
import org.dalol.habeshamixmedia.data.model.Artists;
import org.dalol.habeshamixmedia.data.model.ArtistsResponse;
import org.dalol.habeshamixmedia.data.model.Data;
import org.dalol.habeshamixmedia.data.model.Genre;
import org.dalol.habeshamixmedia.data.model.vo.Artist;
import org.dalol.habeshamixmedia.data.model.vo.GenreArtists;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 15:54.
 */
public class ArtistListUiData extends UiData {

    private final ArtistListApi mGenreMusicApi;

    public ArtistListUiData(@NonNull ArtistListApi genreMusicApi) {
        mGenreMusicApi = genreMusicApi;
    }

    public Single<GenreArtists> getArtists(String genreName, int pageNumber) {
        return mGenreMusicApi.getArtists(genreName, pageNumber)
                .map(new Function<ArtistsResponse, GenreArtists>() {
                    @Override
                    public GenreArtists apply(ArtistsResponse artistsResponse) throws Exception {
                        List<Artist> artistList = new ArrayList<>();

                        Artists artists = artistsResponse.getArtists();
                        Genre genre = artistsResponse.getGenre();


                        Data[] data = artists.getData();
                        for (Data d : data) {
                            Artist artist = new Artist();
                            artist.setArtistId(Integer.valueOf(d.getId()));
                            artist.setArtistName(d.getName());
                            artist.setImageUrl(d.getImage_small());
                            artistList.add(artist);
                        }

                        Integer currentPage = Integer.valueOf(artists.getCurrent_page());
                        boolean canLoadMore = Integer.valueOf(artists.getLast_page()) - currentPage > 0;

                        return new GenreArtists(genre.getName(), artistList, canLoadMore, currentPage);
                    }
                });
    }
}
