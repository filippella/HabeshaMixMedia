package org.dalol.habeshamixmedia.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.dalol.habeshamixmedia.utilities.NetworkUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by filippo on 13/01/2018.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    private final List<NetworkChangeObserver> mNetworkChangeObservers = new LinkedList<>();

    public void addNetworkChangeObserver(NetworkChangeObserver observer) {
        mNetworkChangeObservers.add(observer);
    }

    public void removeNetworkChangeObserver(NetworkChangeObserver observer) {
        mNetworkChangeObservers.remove(observer);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        for (int i = 0; i < mNetworkChangeObservers.size(); i++) {
            NetworkChangeObserver observer = mNetworkChangeObservers.get(i);
            observer.onNetworkStateChanged(NetworkUtils.isNetworkAvailable(context));
        }
    }

    public interface NetworkChangeObserver {

        void onNetworkStateChanged(boolean connected);
    }
}