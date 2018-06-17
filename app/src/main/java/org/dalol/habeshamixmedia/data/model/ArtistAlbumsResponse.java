package org.dalol.habeshamixmedia.data.model;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 20:03.
 */
public class ArtistAlbumsResponse {

    private String spotify_popularity;
    private Genres[] genres;
    private String wiki_image_large;
    private String image_small;
    private String spotify_followers;
    private String fully_scraped;
    private String id;
    private String wiki_image_small;
    private String updated_at;
    private String bio;
    private String name;
    private Albums[] albums;
    private String image_large;
    private String[] similar;

    public String getSpotify_popularity() {
        return spotify_popularity;
    }

    public Genres[] getGenres() {
        return genres;
    }

    public String getWiki_image_large() {
        return wiki_image_large;
    }

    public String getImage_small() {
        return image_small;
    }

    public String getSpotify_followers() {
        return spotify_followers;
    }

    public String getFully_scraped() {
        return fully_scraped;
    }

    public String getId() {
        return id;
    }

    public String getWiki_image_small() {
        return wiki_image_small;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getBio() {
        return bio;
    }

    public String getName() {
        return name;
    }

    public Albums[] getAlbums() {
        return albums;
    }

    public String getImage_large() {
        return image_large;
    }

    public String[] getSimilar() {
        return similar;
    }
}
