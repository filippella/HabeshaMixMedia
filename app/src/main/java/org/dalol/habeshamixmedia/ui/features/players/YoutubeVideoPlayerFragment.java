package org.dalol.habeshamixmedia.ui.features.players;

import android.Manifest;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import org.dalol.habeshamixmedia.R;
import org.dalol.habeshamixmedia.data.api.videos.YoutubeVideoApi;
import org.dalol.habeshamixmedia.data.features.players.YoutubeVideoPlayerUiData;
import org.dalol.habeshamixmedia.data.model.vo.VideoInfoVO;
import org.dalol.habeshamixmedia.presenter.features.players.YoutubeVideoPlayerPresenter;
import org.dalol.habeshamixmedia.presenter.features.players.YoutubeVideoPlayerUiView;
import org.dalol.habeshamixmedia.ui.ViewHolders.BannerAdViewHolder;
import org.dalol.habeshamixmedia.ui.ViewHolders.NativeAppInstallAdViewHolder;
import org.dalol.habeshamixmedia.ui.ViewHolders.NativeContentAdViewHolder;
import org.dalol.habeshamixmedia.ui.base.BaseChildFragment;
import org.dalol.habeshamixmedia.ui.features.MainActivity;
import org.dalol.habeshamixmedia.ui.helpers.RequestPermissionHelper;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 17/06/2018 at 01:52.
 */
public class YoutubeVideoPlayerFragment extends BaseChildFragment<YoutubeVideoPlayerPresenter> implements YoutubeVideoPlayerUiView {

    public static final String ARGS_VIDEO_ID = "org.dalol.habeshamultimedia.ui.features.players.args.VIDEO_ID";

    private VideoView mVideoView;
    private ProgressBar mProgressLoader;

    private BannerAdViewHolder mBannerAdTopView1, mBannerAdTopView2;
    private BannerAdViewHolder mBannerAdBottomView;

    private DownloadManager mDownloadManager;
    private View mDownloadButton;
    private VideoInfoVO mVideoInfo;
    private long mRefId;
    private String mVideoTitle;
    private Context mContext;

    private RequestPermissionHelper mRequestPermissionHelper;

    public static YoutubeVideoPlayerFragment newInstance(String videoId) {

        Bundle args = new Bundle();
        args.putString(ARGS_VIDEO_ID, videoId);
        YoutubeVideoPlayerFragment fragment = new YoutubeVideoPlayerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRequestPermissionHelper = new RequestPermissionHelper();

        mDownloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        mContext = getContext();
        mContext.registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    @Override
    public void onDestroy() {
        mContext.unregisterReceiver(onComplete);
        super.onDestroy();
    }

    @Override
    protected YoutubeVideoPlayerPresenter onCreatePresenter() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return new YoutubeVideoPlayerPresenter(this, new YoutubeVideoPlayerUiData(retrofit.create(YoutubeVideoApi.class)));
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_video_player;
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);

        mVideoView = view.findViewById(R.id.videoPlayer);
        mDownloadButton = view.findViewById(R.id.buttonDownloadVideo);
        mDownloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClicked();
            }
        });
        View adTop1 = view.findViewById(R.id.bannerAdTop1);
        View adTop2 = view.findViewById(R.id.bannerAdTop2);
        View adBottom = view.findViewById(R.id.bannerAdBottom);

        mProgressLoader = view.findViewById(R.id.progressLoader);
        MediaController mediacontroller = new MediaController(getActivity());
        mediacontroller.setAnchorView(mVideoView);
        mVideoView.setMediaController(mediacontroller);

        String videoId = getArguments().getString(ARGS_VIDEO_ID);
        YoutubeVideoPlayerPresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.getVideoInfo(videoId);
        }

        mBannerAdTopView1 = new BannerAdViewHolder(adTop1);
        mBannerAdTopView2 = new BannerAdViewHolder(adTop2);
        mBannerAdBottomView = new BannerAdViewHolder(adBottom);
    }

    @Override
    public void onResume() {
        super.onResume();
        mBannerAdTopView1.loadAd(BannerAdViewHolder.MAX_RETRY);
        mBannerAdTopView2.loadAd(BannerAdViewHolder.MAX_RETRY);
        mBannerAdBottomView.loadAd(BannerAdViewHolder.MAX_RETRY);
    }

    private void handleButtonClicked() {
        mRequestPermissionHelper.requestPermission(getActivity(), new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, 123, new RequestPermissionHelper.RequestPermissionListener() {
            @Override
            public void onSuccess() {
                YoutubeVideoPlayerPresenter presenter = getPresenter();
                if (presenter != null) {
                    mRefId = presenter.downloadVideo(mDownloadManager, mVideoTitle, mVideoInfo);
                }
            }

            @Override
            public void onFailed() {
                onShowToast("Sorry, request permission failed");
            }

            @Override
            public void onRequestPermission(String[] permissions, int requestCode) {
                requestPermissions(permissions, requestCode);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mRequestPermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void playVideo(String videoURL) {
        try {
            // Start the MediaController

            String videoURL_1 = "http://www.dalol.org/video_1.mp4";
            String videoURL_2 = "http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4";
            // Get the URL from String VideoURL
            Uri video = Uri.parse(videoURL);
            mVideoView.setVideoURI(video);

            //showProgress("Playing video...", true);

            mVideoView.requestFocus();
            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                // Close the progress bar and play the video
                public void onPrepared(MediaPlayer mp) {
                    //hideProgress();
                    mProgressLoader.setVisibility(View.GONE);
                    mVideoView.start();
                }
            });
            mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    mProgressLoader.setVisibility(View.GONE);
                    return false;
                }
            });

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
            mProgressLoader.setVisibility(View.GONE);
            //hideProgress();
        }
    }

    @Override
    public void onLoadVideo(String videoTitle, VideoInfoVO videoInfoVO) {
        mVideoTitle = videoTitle;
        mVideoInfo = videoInfoVO;
        String videoURL = videoInfoVO.getUrl();
        System.out.println(videoURL);
        setTitle(videoTitle);
        playVideo(videoURL);
        mDownloadButton.setVisibility(View.VISIBLE);
    }

    private final BroadcastReceiver onComplete = new BroadcastReceiver() {

        public void onReceive(Context ctxt, Intent intent) {

            // get the refid from the download manager
            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

            if (referenceId == mRefId) {
                onShowToast("All Download completed");
//                Log.e("INSIDE", "" + referenceId);
//                FragmentActivity activity = getActivity();
//                NotificationCompat.Builder mBuilder =
//                        new NotificationCompat.Builder(activity)
//                                .setSmallIcon(R.mipmap.ic_launcher)
//                                .setContentTitle("Video downloaded ")
//                                .setContentText("All Download completed");
//
//
//                NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
//                notificationManager.notify(455, mBuilder.build());
            }
        }
    };
}
