package org.dalol.habeshamixmedia.data.model.vo;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 18:06.
 */
public class Genre {

    private final String genreName;
    private final String genreAvatar;

    public Genre(String genreName, String genreAvatar) {
        this.genreName = genreName;
        this.genreAvatar = genreAvatar;
    }

    public String getGenreName() {
        return genreName;
    }

    public String getGenreAvatar() {
        return genreAvatar;
    }
}
