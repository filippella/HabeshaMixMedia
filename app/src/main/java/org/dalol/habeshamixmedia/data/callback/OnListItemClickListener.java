package org.dalol.habeshamixmedia.data.callback;

import android.support.annotation.IntDef;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.dalol.habeshamixmedia.data.callback.OnListItemClickListener.ViewTouchType.CLICK;
import static org.dalol.habeshamixmedia.data.callback.OnListItemClickListener.ViewTouchType.LONG_CLICK;
import static org.dalol.habeshamixmedia.data.callback.OnListItemClickListener.ViewTouchType.TOUCH;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 16:44.
 */
public interface OnListItemClickListener<T> {

    void onItemClick(View view, T model, int position, @ViewTouchType int touchType);

    @Retention(RetentionPolicy.RUNTIME)
    @IntDef({TOUCH, CLICK, LONG_CLICK})
    @interface ViewTouchType {
        int TOUCH = 0;
        int CLICK = 1;
        int LONG_CLICK = 2;
    }
}
