package org.dalol.habeshamixmedia.ui.base;

import android.app.ProgressDialog;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 22/04/2018 at 17:35.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        onActivityCreated(savedInstanceState);
    }

    @CallSuper
    protected void onActivityCreated(Bundle savedInstanceState) {}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void replaceFragment(@IdRes int containerId, Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(containerId, fragment, tag)
                .commit();
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showDialog(String message, boolean cancelable) {
        hideDialog();
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(cancelable);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    public void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected void changeStatusBarColor(int statusBarColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && statusBarColor > 0) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, statusBarColor));
        }
    }

    protected void showHome() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    protected void showHome(int resId) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            showHome();
            actionBar.setHomeAsUpIndicator(resId);
        }
    }

    protected void showHome(int resId, int colorTint) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            showHome();
            Drawable drawable = ContextCompat.getDrawable(this, resId);
            tintDrawable(drawable, colorTint);
            actionBar.setHomeAsUpIndicator(drawable);
        }
    }

    protected void tintDrawable(Drawable drawable, int colorTint) {
        int color = ContextCompat.getColor(this, colorTint);
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrappedDrawable, color);
        DrawableCompat.setTintMode(wrappedDrawable, PorterDuff.Mode.SRC_IN);
    }

    protected void showHome(Drawable drawable) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            showHome();
            actionBar.setHomeAsUpIndicator(drawable);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected abstract int getContentView();
}
