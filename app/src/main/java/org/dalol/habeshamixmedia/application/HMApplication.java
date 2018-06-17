package org.dalol.habeshamixmedia.application;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;

import org.dalol.habeshamixmedia.R;
import org.dalol.habeshamixmedia.common.NetworkChangeReceiver;

/**
 * Created by filippo on 13/01/2018.
 */

public class HMApplication extends Application {

    private static Context sInstance;
    private NetworkChangeReceiver mChangeReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        MobileAds.initialize(this, getString(R.string.admob_app_id));
        registerReceiver(mChangeReceiver = new NetworkChangeReceiver(), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public static void showToast(String message) {
        Toast.makeText(sInstance, message, Toast.LENGTH_SHORT).show();
    }

    public static Context getApplicationInstance() {
        return sInstance;
    }

    @Override
    public void onTerminate() {
        unregisterReceiver(mChangeReceiver);
        sInstance = null;
        super.onTerminate();
    }
}
