package org.dalol.habeshamixmedia.data.model.response.videos;

/**
 * Created by filippo on 05/11/2017.
 */

public class Id {

    private String videoId;

    private String kind;

    public String getVideoId ()
    {
        return videoId;
    }

    public void setVideoId (String videoId)
    {
        this.videoId = videoId;
    }

    public String getKind ()
    {
        return kind;
    }

    public void setKind (String kind)
    {
        this.kind = kind;
    }

    @Override
    public String toString()
    {
        return "Id [videoId = "+videoId+", kind = "+kind+"]";
    }
}
