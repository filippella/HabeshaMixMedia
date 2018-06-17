package org.dalol.habeshamixmedia.data.model.response.channel;

/**
 * Created by filippo on 05/11/2017.
 */

public class Items {

    private String id;

    private String etag;

    private Snippet snippet;

    private String kind;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getEtag ()
    {
        return etag;
    }

    public void setEtag (String etag)
    {
        this.etag = etag;
    }

    public Snippet getSnippet ()
    {
        return snippet;
    }

    public void setSnippet (Snippet snippet)
    {
        this.snippet = snippet;
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
        return "Items [id = "+id+", etag = "+etag+", snippet = "+snippet+", kind = "+kind+"]";
    }
}
