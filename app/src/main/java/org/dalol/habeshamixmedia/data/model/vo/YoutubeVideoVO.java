package org.dalol.habeshamixmedia.data.model.vo;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 17/06/2018 at 00:02.
 */
public class YoutubeVideoVO {

    public String mVideoId;
    public String mVideoTitle;
    public String mVideoDescription;
    public String mVideoPublishDate;
    public String mVideoThumbnailURL;

    public YoutubeVideoVO(String videoId, String videoTitle, String videoDescription, String videoPublishDate, String videoThumbnailURL) {
        mVideoId = videoId;
        mVideoTitle = videoTitle;
        mVideoDescription = videoDescription;
        mVideoPublishDate = videoPublishDate;
        mVideoThumbnailURL = videoThumbnailURL;
    }
}
