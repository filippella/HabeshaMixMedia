package org.dalol.habeshamixmedia.ui.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 17/06/2018 at 16:13.
 */
public class RequestPermissionHelper {

    private Context mContext;
    private RequestPermissionListener mRequestPermissionListener;
    private int mRequestCode;

    public void requestPermission(Context context, @NonNull String[] permissions, int requestCode,
                                  RequestPermissionListener listener) {
        mContext = context;
        mRequestCode = requestCode;
        mRequestPermissionListener = listener;

        if (!needRequestRuntimePermissions()) {
            mRequestPermissionListener.onSuccess();
            return;
        }
        requestUnGrantedPermissions(permissions, requestCode);
    }

    private boolean needRequestRuntimePermissions() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    private void requestUnGrantedPermissions(String[] permissions, int requestCode) {
        String[] unGrantedPermissions = findUnGrantedPermissions(permissions);
        if (unGrantedPermissions.length == 0) {
            mRequestPermissionListener.onSuccess();
            return;
        }
        mRequestPermissionListener.onRequestPermission(unGrantedPermissions, requestCode);
    }

    private boolean isPermissionGranted(String permission) {
        return ActivityCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private String[] findUnGrantedPermissions(String[] permissions) {
        List<String> unGrantedPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (!isPermissionGranted(permission)) {
                unGrantedPermissionList.add(permission);
            }
        }
        return unGrantedPermissionList.toArray(new String[0]);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == mRequestCode) {
            if (grantResults.length > 0) {
                for (int grantResult : grantResults) {
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        mRequestPermissionListener.onFailed();
                        return;
                    }
                }
                mRequestPermissionListener.onSuccess();
            } else {
                mRequestPermissionListener.onFailed();
            }
        }
    }

    public interface RequestPermissionListener {
        void onSuccess();

        void onFailed();

        void onRequestPermission(String[] permissions, int requestCode);
    }
}