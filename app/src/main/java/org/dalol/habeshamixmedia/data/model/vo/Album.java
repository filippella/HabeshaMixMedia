package org.dalol.habeshamixmedia.data.model.vo;

import java.io.Serializable;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 20:53.
 */
public class Album implements Serializable {

    private int id;
    private String name;
    private String release_date;
    private String image;

    private int artist_id;
    private int spotify_popularity;
    private int fully_scraped;

    private String temp_id;
    private final TracksVO mTracksVO;

    public Album(int id, String name, String image, int artist_id, TracksVO tracks) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.artist_id = artist_id;
        this.mTracksVO = tracks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public TracksVO getTracksVO() {
        return mTracksVO;
    }
}
