package org.dalol.habeshamixmedia.data.api.artists;

import org.dalol.habeshamixmedia.data.model.ArtistsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 16:07.
 */
public interface ArtistListApi {

    @GET("/genres/{genre}/paginate-artists")
    Single<ArtistsResponse> getArtists(@Path("genre") String genreName, @Query("page") int pageNumber);
}
