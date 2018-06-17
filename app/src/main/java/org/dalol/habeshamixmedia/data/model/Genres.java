package org.dalol.habeshamixmedia.data.model;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 20:03.
 */
public class Genres {

    private String id;
    private Pivot pivot;
    private String updated_at;
    private String name;
    private String created_at;

    public String getId() {
        return id;
    }

    public Pivot getPivot() {
        return pivot;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getName() {
        return name;
    }

    public String getCreated_at() {
        return created_at;
    }
}
