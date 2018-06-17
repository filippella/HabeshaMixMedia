package org.dalol.habeshamixmedia.data.model;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 20:04.
 */
public class Albums {

    private String id;
    private String spotify_popularity;
    private String name;
    private String release_date;
    private String image;
    private Tracks[] tracks;
    private String artist_id;
    private String temp_id;
    private String fully_scraped;

    public String getId() {
        return id;
    }

    public String getSpotify_popularity() {
        return spotify_popularity;
    }

    public String getName() {
        return name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getImage() {
        return image;
    }

    public Tracks[] getTracks() {
        return tracks;
    }

    public String getArtist_id() {
        return artist_id;
    }

    public String getTemp_id() {
        return temp_id;
    }

    public String getFully_scraped() {
        return fully_scraped;
    }
}
