package org.dalol.habeshamixmedia.presenter.features.genres;

import org.dalol.habeshamixmedia.data.model.vo.Genre;
import org.dalol.habeshamixmedia.data.model.vo.Genre;
import org.dalol.habeshamixmedia.presenter.base.CommonUiView;

import java.util.List;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 15:51.
 */
public interface GenreListUiView extends CommonUiView {

    void onLoadGenres(List<Genre> genres);
}
