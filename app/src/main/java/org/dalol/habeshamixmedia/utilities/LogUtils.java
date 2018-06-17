package org.dalol.habeshamixmedia.utilities;

import android.util.Log;

/**
 * Created by filippo on 13/01/2018.
 */

public final class LogUtils {

    private static final String TAG = LogUtils.class.getSimpleName();

    private LogUtils(){}

    public static void log(String message) {
        Log.d(TAG, message);
    }

    public static void log(String tag, String message) {
        Log.d(tag, message);
    }
}
