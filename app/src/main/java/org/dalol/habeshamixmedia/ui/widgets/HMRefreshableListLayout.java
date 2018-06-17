package org.dalol.habeshamixmedia.ui.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.dalol.habeshamixmedia.R;
import org.dalol.habeshamixmedia.utilities.ViewUtils;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 17:18.
 */
public class HMRefreshableListLayout extends SwipeRefreshLayout {

    private RecyclerView mItemsList;
    private ProgressBar mProgressBar;
    private Context mContext;

    public HMRefreshableListLayout(@NonNull Context context) {
        super(context);
        initialize(context, null);
    }

    public HMRefreshableListLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    void initialize(@NonNull Context context, @Nullable AttributeSet attrs) {
        mContext = context;

        FrameLayout frameLayout = new FrameLayout(context);

        mItemsList = new RecyclerView(new ContextThemeWrapper(context, R.style.ScrollbarRecyclerView));
        frameLayout.addView(mItemsList, new RecyclerView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        mProgressBar = new ProgressBar(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        frameLayout.addView(mProgressBar, params);

        addView(frameLayout, new RecyclerView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(mContext, "Refreshing", Toast.LENGTH_SHORT).show();
                setRefreshing(false);
                if (refreshListListener != null) {
                    refreshListListener.onRefresh();
                }
            }
        });
    }

    public void setItemListProgressBarVisibility(boolean visible) {
        ViewUtils.setVisible(mProgressBar, visible);
    }

    public void setItemListLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mItemsList.setLayoutManager(layoutManager);
    }

    public void setItemListHasFixedSize(boolean fixedSize) {
        mItemsList.setHasFixedSize(fixedSize);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        mItemsList.addItemDecoration(itemDecoration);
    }

    public void addOnScrollListener(RecyclerView.OnScrollListener scrollListener) {
        mItemsList.addOnScrollListener(scrollListener);
    }

    public void removeOnScrollListener(RecyclerView.OnScrollListener scrollListener) {
        mItemsList.removeOnScrollListener(scrollListener);
    }

    public void setItemListAdapter(RecyclerView.Adapter<?> adapter) {
        mItemsList.setAdapter(adapter);
    }

    public void setListItemClipToPadding(boolean clip) {
        mItemsList.setClipToPadding(clip);
    }

    public void setListItemPadding(int left, int top, int right, int bottom) {
        mItemsList.setPadding(ViewUtils.toPx(mContext, left), ViewUtils.toPx(mContext, top), ViewUtils.toPx(mContext, right), ViewUtils.toPx(mContext, bottom));
    }

    public void setRefreshListListener(OnRefreshListListener listener) {
        this.refreshListListener = listener;
    }

    private OnRefreshListListener refreshListListener;

    public interface OnRefreshListListener  {

        void onRefresh();
    }
}
