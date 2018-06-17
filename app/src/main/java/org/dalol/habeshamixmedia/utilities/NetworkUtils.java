package org.dalol.habeshamixmedia.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

/**
 * Created by filippo on 13/01/2018.
 */

public final class NetworkUtils {

    private NetworkUtils() {
        throw new IllegalStateException("Never instantiate a utility class.");
    }

    public static boolean isNetworkAvailable(@NonNull Context context) {
        ConnectivityManager connectivityManager  = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }
}
