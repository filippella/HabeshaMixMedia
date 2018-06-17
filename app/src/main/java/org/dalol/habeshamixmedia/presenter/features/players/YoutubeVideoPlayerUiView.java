package org.dalol.habeshamixmedia.presenter.features.players;

import org.dalol.habeshamixmedia.data.model.vo.VideoInfoVO;
import org.dalol.habeshamixmedia.presenter.base.CommonUiView;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 17/06/2018 at 11:56.
 */
public interface YoutubeVideoPlayerUiView extends CommonUiView {

    void onLoadVideo(String videoTitle, VideoInfoVO videoInfoVO);
}
