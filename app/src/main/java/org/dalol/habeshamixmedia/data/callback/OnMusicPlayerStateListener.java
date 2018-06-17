package org.dalol.habeshamixmedia.data.callback;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 29/04/2018 at 18:49.
 */
public interface OnMusicPlayerStateListener {

    void onSeekChanged(int currentPosition);

    void onBufferingUpdate(int percent);

    void onComplete();

    void onPlay(int maxDuration, String totalDuration);

    void onPause();

    void onStop();

    void onProgress(int currentProgress, String elapsed);
}
