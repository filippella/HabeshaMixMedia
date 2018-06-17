package org.dalol.habeshamixmedia.utilities;

import android.os.Build;
import android.text.Html;

/**
 * Created by filippo on 13/01/2018.
 */

public  final class TextUtils {

    private TextUtils() {
        throw new IllegalAccessError("Never instantiate a utility class.");
    }

    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static boolean equals(String first, String second) {
        return (first != null && first.equals(second)) || (second != null && second.equals(first)) || (first == second);
    }

    public static boolean equalsIgnoreCase(String first, String second) {
        return (first != null && first.equalsIgnoreCase(second)) || (second != null && second.equalsIgnoreCase(first)) || (first == second);
    }

    public static CharSequence fromHtml(String html) {
        if (isEmpty(html)) return "";
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ? Html.fromHtml(html, 0) : Html.fromHtml(html);
    }
}
