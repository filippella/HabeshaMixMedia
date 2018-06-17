/*
 * Copyright (c) 2017 Lightful. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Lightful.
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package org.dalol.habeshamixmedia.ui.extras;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.dalol.habeshamixmedia.utilities.NetworkUtils;

/**
 * Created by filippo on 13/01/2018.
 */

public abstract class LoadMoreScrollListener extends RecyclerView.OnScrollListener {

    private boolean mLoading = false;
    private static final int VISIBLE_ITEM_THRESHOLD = 10;
    private LinearLayoutManager mLinearLayoutManager;
    private Context mContext;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (mContext == null) {
            mContext = recyclerView.getContext();
        }
        if (mLinearLayoutManager == null) {
            mLinearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        }
        int totalItemCount = mLinearLayoutManager.getItemCount();
        int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();

        if (recyclerView.canScrollVertically(dy) && !mLoading && dy > 0 && totalItemCount <= (lastVisibleItem + VISIBLE_ITEM_THRESHOLD) && NetworkUtils.isNetworkAvailable(mContext)) {
            mLoading = true;
            onLoadMore(recyclerView, totalItemCount);
        }
    }

    public void setLoading(boolean loading) {
        mLoading = loading;
    }

    protected abstract void onLoadMore(RecyclerView recyclerView, int totalItemCount);
}
