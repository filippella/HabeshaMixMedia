package org.dalol.habeshamixmedia.data.api.videos;

import org.dalol.habeshamixmedia.data.model.response.channel.ChannelResponse;
import org.dalol.habeshamixmedia.data.model.response.videos.VideoListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 17/06/2018 at 00:11.
 */
public interface YoutubeApi {

    String YOUTUBE_API_KEY = "AIzaSyD-xeBAxM3ibxESPfMyvysOoHAelUtGbVc";

    @GET("/youtube/v3/channels?part=snippet")
    Single<ChannelResponse> getChannel(@Query("forUsername") String channelName, @Query("key") String apiKey);


    @GET("/youtube/v3/search?order=date&part=snippet&maxResults=50")
    Single<VideoListResponse> getVideoList(@Query("channelId") String channelId, @Query("key") String apiKey);

    @GET("/youtube/v3/search?order=date&part=snippet&maxResults=25")
    Single<VideoListResponse> getNextVideoList(@Query("channelId") String channelId, @Query("pageInfo") String pageInfo, @Query("key") String apiKey);
}
