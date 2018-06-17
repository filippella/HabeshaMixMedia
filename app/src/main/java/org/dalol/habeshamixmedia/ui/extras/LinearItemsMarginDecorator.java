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
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by filippo on 13/01/2018.
 */

public class LinearItemsMarginDecorator extends RecyclerView.ItemDecoration {

    private final int mLeftSpaceSize;
    private final int mRightSpaceSize;
    private final int mTopSpaceSize;
    private final int mBottomSpaceSize;

    public LinearItemsMarginDecorator(int spaceSize) {
        this(spaceSize, spaceSize, spaceSize, spaceSize);
    }

    public LinearItemsMarginDecorator(int leftSpaceSize, int rightSpaceSize, int topSpaceSize, int bottomSpaceSize) {
        mLeftSpaceSize = leftSpaceSize;
        mRightSpaceSize = rightSpaceSize;
        mTopSpaceSize = topSpaceSize;
        mBottomSpaceSize = bottomSpaceSize;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);

        if (position < 1) {
            outRect.top = mTopSpaceSize;
        } else {
            outRect.top = 0;
        }
        outRect.left = mLeftSpaceSize;
        outRect.right = mRightSpaceSize;
        outRect.bottom = mBottomSpaceSize;
    }
}
