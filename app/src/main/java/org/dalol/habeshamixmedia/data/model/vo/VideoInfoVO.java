package org.dalol.habeshamixmedia.data.model.vo;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 16/06/2018 at 15:44.
 */
public class VideoInfoVO {

    private final String url;
    private final String itag;
    private final String type;
    private final String quality;

    public VideoInfoVO(String url, String itag, String type, String quality) {
        this.url = url;
        this.itag = itag;
        this.type = type;
        this.quality = quality;
    }

    public String getUrl() {
        return url;
    }

    public String getItag() {
        return itag;
    }

    public String getType() {
        return type;
    }

    public String getQuality() {
        return quality;
    }

    @Override
    public String toString() {
        return "Video{" +
                "url='" + url + '\'' +
                ", itag='" + itag + '\'' +
                ", type='" + type + '\'' +
                ", quality='" + quality + '\'' +
                '}';
    }
}
