package org.dalol.habeshamixmedia.presenter.features.albums;

import org.dalol.habeshamixmedia.data.model.vo.Album;
import org.dalol.habeshamixmedia.presenter.base.CommonUiView;

import java.util.List;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 21:17.
 */
public interface AlbumListUiView extends CommonUiView {

    void onLoadAlbums(List<Album> albums);
}
