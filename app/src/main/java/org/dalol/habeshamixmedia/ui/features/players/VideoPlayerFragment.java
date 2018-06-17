package org.dalol.habeshamixmedia.ui.features.players;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import org.dalol.habeshamixmedia.R;
import org.dalol.habeshamixmedia.ui.base.BaseChildFragment;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 17/06/2018 at 01:52.
 */
public class VideoPlayerFragment extends BaseChildFragment {

    public static final String ARGS_VIDEO_URL = "org.dalol.habeshamultimedia.ui.features.players.args.VIDEO_URL";

    private VideoView mVideoView;

    public static VideoPlayerFragment newInstance(String videoURL) {

        Bundle args = new Bundle();
        args.putString(ARGS_VIDEO_URL, videoURL);
        VideoPlayerFragment fragment = new VideoPlayerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_video_player;
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);

        mVideoView = view.findViewById(R.id.videoPlayer);

        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(getActivity());
            mediacontroller.setAnchorView(mVideoView);

            String videoURL = getArguments().getString(ARGS_VIDEO_URL, "http://www.dalol.org/video_1.mp4");

            String videoURL_1 = "http://www.dalol.org/video_1.mp4";
            String videoURL_2 = "http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4";
            // Get the URL from String VideoURL
            Uri video = Uri.parse(videoURL);
            mVideoView.setMediaController(mediacontroller);
            mVideoView.setVideoURI(video);
            //showDialog("Playing video...");
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
            //hideDialog();
        }

        mVideoView.requestFocus();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
//                hideDialog();
                mVideoView.start();
            }
        });
    }
}
