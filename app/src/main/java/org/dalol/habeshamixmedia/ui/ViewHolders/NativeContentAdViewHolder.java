package org.dalol.habeshamixmedia.ui.ViewHolders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;

import org.dalol.habeshamixmedia.R;

import java.util.List;

public class NativeContentAdViewHolder {

    private final NativeContentAdView adView;
    private final TextView headlineView;
    private final ImageView imageView;
    private final TextView bodyView;
    private final TextView callToActionView;
    private final ImageView logoView;
    private final TextView advertiserView;

    private final Context mContext;

    private String mAdUnitId;
    private boolean mIsLoading;
    private boolean mAdLoaded;

    public NativeContentAdViewHolder(View itemView) {
        mContext = itemView.getContext();
        this.adView = (NativeContentAdView) itemView;

        // Register the view used for each individual asset.
        this.adView.setHeadlineView(this.headlineView = this.adView.findViewById(R.id.contentad_headline));
        this.adView.setImageView(this.imageView = this.adView.findViewById(R.id.contentad_image));
        this.adView.setBodyView(this.bodyView = this.adView.findViewById(R.id.contentad_body));
        this.adView.setCallToActionView(this.callToActionView = this.adView.findViewById(R.id.contentad_call_to_action));
        this.adView.setLogoView(this.logoView = this.adView.findViewById(R.id.contentad_logo));
        this.adView.setAdvertiserView(this.advertiserView = this.adView.findViewById(R.id.contentad_advertiser));
    }

    public void loadAd(String adUnitId) {
        if (!mAdLoaded && !mIsLoading) {
            mAdUnitId = adUnitId;
            mIsLoading = true;
            AdLoader adLoader = new AdLoader.Builder(mContext, mAdUnitId)
                    .forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
                        @Override
                        public void onContentAdLoaded(NativeContentAd ad) {
                            // A content ad loaded successfully, call this method again to
                            // load the next ad in the items list.
                            mAdLoaded = true;
                            mIsLoading = false;
                            bindAd(ad);
//                        ListItem<NativeAd> adListItem = new ListItem<>(ListItem.VIEW_TYPE_NATIVE_CONTENT_AD);
//                        adListItem.setModel(ad);
//                        mNativeAds.add(adListItem);
//                        loadNativeAd(adLoadCount + 1);
                        }
                    }).withAdListener(new AdListener() {
                        @Override
                        public void onAdFailedToLoad(int errorCode) {
                            // A native ad failed to load. Call this method again to load
                            // the next ad in the items list.
//                        Log.e("MainActivity", "The previous native ad failed to load. Attempting to" +
//                                " load another.");
//                        loadNativeAd(adLoadCount + 1);
                            mAdLoaded = false;
                            mIsLoading = false;
                            loadAd(mAdUnitId);
                        }
                    }).withNativeAdOptions(new NativeAdOptions.Builder()
                            // Methods in the NativeAdOptions.Builder class can be
                            // used here to specify individual options settings.
                            .build())
                    .build();

            // Load the Native Express ad.
            adLoader.loadAd(new AdRequest.Builder().build());
        }
    }

    private void bindAd(NativeContentAd nativeContentAd) {
        // Some assets are guaranteed to be in every NativeContentAd.
        this.headlineView.setText(nativeContentAd.getHeadline());
        this.bodyView.setText(nativeContentAd.getBody());
        this.callToActionView.setText(nativeContentAd.getCallToAction());
        this.advertiserView.setText(nativeContentAd.getAdvertiser());

        List<NativeAd.Image> images = nativeContentAd.getImages();

        if (images.size() > 0) {
            this.imageView.setImageDrawable(images.get(0).getDrawable());
        }

        // Some aren't guaranteed, however, and should be checked.
        NativeAd.Image logoImage = nativeContentAd.getLogo();

        if (logoImage == null) {
            this.logoView.setVisibility(View.INVISIBLE);
        } else {
            this.logoView.setImageDrawable(logoImage.getDrawable());
            this.logoView.setVisibility(View.VISIBLE);
        }

        // Assign native ad object to the native view.
        adView.setNativeAd(nativeContentAd);
    }
}
