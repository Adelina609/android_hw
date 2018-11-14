package com.example.hw3_b;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayingActivity extends AppCompatActivity {

    public final String NAME = "name";
    public final String RAW = "raw";
    public ImageView logo;
    public ImageButton next;
    public ImageButton prev;
    public ImageButton start;
    public TextView tv_name;
    public MusicService musicSrv;
    private boolean musicBound;
    private Intent playIntent;
    private int raw = 0;
    private String themeName;
    private SongsUtil songsUtil = new SongsUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(this);
        themeName = pref.getString("theme", "Orange");
        setAppTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        logo = findViewById(R.id.iv_cover);
        next = findViewById(R.id.btn_next);
        prev = findViewById(R.id.btn_prev);
        start = findViewById(R.id.btn_start);
        tv_name = findViewById(R.id.tv_name_in_play);
        Intent intent = getIntent();
        String name = intent.getStringExtra(NAME);
        raw = intent.getIntExtra(RAW, 0);
        tv_name.setText(name);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicSrv.playSong();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicSrv.playNext();
                tv_name.setText(songsUtil.fillIn().get(musicSrv.getCurrentIdx()).getName());
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicSrv.playPrev();
                tv_name.setText(songsUtil.fillIn().get(musicSrv.getCurrentIdx()).getName());
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(musicBound){
            unbindService(musicConnection);
            musicBound = false;
        }
    }

    public void setAppTheme(){
        switch (themeName){
            case("Orange"):
                setTheme(R.style.OrangeAppTheme);
                break;
            case("Purple"):
                setTheme(R.style.PurpleAppTheme);
                break;
            case("Green"):
                setTheme(R.style.TealAppTheme);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(playIntent == null){
            playIntent = new Intent(this, MusicService.class);
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
        }
    }

    private ServiceConnection musicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) iBinder;
            musicSrv = binder.getService();
            musicSrv.setList(songsUtil.fillIn());
            musicSrv.setCurrentIdx(getPosition());
            musicBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            musicBound = false;
        }
    };

    public int getPosition(){
        for(int i = 0; i < 5; i++){
            if(raw == songsUtil.fillIn().get(i).getRaw()){
                return i;
            }
        }
        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.playing_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_end:
                stopService(playIntent);
                musicSrv=null;
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
