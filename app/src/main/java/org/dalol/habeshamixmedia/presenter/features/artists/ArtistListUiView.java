package org.dalol.habeshamixmedia.presenter.features.artists;

import org.dalol.habeshamixmedia.data.model.vo.Artist;
import org.dalol.habeshamixmedia.presenter.base.CommonUiView;

import java.util.List;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 15:52.
 */
public interface ArtistListUiView extends CommonUiView {

    void onLoadArtists(List<Artist> artists, boolean canLoadMore);
}
