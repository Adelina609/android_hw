package com.example.hw3_b;

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

    public void setI(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
