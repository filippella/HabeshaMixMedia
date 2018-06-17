package org.dalol.habeshamixmedia.data.model.request;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Mon, 11/06/2018 at 00:32.
 */
public class VideoDownloadRequest {

    public String type = "mp3";
    public String id;
    public String bitrate = "";
    public String start = "";
    public String end = "";
    public String tempo = "";
    public boolean loudnorm;
    public String title = "";
    public String artist = "";
    public String album = "";
    public String track = "";
    public String year = "";

    public VideoDownloadRequest(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }
}
