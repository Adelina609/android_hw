package com.example.hw3_b;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.media.AudioManager;
import android.os.PowerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MusicService extends Service implements MediaPlayer.OnCompletionListener {

    private MediaPlayer mediaPlayer;
    private List<SongOls> songs = new ArrayList<>();
    private final IBinder musicBind = new MusicBinder();
    int i = 0;
    private static final int NOTIFY_ID = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        initMusicPlayer();
    }

    public class MusicBinder extends Binder{
        MusicService getService(){
            return MusicService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }

    public void initMusicPlayer(){
        mediaPlayer.setWakeMode(getApplicationContext(),
                PowerManager.PARTIAL_WAKE_LOCK);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnCompletionListener(this);
    }

    public void setList(ArrayList<SongOls> list){
        songs = list;
    }

    boolean flag = true;
    public void playSong(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }else {
            if (flag) {
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(getApplicationContext(), songs.get(i).getRaw());
                flag = false;
            } mediaPlayer.start();
            Intent notIntent = new Intent(this, MainActivityOld.class);
            notIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendInt = PendingIntent.getActivity(this, 0,
                    notIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            Notification.Builder builder = new Notification.Builder(this);

            builder.setContentIntent(pendInt)
                    .setSmallIcon(R.drawable.ic_menu_send)
                    .setTicker(songs.get(i).getName())
                    .setOngoing(true)
                    .setContentTitle("Playing")
                    .setContentText(songs.get(i).getName());
            Notification not = builder.build();

            startForeground(NOTIFY_ID, not);
        }
    }

    public void playNext(){
        mediaPlayer.reset();
        if(i == songs.size()-1) i = -1;
        mediaPlayer = MediaPlayer.create(getApplicationContext(), songs.get(++i).getRaw());
        mediaPlayer.start();
    }

    public void playPrev(){
        mediaPlayer.reset();
        if(i == 0) i = songs.size();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), songs.get(--i).getRaw());
        mediaPlayer.start();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.reset();
        playNext();
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
