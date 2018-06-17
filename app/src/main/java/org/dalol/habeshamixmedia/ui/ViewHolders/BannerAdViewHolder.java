package org.dalol.habeshamixmedia.ui.ViewHolders;

import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class BannerAdViewHolder {

    public final static int MAX_RETRY = 5;

    private AdView adView;

    private int mRetryCount = 0;

    private boolean mIsLoading;
    private boolean mAdLoaded;

    public BannerAdViewHolder(View itemView) {
        this.adView = (AdView) itemView;
        this.adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdLoaded = true;
                mIsLoading = false;
                if (adView.getVisibility() != View.VISIBLE) adView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                mAdLoaded = false;
                mIsLoading = false;
                loadAd(mRetryCount--);
            }
        });
    }

    public void loadAd(int maxRetryCount) {
        if(maxRetryCount < 0) return;
        mRetryCount = maxRetryCount;
        if (!mAdLoaded && !mIsLoading) {
            mIsLoading = true;
            adView.loadAd(new AdRequest.Builder().build());
        }
    }
}
