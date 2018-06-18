package org.dalol.habeshamixmedia.presenter.features.videos;

import org.dalol.habeshamixmedia.data.model.vo.VideosVO;
import org.dalol.habeshamixmedia.data.model.vo.YoutubeVideoVO;
import org.dalol.habeshamixmedia.presenter.base.CommonUiView;

import java.util.List;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 16/06/2018 at 23:53.
 */
public interface VideoListUiView extends CommonUiView {

    void onLoadVideoList(VideosVO videoVOS);
}
