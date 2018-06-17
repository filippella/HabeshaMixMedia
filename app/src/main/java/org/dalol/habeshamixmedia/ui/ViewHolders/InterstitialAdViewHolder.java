package org.dalol.habeshamixmedia.ui.ViewHolders;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 17/06/2018 at 22:48.
 */
public class InterstitialAdViewHolder {

    private InterstitialAd mInterstitialAd;

    public InterstitialAdViewHolder(Context context, String adUnitId) {
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(adUnitId);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener(){

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }

    public void showAd() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
