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

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by filippo on 13/01/2018.
 */

public class GridItemsMarginDecorator extends RecyclerView.ItemDecoration {

    private final int mSpaceSize;
    private GridLayoutManager mGridLayoutManager;
    private int mSpanCount;

    public GridItemsMarginDecorator(int spaceSize) {
        mSpaceSize = spaceSize;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);

        if (mGridLayoutManager == null) {
            mGridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
            mSpanCount = mGridLayoutManager.getSpanCount();
        }

        if (position < mSpanCount) {
            outRect.top = mSpaceSize;
        } else {
            outRect.top = 0;
        }

        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        int column = params.getSpanIndex();

        outRect.left = mSpaceSize - column * mSpaceSize / mSpanCount;
        outRect.right = (column + 1) * mSpaceSize / mSpanCount;
        outRect.bottom = mSpaceSize;
    }
}
