package org.dalol.habeshamixmedia.data.transformers.videos;

import org.dalol.habeshamixmedia.data.model.response.videos.High;
import org.dalol.habeshamixmedia.data.model.response.videos.Items;
import org.dalol.habeshamixmedia.data.model.response.videos.Snippet;
import org.dalol.habeshamixmedia.data.model.response.videos.Thumbnails;
import org.dalol.habeshamixmedia.data.model.response.videos.VideoListResponse;
import org.dalol.habeshamixmedia.data.model.vo.YoutubeVideoVO;
import org.dalol.habeshamixmedia.data.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 17/06/2018 at 01:17.
 */
public class VideoListTransformer implements Transformer<VideoListResponse, List<YoutubeVideoVO>> {

    @Override
    public List<YoutubeVideoVO> transform(VideoListResponse input) throws Exception {
        List<YoutubeVideoVO> videoVOs = new ArrayList<>();
        if (input != null) {
            Items[] items = input.getItems();
            if (items != null) {
                for (Items item : items) {
                    String videoId = item.getId().getVideoId();
                    String videoTitle = "-";
                    String videoDescription = "-";
                    String videoPublishDate = "-";
                    String videoThumbnailURL = "-";

                    Snippet snippet = item.getSnippet();
                    if (snippet != null) {
                        videoTitle = snippet.getTitle();
                        videoDescription = snippet.getDescription();
                        videoPublishDate = snippet.getPublishedAt();
                        Thumbnails thumbnails = snippet.getThumbnails();
                        if (thumbnails != null) {
                            High high = thumbnails.getHigh();
                            if (high != null) {
                                videoThumbnailURL = high.getUrl();
                            }
//                            Default aDefault = thumbnails.getDefault();
//                            if (aDefault != null) {
//                                videoVO.mVideoThumbnailURL = aDefault.getUrl();
//                            }
                        }
                    }
                    YoutubeVideoVO videoVO = new YoutubeVideoVO(videoId, videoTitle, videoDescription, videoPublishDate, videoThumbnailURL);
                    videoVOs.add(videoVO);
                }
            }
        }
        return videoVOs;
    }
}
