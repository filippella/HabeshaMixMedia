package org.dalol.habeshamixmedia.data.model.response.channel;

/**
 * Created by filippo on 05/11/2017.
 */

public class Localized {

    private String title;

    private String description;

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

    @Override
    public String toString()
    {
        return "Localized [title = "+title+", description = "+description+"]";
    }
}
