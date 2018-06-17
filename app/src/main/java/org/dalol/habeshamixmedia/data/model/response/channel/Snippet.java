package org.dalol.habeshamixmedia.data.model.response.channel;

/**
 * Created by filippo on 05/11/2017.
 */

public class Snippet {

    private String publishedAt;

    private String title;

    private String description;

    private String customUrl;

    private Thumbnails thumbnails;

    private Localized localized;

    public String getPublishedAt ()
    {
        return publishedAt;
    }

    public void setPublishedAt (String publishedAt)
    {
        this.publishedAt = publishedAt;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getCustomUrl ()
    {
        return customUrl;
    }

    public void setCustomUrl (String customUrl)
    {
        this.customUrl = customUrl;
    }

    public Thumbnails getThumbnails ()
    {
        return thumbnails;
    }

    public void setThumbnails (Thumbnails thumbnails)
    {
        this.thumbnails = thumbnails;
    }

    public Localized getLocalized ()
    {
        return localized;
    }

    public void setLocalized (Localized localized)
    {
        this.localized = localized;
    }

    @Override
    public String toString()
    {
        return "Snippet [publishedAt = "+publishedAt+", title = "+title+", description = "+description+", customUrl = "+customUrl+", thumbnails = "+thumbnails+", localized = "+localized+"]";
    }
}
