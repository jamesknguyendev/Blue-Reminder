package com.myapp.jamesnguyen.bluereminder.notification_builder;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.myapp.jamesnguyen.bluereminder.R;
import com.myapp.jamesnguyen.bluereminder.activities.MainActivity;

/**
 * Created by jamesnguyen on 3/26/18.
 */

//TODO add channel to notification for Android O
public class NotificationBuilder {

    public static NotificationCompat.Builder buildNotification(Context context,
                                                               String channelId,
                                                               String contentTitle,
                                                               String contentDescription){

        Intent intent = createNewIntent(context, MainActivity.START_DEFAULT_FRAGMENT);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, channelId)
                .setContentTitle(contentTitle)
                .setContentText(contentDescription)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_icon_notification)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true);
        return builder;
    }

    private static Intent createNewIntent(Context context, int fragmentCode){
        Intent intent = MainActivity.createIntent(context, fragmentCode);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }


}
