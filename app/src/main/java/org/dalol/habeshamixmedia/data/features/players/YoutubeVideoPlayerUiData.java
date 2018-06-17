package org.dalol.habeshamixmedia.data.features.players;

import org.dalol.habeshamixmedia.data.api.videos.YoutubeVideoApi;
import org.dalol.habeshamixmedia.data.base.UiData;
import org.dalol.habeshamixmedia.data.transformers.videos.GzipTransformer;
import org.dalol.habeshamixmedia.data.transformers.videos.StringToMapTransformer;

import java.util.Map;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 17/06/2018 at 11:57.
 */
public class YoutubeVideoPlayerUiData extends UiData {

    private final YoutubeVideoApi mYoutubeVideoApi;

    public YoutubeVideoPlayerUiData(YoutubeVideoApi youtubeVideoApi) {
        mYoutubeVideoApi = youtubeVideoApi;
    }

    public Single<Map<String, String>> getVideoInfo(String videoId) {
        return mYoutubeVideoApi.getVideoInfo(videoId)
                .map(new Function<ResponseBody, String>() {
                    @Override
                    public String apply(ResponseBody body) throws Exception {
                        return new GzipTransformer().transform(body);
                    }
                })
                .map(new Function<String, Map<String, String>>() {
                    @Override
                    public Map<String, String> apply(String info) throws Exception {
                        return new StringToMapTransformer().transform(info);
                    }
                });
    }
}
