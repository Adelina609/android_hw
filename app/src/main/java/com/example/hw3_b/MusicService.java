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

public class MusicService extends Service implements MediaPlayer.OnCompletionListener {

    private MediaPlayer mediaPlayer;
    private List<Song> songs = new ArrayList<>();
    private final IBinder musicBind = new MusicBinder();
    private int currentIdx = 0;
    private boolean isFirstCreating = true;

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

    public void setList(ArrayList<Song> list){
        songs = list;
    }

    public void playSong(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }else {
            if (isFirstCreating) {
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(getApplicationContext(), songs.get(currentIdx).getRaw());
                isFirstCreating = false;
            } mediaPlayer.start();
        }
    }

    public void playNext(){
        mediaPlayer.reset();
        if(currentIdx == songs.size()-1) currentIdx = -1;
        mediaPlayer = MediaPlayer.create(getApplicationContext(), songs.get(++currentIdx).getRaw());
        mediaPlayer.start();
    }

    public void playPrev(){
        mediaPlayer.reset();
        if(currentIdx == 0) currentIdx = songs.size();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), songs.get(--currentIdx).getRaw());
        mediaPlayer.start();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.reset();
        playNext();
    }

    public void setCurrentIdx(int currentIdx) {
        this.currentIdx = currentIdx;
    }

    public int getCurrentIdx() {
        return currentIdx;
    }
}
