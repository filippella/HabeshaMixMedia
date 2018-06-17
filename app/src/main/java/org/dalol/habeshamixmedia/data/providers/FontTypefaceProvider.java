package org.dalol.habeshamixmedia.data.providers;

import android.content.Context;
import android.graphics.Typeface;

import org.dalol.habeshamixmedia.application.HMApplication;
import org.dalol.habeshamixmedia.data.UIFont;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by filippo on 13/01/2018.
 */

public final class FontTypefaceProvider implements UIFont {

    private final Map<String, Typeface> mFontTypefaceMap;

    private FontTypefaceProvider() {
        mFontTypefaceMap = new HashMap<>();
    }

    private final static class InstanceHolder {
        private static final FontTypefaceProvider INSTANCE = new FontTypefaceProvider();
    }

    public static FontTypefaceProvider getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public Typeface getTypeface(String fontName) {
        Typeface typeface = mFontTypefaceMap.get(fontName);
        if (typeface == null) {
            Context context = HMApplication.getApplicationInstance();
            typeface = Typeface.createFromAsset(context.getAssets(), fontName);
            mFontTypefaceMap.put(fontName, typeface);
        }
        return typeface;
    }
}
