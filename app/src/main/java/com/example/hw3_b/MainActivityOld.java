package com.example.hw3_b;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivityOld extends AppCompatActivity {

    public final String NAME = "name";
    public final String RAW = "raw";
    public final String THEME = "theme";
    TextView tvName;
    RecyclerView recyclerView;
    MusicAdapter adapter;
    SharedPreferences sp;
    private int SETTINGS_ACTION = 1;
    public String themeName;
    private SongsUtil songsUtil = new SongsUtil();

    @Override
    public void onCreate(Bundle savedInstanceState){
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(this);
        themeName = pref.getString("theme", "Orange");
        setAppTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_main);
        tvName = findViewById(R.id.tv_nameOfSong);
        recyclerView.setLayoutManager(new
                LinearLayoutManager(this));
        MusicAdapter.OnItemClickListener onItemClickListener = new MusicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SongOls song) {
                Intent intent = new Intent(MainActivityOld.this, PlayingActivity.class);
                intent.putExtra(NAME, song.getName());
                intent.putExtra(RAW, song.getRaw());
                intent.putExtra(THEME, themeName);
                startActivity(intent);
            }
        };
        adapter = new MusicAdapter(songsUtil.fillIn(), onItemClickListener);
        recyclerView.setAdapter(adapter);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.settings:
                startActivityForResult(new Intent(this,
                        ThemePreferenceActivity.class), SETTINGS_ACTION);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SETTINGS_ACTION) {
            if (resultCode == ThemePreferenceActivity.RESULT_CODE_THEME_UPDATED) {
                finish();
                startActivity(getIntent());
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
