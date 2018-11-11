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
    TextView tvName;
    RecyclerView recyclerView;
    MusicAdapter adapter;
    ArrayList<SongOls> songs;
    SharedPreferences sp;
    int theme = 0;
    private int SETTINGS_ACTION = 1;

    @Override
    public void onCreate(Bundle savedInstanceState){

        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(this);
        String themeName = pref.getString("theme", "Theme1");
        switch (themeName){
            case("Theme1"):
                setTheme(R.style.OrangeAppTheme);
                break;
            case("Theme2"):
                setTheme(R.style.PurpleAppTheme);
                break;
            case("Theme3"):
                setTheme(R.style.TealAppTheme);
                break;
        }
        Toast.makeText(this, "Theme has been reset to " + themeName,
                Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_main);
        tvName = findViewById(R.id.tv_nameOfSong);
        recyclerView = findViewById(R.id.rv_main);
        songs = new ArrayList<>();
        recyclerView.setLayoutManager(new
                LinearLayoutManager(this));
        MusicAdapter.OnItemClickListener onItemClickListener = new MusicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SongOls song) {
                Intent intent = new Intent(MainActivityOld.this, PlayingActivity.class);
                intent.putExtra(NAME, song.getName());
                intent.putExtra(RAW, song.getRaw());
                startActivity(intent);
            }
        };
        adapter = new MusicAdapter(fillIn(), onItemClickListener);
        recyclerView.setAdapter(adapter);
    }

    public ArrayList<SongOls> fillIn(){
        songs.add(new SongOls("Awolnation - Sail", R.raw.awolnation_sail));
        songs.add(new SongOls("Nirvana - Polly", R.raw.nirvana_polly));
        songs.add(new SongOls("Nomy - Heart of ice", R.raw.nomy_heart_of_ice));
        songs.add(new SongOls("Our Last night - Sunrise", R.raw.our_last_night_sunrise));
        songs.add(new SongOls("Seether - The gift", R.raw.seether_the_gift));
        return songs;
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
                Toast.makeText(this, "Starting new Activity", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
