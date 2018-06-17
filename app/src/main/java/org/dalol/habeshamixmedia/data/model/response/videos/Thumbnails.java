package org.dalol.habeshamixmedia.data.model.response.videos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by filippo on 05/11/2017.
 */

public class Thumbnails {

    @SerializedName("default")
    private Default mDefault;

    private High high;

    private Medium medium;

    public Default getDefault ()
    {
        return mDefault;
    }

    public High getHigh ()
    {
        return high;
    }

    public void setHigh (High high)
    {
        this.high = high;
    }

    public Medium getMedium ()
    {
        return medium;
    }

    public void setMedium (Medium medium)
    {
        this.medium = medium;
    }

    @Override
    public String toString()
    {
        return "Thumbnails [default = "+mDefault+", high = "+high+", medium = "+medium+"]";
    }
}
