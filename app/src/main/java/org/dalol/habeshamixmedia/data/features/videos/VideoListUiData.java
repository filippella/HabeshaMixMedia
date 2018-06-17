package org.dalol.habeshamixmedia.data.features.videos;

import android.support.annotation.NonNull;

import org.dalol.habeshamixmedia.data.api.videos.YoutubeApi;
import org.dalol.habeshamixmedia.data.base.UiData;
import org.dalol.habeshamixmedia.data.model.response.channel.ChannelResponse;
import org.dalol.habeshamixmedia.data.model.response.videos.VideoListResponse;
import org.dalol.habeshamixmedia.data.model.vo.YoutubeVideoVO;
import org.dalol.habeshamixmedia.data.transformers.videos.VideoListTransformer;

import java.util.List;

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

    public Single<List<YoutubeVideoVO>> getVideoList(String channelId) {
        return mYoutubeApi.getVideoList(channelId, YoutubeApi.YOUTUBE_API_KEY)
                .map(new Function<VideoListResponse, List<YoutubeVideoVO>>() {
                    @Override
                    public List<YoutubeVideoVO> apply(VideoListResponse videoListResponse) throws Exception {
                        return new VideoListTransformer().transform(videoListResponse);
                    }
                });
    }
}
