package org.dalol.habeshamixmedia.ui.extras;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import org.dalol.habeshamixmedia.R;
import org.dalol.habeshamixmedia.application.HMApplication;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 16:49.
 */
public final class AppImageLoader {

    private AppImageLoader() {
    }

    public static void loadImage(Context context, Object url, ImageView iv) {
        Glide.with(HMApplication.getApplicationInstance())
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .apply(RequestOptions.placeholderOf(R.color.colorLightBlue))
                .apply(RequestOptions.errorOf(R.drawable.ic_album_no_image))
                .into(iv);
    }
}
