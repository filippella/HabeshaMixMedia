package org.dalol.habeshamixmedia.ui.player;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;

import org.dalol.habeshamixmedia.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by filippo on 13/01/2018.
 */

public class PlayerNotificationDelegate {

    private static final int NOTIFICATION_ID = 1;

    private NotificationCompat.Builder mNotificationBuilder;

    public void showNotification(Service service, String text, Class<?> clazz) {

        Intent playerIntent = new Intent(service, clazz);
        Intent dashboardIntent = new Intent(service, clazz);

        PendingIntent intent = TaskStackBuilder.create(service)
                .addNextIntentWithParentStack(dashboardIntent)
                .addNextIntent(playerIntent)
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

//        RemoteViews views = new RemoteViews(service.getPackageName(), R.layout.player_small_notification);
        RemoteViews views = new RemoteViews(service.getPackageName(), 0);
//        views.setOnClickPendingIntent(R.id.player_pause, getPendingAction(service, "pause"));
//        views.setOnClickPendingIntent(R.id.player_previous, getPendingAction(service, "previous"));
//        views.setOnClickPendingIntent(R.id.player_next, getPendingAction(service, "next"));
//        views.setOnClickPendingIntent(R.id.player_close, getPendingAction(service, "stop"));
//        views.setTextViewText(R.id.player_song_name, text);
//        views.setViewVisibility(R.id.player_progress_bar, View.GONE);

        mNotificationBuilder = new NotificationCompat.Builder(service)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(intent)
                .setOngoing(true)
                .setContent(views);

        service.startForeground(NOTIFICATION_ID, mNotificationBuilder.build());
    }

    private PendingIntent getPendingAction(Service service, String action, Class<?> clazz) {
        Intent intent = new Intent(service, clazz);
        intent.setAction(action);
        return PendingIntent.getService(service, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void hideNotification(Service service) {
        service.stopForeground(true);
        NotificationManager mNotificationManager = (NotificationManager) service.getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.cancel(NOTIFICATION_ID);
    }
}
