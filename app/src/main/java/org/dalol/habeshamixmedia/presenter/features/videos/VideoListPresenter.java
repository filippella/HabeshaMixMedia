package org.dalol.habeshamixmedia.presenter.features.videos;

import android.support.annotation.NonNull;

import org.dalol.habeshamixmedia.data.features.videos.VideoListUiData;
import org.dalol.habeshamixmedia.data.model.response.channel.ChannelResponse;
import org.dalol.habeshamixmedia.data.model.vo.YoutubeVideoVO;
import org.dalol.habeshamixmedia.presenter.BasePresenter;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 16/06/2018 at 23:53.
 */
public class VideoListPresenter extends BasePresenter<VideoListUiView, VideoListUiData> {

    public VideoListPresenter(@NonNull VideoListUiView uiView, @NonNull VideoListUiData uiData) {
        super(uiView, uiData);
    }

    public void getChannelVideos(String channelIc) {
        subscribeWith(getUiData().getChannel(channelIc), new DisposableSingleObserver<ChannelResponse>() {
            @Override
            public void onSuccess(ChannelResponse channelResponse) {
                System.out.println(channelResponse);
                getVideoList(channelResponse.getItems()[0].getId());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public void getVideoList(String channelId) {
        subscribeWith(getUiData().getVideoList(channelId), new DisposableSingleObserver<List<YoutubeVideoVO>>() {
            @Override
            public void onSuccess(List<YoutubeVideoVO> videoVOS) {
                getUiView().onLoadVideoList(videoVOS);
            }

            @Override
            public void onError(Throwable e) {
                getUiView().onShowToast(e.getMessage());
            }
        });
    }
}
