package org.dalol.habeshamixmedia.ui.base;

import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 16:46.
 */
public abstract class BaseViewHolder<D> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    @CallSuper
    protected void bindData(D data) {}
}
