package org.dalol.habeshamixmedia.data.api.artists;

import org.dalol.habeshamixmedia.data.model.ArtistAlbumsResponse;
import org.dalol.habeshamixmedia.data.model.request.ArtistRequest;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 20:09.
 */
public interface ArtistAlbumApi {

    @Headers({
            "Pragma: no-cache",
            "Origin: http://www.huluzefen.com",
            "X-XSRF-TOKEN: eyJpdiI6IlBYRFk0Y3h4d3RxOEJwSjQ0YUE1Z1E9PSIsInZhbHVlIjoiVk1JK2FTRVpwUXV0Tmd4UVwvbFJvUDhXTFJpejE3b1kzVmJGN0p6Ym5RZ1d2ak5WWisxNHR6UHlybmxxN2RUUXZ6QjRiUW15ZU01amd1ell5QXdDRmlBPT0iLCJtYWMiOiI1ODY3NjJiZDZiMGFiZDE5NTVkMmU2MGNmZTc3YTM0ZjdjZmEzYTUxM2Q3YjQzM2VlMmNlY2NjY2NmM2Y3N2FhIn0=",
            "Accept-Encoding: gzip, deflate",
            "Accept-Language: en-US,en;q=0.9",
            "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.79 Safari/537.36",
            "Content-Type: application/json;charset=UTF-8",
            "Accept: application/json, text/plain, */*",
            "Cache-Control: no-cache",
            "Referer: http://www.huluzefen.com/artist/135/Kiros+Alemayehu",
            "Cookie: _ga=GA1.2.275801953.1524414445; cookieconsent_status=dismiss; _gid=GA1.2.638654012.1528553366; XSRF-TOKEN=eyJpdiI6IlBYRFk0Y3h4d3RxOEJwSjQ0YUE1Z1E9PSIsInZhbHVlIjoiVk1JK2FTRVpwUXV0Tmd4UVwvbFJvUDhXTFJpejE3b1kzVmJGN0p6Ym5RZ1d2ak5WWisxNHR6UHlybmxxN2RUUXZ6QjRiUW15ZU01amd1ell5QXdDRmlBPT0iLCJtYWMiOiI1ODY3NjJiZDZiMGFiZDE5NTVkMmU2MGNmZTc3YTM0ZjdjZmEzYTUxM2Q3YjQzM2VlMmNlY2NjY2NmM2Y3N2FhIn0%3D; laravel_session=eyJpdiI6Im1tSUE1QnpFZGxQZUw1SVFpVHdQcWc9PSIsInZhbHVlIjoiM244M0wyWUlCWTZqUTM4THgwN0RRQXRINkFnR2xIQ0VLV1R5dnFPSnAwdVBsZm1HSGU0VDRXMGlTaklwc0JwYjJMNG5hRW4yRDN1SFdSeDJOSE9ENEE9PSIsIm1hYyI6ImNiMzBhMTI4ODZlNDZlNDA5NDhmYTQ1MjFmY2RkZDM4ZWQwNDVkZWQ3N2E5MWY1OGRiMDVlNDZmOTk4ODc2N2UifQ%3D%3D; __atuvc=1%7C19%2C0%7C20%2C0%7C21%2C0%7C22%2C12%7C23; __atuvs=5b1c110016bba439009",
            "Connection: keep-alive"
    })
    @POST("/get-artist")
    Single<ArtistAlbumsResponse> getAlbums(@Body ArtistRequest request);
}
