package org.dalol.habeshamixmedia.player;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 29/04/2018 at 18:06.
 */
public enum HMPlayer {

    INSTANCE;

    private final MusicPlayer mMusicPlayer = new MusicPlayer();

    public MusicPlayer getMusicPlayer() {
        return mMusicPlayer;
    }
}
