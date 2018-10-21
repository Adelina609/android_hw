package com.example.hw3_b;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class AlarmService {

    Context context;
    PendingIntent pendingIntent;
    private NotificationManager notificationManager;
    private static int ID = 0;

    public AlarmService(Context context, PendingIntent pendingIntent) {
        this.context = context;
        this.pendingIntent = pendingIntent;
    }

    public void sendNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("Alarm")
                .setSmallIcon(R.drawable.ic_menu_manage)
                .setContentText("Yey");

        notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        builder.setContentIntent(pendingIntent);
        notificationManager.notify(0, builder.build());
    }

//    @TargetApi(Build.VERSION_CODES.O)
//    public void createNotifChannel(){
//        CharSequence name = "name";
//        int importance = NotificationManager.IMPORTANCE_DEFAULT;
//        NotificationChannel channel = new NotificationChannel("cccc", name, importance);
//        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
//        notificationManager.createNotificationChannel(channel);
//        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, "cccc");
//        notificationManager.notify(ID, notification.build());
//    }

}
