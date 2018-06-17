package org.dalol.habeshamixmedia.ui.ViewHolders;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;

import org.dalol.habeshamixmedia.R;

public class NativeAppInstallAdViewHolder {

    private final static int MAX_RETRY = 3;

    private final Context mContext;
    private int mRetryCount = 0;

    private final NativeAppInstallAdView adView;
    private final ImageView iconView;
    private final TextView headlineView;
    private final TextView bodyView;
    private final Button callToActionView;
    private final TextView priceView;
    private final RatingBar starRatingView;
    private final TextView storeView;

    private String mAdUnitId;
    private boolean mAdLoaded;
    private boolean mIsLoading;

    public NativeAppInstallAdViewHolder(View itemView) {

        mContext = itemView.getContext();
        this.adView = (NativeAppInstallAdView) itemView;

        // The MediaView will display a video asset if one is present in the ad, and the
        // first image asset otherwise.
        MediaView mediaView = (MediaView) adView.findViewById(R.id.appinstall_media);
        this.adView.setMediaView(mediaView);

        // Register the view used for each individual asset.
        this.adView.setIconView(this.iconView = adView.findViewById(R.id.appinstall_app_icon));
        this.adView.setHeadlineView(this.headlineView = adView.findViewById(R.id.appinstall_headline));
        this.adView.setBodyView(this.bodyView = adView.findViewById(R.id.appinstall_body));
        this.adView.setCallToActionView(this.callToActionView = adView.findViewById(R.id.appinstall_call_to_action));
        this.adView.setPriceView(this.priceView = adView.findViewById(R.id.appinstall_price));
        this.adView.setStarRatingView(this.starRatingView = adView.findViewById(R.id.appinstall_stars));
        this.adView.setStoreView(this.storeView = adView.findViewById(R.id.appinstall_store));
    }

    public void loadAd(String adUnitId) {
        if(mRetryCount > MAX_RETRY) return;
        mRetryCount++;
        if (!mAdLoaded && !mIsLoading) {
            mAdUnitId = adUnitId;
            mIsLoading = true;
            AdLoader adLoader = new AdLoader.Builder(mContext, mAdUnitId)
                    .forAppInstallAd(new NativeAppInstallAd.OnAppInstallAdLoadedListener() {
                        @Override
                        public void onAppInstallAdLoaded(NativeAppInstallAd ad) {
                            // An app install ad loaded successfully, call this method again to
                            // load the next ad in the items list.
                            mAdLoaded = true;
                            mIsLoading = false;
                            bindAd(ad);

                        }
                    }).withAdListener(new AdListener() {
                        @Override
                        public void onAdFailedToLoad(int errorCode) {
                            // A native ad failed to load. Call this method again to load
                            // the next ad in the items list.
                            Log.e("MainActivity", "The previous native ad failed to load. Attempting to" +
                                    " load another.");
                            mAdLoaded = false;
                            mIsLoading = false;
                            loadAd(mAdUnitId);
                        }
                    }).withNativeAdOptions(new NativeAdOptions.Builder()
                            // Methods in the NativeAdOptions.Builder class can be
                            // used here to specify individual options settings.
                            .build())
                    .build();

            adLoader.loadAd(new AdRequest.Builder().build());
        }
    }

    private void bindAd(NativeAppInstallAd nativeAppInstallAd) {
        // Some assets are guaranteed to be in every NativeAppInstallAd.
        this.iconView.setImageDrawable(nativeAppInstallAd.getIcon().getDrawable());
        this.headlineView.setText(nativeAppInstallAd.getHeadline());
        this.bodyView.setText(nativeAppInstallAd.getBody());
        this.callToActionView.setText(nativeAppInstallAd.getCallToAction());

        // These assets aren't guaranteed to be in every NativeAppInstallAd, so it's important to
        // check before trying to display them.
        if (nativeAppInstallAd.getPrice() == null) {
            this.priceView.setVisibility(View.INVISIBLE);
        } else {
            this.priceView.setVisibility(View.VISIBLE);
            this.priceView.setText(nativeAppInstallAd.getPrice());
        }

        if (nativeAppInstallAd.getStore() == null) {
            this.storeView.setVisibility(View.INVISIBLE);
        } else {
            this.storeView.setVisibility(View.VISIBLE);
            this.storeView.setText(nativeAppInstallAd.getStore());
        }

        if (nativeAppInstallAd.getStarRating() == null) {
            this.starRatingView.setVisibility(View.INVISIBLE);
        } else {
            this.starRatingView.setRating(nativeAppInstallAd.getStarRating().floatValue());
            this.starRatingView.setVisibility(View.VISIBLE);
        }

        // Assign native ad object to the native view.
        adView.setNativeAd(nativeAppInstallAd);
    }
}
