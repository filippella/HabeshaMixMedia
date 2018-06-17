package org.dalol.habeshamixmedia.data.model.response.channel;

/**
 * Created by filippo on 05/11/2017.
 */

public class High {

    private String url;

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "High [url = "+url+"]";
    }
}
