package org.dalol.habeshamixmedia.data.model.response.channel;

/**
 * Created by filippo on 05/11/2017.
 */

public class ChannelResponse {

    private String etag;

    private Items[] items;

    private PageInfo pageInfo;

    private String kind;

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public Items[] getItems() {
        return items;
    }

    public void setItems(Items[] items) {
        this.items = items;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "ChannelResponse [etag = " + etag + ", items = " + items + ", pageInfo = " + pageInfo + ", kind = " + kind + "]";
    }
}
