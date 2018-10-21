package com.example.hw3_b;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;

public class AlarmReceiver extends BroadcastReceiver {

    final String ID = "5";

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(ID, "channel1", importance);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
            channel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),
                    channel.getAudioAttributes());
        }
        NotificationService notificationService = new NotificationService();
        notificationManager.notify(1, notificationService.sendNotification(context));
    }
}
