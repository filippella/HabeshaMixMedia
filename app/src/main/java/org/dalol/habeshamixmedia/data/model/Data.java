package org.dalol.habeshamixmedia.data.model;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 16:13.
 */
public class Data {

    private String id;
    private Pivot pivot;
    private String wiki_image_small;
    private String updated_at;
    private int spotify_popularity;
    private String bio;
    private String name;
    private String wiki_image_large;
    private String image_small;
    private String spotify_followers;
    private String fully_scraped;
    private String image_large;

    public String getId() {
        return id;
    }

    public Pivot getPivot() {
        return pivot;
    }

    public String getWiki_image_small() {
        return wiki_image_small;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public int getSpotify_popularity() {
        return spotify_popularity;
    }

    public String getBio() {
        return bio;
    }

    public String getName() {
        return name;
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

    public String getImage_large() {
        return image_large;
    }
}
