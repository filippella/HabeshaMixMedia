package org.dalol.habeshamixmedia.presenter.features.players;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import org.dalol.habeshamixmedia.data.features.players.YoutubeVideoPlayerUiData;
import org.dalol.habeshamixmedia.data.model.vo.VideoInfoVO;
import org.dalol.habeshamixmedia.presenter.BasePresenter;

import java.util.Map;

import io.reactivex.Single;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 17/06/2018 at 11:56.
 */
public class YoutubeVideoPlayerPresenter extends BasePresenter<YoutubeVideoPlayerUiView, YoutubeVideoPlayerUiData> {

    public YoutubeVideoPlayerPresenter(@NonNull YoutubeVideoPlayerUiView uiView, @NonNull YoutubeVideoPlayerUiData uiData) {
        super(uiView, uiData);
    }

    public void getVideoInfo(String videoId) {
        Single<Map<String, String>> single = getUiData().getVideoInfo(videoId);
        subscribeWith(single, new DisposableSingleObserver<Map<String, String>>() {
            @Override
            public void onSuccess(Map<String, String> stringStringMap) {
                String encodedFmtStreamMap = stringStringMap.get("url_encoded_fmt_stream_map");
                VideoInfoVO[] videoInfoVOS = new Gson().fromJson(encodedFmtStreamMap, VideoInfoVO[].class);
                getUiView().onLoadVideo(stringStringMap.get("title"), videoInfoVOS[0]);
            }

            @Override
            public void onError(Throwable e) {
                getUiView().onShowToast(e.getMessage());
            }
        });
    }

    public long downloadVideo(DownloadManager downloadManager, String videoTitle, VideoInfoVO infoVO) {

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(infoVO.getUrl()));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setTitle(videoTitle);
        request.setDescription("Downloading Video");
        request.setVisibleInDownloadsUi(true);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/HabeshaMixMedia/" + videoTitle + ".mp4");

        return downloadManager.enqueue(request);
    }
}
