package org.dalol.habeshamixmedia.utilities;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 17:19.
 */
public final class ViewUtils {

    private ViewUtils() {
    }

    public static void gone(View... views) {
        for (View view : views) {
            view.setVisibility(View.GONE);
        }
    }

    public static void visible(View... views) {
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void invisible(View... views) {
        for (View view : views) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    public static void setVisible(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }

    public static void setGone(View view, boolean gone) {
        view.setVisibility(gone ? View.GONE : View.VISIBLE);
    }

    public static int toPx(Context context, float value) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics()));
    }
}
