package org.dalol.habeshamixmedia.data.model.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 17/06/2018 at 00:02.
 */
public class VideosVO {

    private final List<YoutubeVideoVO> videoVOS = new ArrayList<>();
    private final String nextPageToken;

    public VideosVO(List<YoutubeVideoVO> videoVOS, String nextPageToken) {
        this.videoVOS.addAll(videoVOS);
        this.nextPageToken = nextPageToken;
    }

    public List<YoutubeVideoVO> getVideoVOS() {
        return videoVOS;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }
}
