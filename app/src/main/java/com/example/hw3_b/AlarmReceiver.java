package com.example.hw3_b;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

//import android.support.v4.content.WakefulBroadcastReceiver;
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent1) {
//        Intent intent1 = new Intent(context, MainActivity.class);
//        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        mn.sendNotification("title", "body", intent, 0);
//        intent1 = new Intent(context, MainActivity.class);
//        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        Toast.makeText(context, "..................", Toast.LENGTH_LONG).show();
//
//        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//        Ringtone ringtone = RingtoneManager.getRingtone(context, uri);
//        ringtone.play();
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
//                .setSmallIcon(R.drawable.ic_menu_gallery)
//                .setContentTitle("mmmmmmm")
//                .setContentText("bodyy");
//
//        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1,
//                    PendingIntent.FLAG_ONE_SHOT);
//        builder.setContentIntent(pendingIntent);
//
//
//        NotificationManager notificationManager = (NotificationManager)context
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0, builder.build());
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent =  PendingIntent.getActivity(context, 0, intent, 0);
        AlarmService alarmService = new AlarmService(context, pendingIntent);
//        alarmService.createNotifChannel();
        alarmService.sendNotification();
        Toast.makeText(context, "BITCHBITCH", Toast.LENGTH_LONG).show();
    }

//    public void sendNotification(String title, String body, Intent intent, int pushid){
//        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.ic_menu_gallery)
//                .setContentTitle(title)
//                .setSound(defaultSound)
//                .setContentText(body);
//
//        if(intent!=null){
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
//                    PendingIntent.FLAG_ONE_SHOT);
//            builder.setContentIntent(pendingIntent);
//        }
//
//        NotificationManager notificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0, builder.build());
//    }



    }