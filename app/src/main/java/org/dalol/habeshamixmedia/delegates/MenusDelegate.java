package org.dalol.habeshamixmedia.delegates;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 17/06/2018 at 22:23.
 */
public class MenusDelegate {

    public static void rate(Context context) {
        String applicationId = "org.dalol.habeshamixmedia";
        String url;
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            url = "market://details?id=" + applicationId;
        } catch (final Exception e) {
            url = "https://play.google.com/store/apps/details?id=" + applicationId;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        context.startActivity(intent);
    }

    public static void share(Context context) {
        String applicationId = "org.dalol.habeshamixmedia";
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Hey check out Habesha MixMedia at: https://play.google.com/store/apps/details?id=" + applicationId;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Habesha MixMedia App");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        context.startActivity(sharingIntent);
    }
}
