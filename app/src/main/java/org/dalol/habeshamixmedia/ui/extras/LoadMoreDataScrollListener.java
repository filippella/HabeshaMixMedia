package org.dalol.habeshamixmedia.ui.extras;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 16:25.
 */
public abstract class LoadMoreDataScrollListener extends RecyclerView.OnScrollListener {

    private boolean mLoading = false; // True if we are still waiting for the last set of data to load
    private boolean mCanLoadMore = true;
    private static final int VISIBLE_ITEM_THRESHOLD = 10;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (mLinearLayoutManager == null) {
            mLinearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        }
        int totalItemCount = mLinearLayoutManager.getItemCount();
        int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
        if (mCanLoadMore && !mLoading && totalItemCount <= (lastVisibleItem + VISIBLE_ITEM_THRESHOLD)) {
            onLoadMore(recyclerView, this);
            mLoading = true;
        }
    }
    public void setLoading(boolean loading) {
        mLoading = loading;
    }
    public void canLoadMore(boolean done) {
        mCanLoadMore = !done;
    }
    protected abstract void onLoadMore(RecyclerView recyclerView, LoadMoreDataScrollListener listener);
}
