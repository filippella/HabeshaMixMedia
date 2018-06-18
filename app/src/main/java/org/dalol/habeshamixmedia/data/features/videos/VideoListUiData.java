package org.dalol.habeshamixmedia.data.features.videos;

import android.support.annotation.NonNull;

import org.dalol.habeshamixmedia.data.api.videos.YoutubeApi;
import org.dalol.habeshamixmedia.data.base.UiData;
import org.dalol.habeshamixmedia.data.model.response.channel.ChannelResponse;
import org.dalol.habeshamixmedia.data.model.response.videos.VideoListResponse;
import org.dalol.habeshamixmedia.data.model.vo.VideosVO;
import org.dalol.habeshamixmedia.data.transformers.videos.VideoListTransformer;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 16/06/2018 at 23:54.
 */
public class VideoListUiData extends UiData {

    private final YoutubeApi mYoutubeApi;

    public VideoListUiData(@NonNull YoutubeApi youtubeApi) {
        mYoutubeApi = youtubeApi;
    }

    public Single<ChannelResponse> getChannel(String username) {
        return mYoutubeApi.getChannel(username, YoutubeApi.YOUTUBE_API_KEY);
    }

    public Single<VideosVO> getVideoList(String channelId) {
        return mYoutubeApi.getVideoList(channelId, YoutubeApi.YOUTUBE_API_KEY)
                .map(new Function<VideoListResponse, VideosVO>() {
                    @Override
                    public VideosVO apply(VideoListResponse videoListResponse) throws Exception {
                        return new VideoListTransformer().transform(videoListResponse);
                    }
                });
    }

    public Single<VideosVO> getNextVideoList(String channelId, String pageInfo) {
        return mYoutubeApi.getNextVideoList(channelId, pageInfo, YoutubeApi.YOUTUBE_API_KEY)
                .map(new Function<VideoListResponse, VideosVO>() {
                    @Override
                    public VideosVO apply(VideoListResponse videoListResponse) throws Exception {
                        return new VideoListTransformer().transform(videoListResponse);
                    }
                });
    }
}
