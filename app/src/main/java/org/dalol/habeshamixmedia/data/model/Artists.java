package org.dalol.habeshamixmedia.data.model;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 16:13.
 */
public class Artists {

    private String to;
    private String next_page_url;
    private String last_page;
    private String total;
    private String per_page;
    private Data[] data;
    private String from;
    private String prev_page_url;
    private String current_page;

    public String getTo() {
        return to;
    }

    public String getNext_page_url() {
        return next_page_url;
    }

    public String getLast_page() {
        return last_page;
    }

    public String getTotal() {
        return total;
    }

    public String getPer_page() {
        return per_page;
    }

    public Data[] getData() {
        return data;
    }

    public String getFrom() {
        return from;
    }

    public String getPrev_page_url() {
        return prev_page_url;
    }

    public String getCurrent_page() {
        return current_page;
    }
}
