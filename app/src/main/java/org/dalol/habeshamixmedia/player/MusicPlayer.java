package org.dalol.habeshamixmedia.player;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;

import org.dalol.habeshamixmedia.data.callback.OnMusicPlayerStateListener;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 29/04/2018 at 18:07.
 */
public class MusicPlayer {

    private static final int MSG_WHAT_PLAY = 0;
    private static final int MSG_WHAT_PAUSE = 1;
    private static final int MSG_WHAT_STOP = 2;

    private final List<OnMusicPlayerStateListener> mPlayerStateListeners = new LinkedList<>();

    private boolean mPaused, mCompleted;

    private MediaPlayer mediaPlayer;

    public void initMediaPlayer() {
        if (mediaPlayer != null) {
            return;
        }
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setLooping(false);

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                int millis = mp.getCurrentPosition();
                int min = (millis / 1000) / 60;
                int sec = 0;
                if ((sec = millis / 1000) >= 60) {
                    sec -= 60;
                }

                for (OnMusicPlayerStateListener stateListener : mPlayerStateListeners) {
                    stateListener.onBufferingUpdate(percent);
                }

                String time_x = min + ":" + sec;
                //tv_time.setText(time_x + "  " + percent + "%");
            }
        });
        //mediaPlayer.setScreenOnWhilePlaying(true);
        mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
        mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mp) {
                for (OnMusicPlayerStateListener stateListener : mPlayerStateListeners) {
                    //stateListener.onSeekChanged(mp.getCurrentPosition());
                }
            }
        });
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                startPlaying(mp);
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mCompleted = true;
                for (OnMusicPlayerStateListener stateListener : mPlayerStateListeners) {
                    stateListener.onComplete();
                }
            }
        });
    }

    private void startPlaying(MediaPlayer mp) {
        mp.start();
        mPaused = false;
        mCompleted = false;

        int duration = mp.getDuration();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));

        for (OnMusicPlayerStateListener stateListener : mPlayerStateListeners) {
            stateListener.onPlay(duration / 1000, minutes + ":" + seconds);
        }
        handler.postDelayed(mProgressWorker, 0);
    }

    public void addOnMusicPlayerStateListener(OnMusicPlayerStateListener stateListener) {
        mPlayerStateListeners.add(stateListener);
    }

    public void removeOnMusicPlayerStateListener(OnMusicPlayerStateListener stateListener) {
        mPlayerStateListeners.remove(stateListener);
    }

    public boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

    public boolean isPaused() {
        return mPaused;
    }

    public void resumeMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public void playMusic(String url) {

        //long start = System.currentTimeMillis();
        try {
            //long end = System.currentTimeMillis();
            //Log.v("url", "" + (end - start) + "ms");
            if (mediaPlayer != null) {

                if (mPaused) {
                    startPlaying(mediaPlayer);

                } else {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepareAsync();
                }


                //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
//                    @Override
//                    public void onBufferingUpdate(MediaPlayer mp,
//                                                  int percent) {
//                        int millis = mp.getCurrentPosition();
//                        int min = (millis / 1000) / 60;
//                        int sec = 0;
//                        if ((sec = millis / 1000) >= 60) {
//                            sec -= 60;
//                        }
//                        String time_x = min + ":" + sec;
//                        //tv_time.setText(time_x + "  " + percent + "%");
//                    }
//                });
//                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//
//                    }
//                });
//                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                    @Override
//                    public void onPrepared(MediaPlayer mp) {
//                        mp.start();
//                    }
//                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void seekPayer(int progress) {
        if (mediaPlayer != null) {
            int playPositionInMillisecconds = (mediaPlayer.getDuration() / 100) * progress;
            mediaPlayer.seekTo(playPositionInMillisecconds);
//            mediaPlayer.seekTo(progress);
        }
        if (mediaPlayer.isPlaying()) {


//            int playPositionInMillisecconds = (mediaFileLengthInMilliseconds / 100) * progress;
//            mediaPlayer.seekTo(playPositionInMillisecconds);
        }
    }

    public void stopPlaying() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            //mediaPlayer.release();
            mediaPlayer.reset();
            for (OnMusicPlayerStateListener stateListener : mPlayerStateListeners) {
                stateListener.onStop();
            }
        }
    }

    public void pausePlaying() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mPaused = true;
            mediaPlayer.pause();
            for (OnMusicPlayerStateListener stateListener : mPlayerStateListeners) {
                stateListener.onPause();
            }
        }
    }

    public void cleanup() {
        stopPlaying();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = null;
    }


    private final Runnable mProgressWorker = new Runnable() {
        @Override
        public void run() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                int currentPosition = mediaPlayer.getCurrentPosition();
                System.out.println("FILIPPOCCC -> " + currentPosition);

                long minutes = TimeUnit.MILLISECONDS.toMinutes(currentPosition);
                long seconds = TimeUnit.MILLISECONDS.toSeconds(currentPosition) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(currentPosition));

                //String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) currentPosition), )

                for (OnMusicPlayerStateListener stateListener : mPlayerStateListeners) {
                    stateListener.onProgress(currentPosition / 1000, minutes + ":" + seconds);
                }
                handler.postDelayed(this, 1000);
            }
        }
    };
    private final Handler handler = new Handler();
}
