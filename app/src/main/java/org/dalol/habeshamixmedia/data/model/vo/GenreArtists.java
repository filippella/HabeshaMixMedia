package org.dalol.habeshamixmedia.data.model.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 16:36.
 */
public class GenreArtists {

    private final String mGenreName;
    private final List<Artist> mArtists = new ArrayList<>();
    private final boolean mIsAbleToLoadMore;
    private final int mNextPageNumber;

    public GenreArtists(String genreName, List<Artist> artists, boolean canLoadMore, int nextPageNumber) {
        mArtists.addAll(artists);
        mIsAbleToLoadMore = canLoadMore;
        mNextPageNumber = nextPageNumber;
        mGenreName = genreName;
    }

    public int getNextPageNumber() {
        return mNextPageNumber;
    }

    public boolean isAbleToLoadMore() {
        return mIsAbleToLoadMore;
    }

    public List<Artist> getArtists() {
        return mArtists;
    }

    public String getGenreName() {
        return mGenreName;
    }
}
