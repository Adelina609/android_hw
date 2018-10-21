package com.example.hw3_b;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import com.example.notification.R;

public class NotificationService {

    public Notification sendNotification(Context context) {
        Intent intentResult = new Intent(context, SecondActivity.class);
        intentResult.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                0, intentResult, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "5")
                .setContentTitle("Wake up")
                .setSmallIcon(R.drawable.uni)
                .setContentText("Wake me up when September ends...")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent);
        return notificationBuilder.build();
    }
}
