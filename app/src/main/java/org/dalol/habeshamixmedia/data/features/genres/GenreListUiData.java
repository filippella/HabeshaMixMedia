package org.dalol.habeshamixmedia.data.features.genres;

import org.dalol.habeshamixmedia.data.base.UiData;
import org.dalol.habeshamixmedia.data.model.vo.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 16:00.
 */
public class GenreListUiData extends UiData {

    public Single<List<Genre>> getGenres() {
        return Single.fromCallable(new Callable<List<Genre>>() {
            @Override
            public List<Genre> call() throws Exception {
                List<Genre> genres = new ArrayList<>();

                genres.add(new Genre("Newest-Playlists", "http://www.huluzefen.com/assets/images/genres/Newest-Playlists.jpg"));
                genres.add(new Genre("Amharic", "http://www.huluzefen.com/assets/images/genres/Amharic.jpg"));
                genres.add(new Genre("Oromigna", "http://www.huluzefen.com/assets/images/genres/Oromigna.jpg"));
                genres.add(new Genre("Tigrigna", "http://www.huluzefen.com/assets/images/genres/Tigrigna.jpg"));
                genres.add(new Genre("Eritrean", "http://www.huluzefen.com/assets/images/genres/Eritrean.jpg"));
                genres.add(new Genre("Gonder", "http://www.huluzefen.com/assets/images/genres/Gonder.jpg"));
                genres.add(new Genre("Gojam", "http://www.huluzefen.com/assets/images/genres/Gojam.jpg"));
                genres.add(new Genre("Wollo", "http://www.huluzefen.com/assets/images/genres/Wollo.jpg"));
                genres.add(new Genre("Ethio-Reggae", "http://www.huluzefen.com/assets/images/genres/Ethio-Reggae.jpg"));
                genres.add(new Genre("Ethio-Traditional", "http://www.huluzefen.com/assets/images/genres/Ethio-Traditional.jpg"));
                genres.add(new Genre("Ethiopiques", "http://www.huluzefen.com/assets/images/genres/Ethiopiques.jpg"));
                genres.add(new Genre("Ethio-Jazz", "http://www.huluzefen.com/assets/images/genres/Ethio-Jazz.jpg"));
                genres.add(new Genre("Blues", "http://www.huluzefen.com/assets/images/genres/Blues.jpg"));
                genres.add(new Genre("Ethio-Folk", "http://www.huluzefen.com/assets/images/genres/Ethio-Folk.jpg"));
                genres.add(new Genre("Ethiopian-Somali", "http://www.huluzefen.com/assets/images/genres/Ethiopian-Somali.jpg"));
                genres.add(new Genre("Afar", "http://www.huluzefen.com/assets/images/genres/Afar.jpg"));
                genres.add(new Genre("Guragigna", "http://www.huluzefen.com/assets/images/genres/Guragigna.jpg"));
                genres.add(new Genre("Kunama", "http://www.huluzefen.com/assets/images/genres/Kunama.jpg"));
                genres.add(new Genre("Harari", "http://www.huluzefen.com/assets/images/genres/Harari.jpg"));
                genres.add(new Genre("Tizita-Playlist", "http://www.huluzefen.com/assets/images/genres/Tizita-Playlist.jpg"));
                genres.add(new Genre("Ethio-HipHop", "http://www.huluzefen.com/assets/images/genres/Ethio-HipHop.jpg"));
                genres.add(new Genre("Instrumental", "http://www.huluzefen.com/assets/images/genres/Instrumental.jpg"));
//                genres.add(new Genre("album-no-image", "http://www.huluzefen.com/assets/images/album-no-image.png"));
                genres.add(new Genre("Spiritual", "http://www.huluzefen.com/assets/images/genres/Spiritual.jpg"));
                genres.add(new Genre("Wedding", "http://www.huluzefen.com/assets/images/genres/Wedding.jpg"));
                genres.add(new Genre("Holiday", "http://www.huluzefen.com/assets/images/genres/Holiday.jpg"));
                genres.add(new Genre("Bands", "http://www.huluzefen.com/assets/images/genres/Bands.jpg"));
                genres.add(new Genre("Classical", "http://www.huluzefen.com/assets/images/genres/Classical.jpg"));

                return genres;
            }
        });
    }
}
