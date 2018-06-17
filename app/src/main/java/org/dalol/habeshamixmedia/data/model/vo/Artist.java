package org.dalol.habeshamixmedia.data.model.vo;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 16:36.
 */
public class Artist {

    private String artistName;
    private String imageUrl;
    private int artistId;

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
