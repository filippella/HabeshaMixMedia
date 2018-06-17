package org.dalol.habeshamixmedia.data.model.response.videos;

/**
 * Created by filippo on 05/11/2017.
 */

public class PageInfo {

    private String totalResults;

    private String resultsPerPage;

    public String getTotalResults ()
    {
        return totalResults;
    }

    public void setTotalResults (String totalResults)
    {
        this.totalResults = totalResults;
    }

    public String getResultsPerPage ()
    {
        return resultsPerPage;
    }

    public void setResultsPerPage (String resultsPerPage)
    {
        this.resultsPerPage = resultsPerPage;
    }

    @Override
    public String toString()
    {
        return "PageInfo [totalResults = "+totalResults+", resultsPerPage = "+resultsPerPage+"]";
    }
}
