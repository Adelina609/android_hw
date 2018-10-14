package com.example.hw3_b;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import adapter.FilmsAdapter;

public class RecyclerActivity extends AppCompatActivity {

    public final String NAME_TEXT = "text";
    public final String NAME_VALUE = "value";

    ImageView image;
    TextView name;
    TextView rating;
    RecyclerView rv;
    FilmsAdapter adapter;
    List<Films> list= new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.person_photo);
        name = findViewById(R.id.person_name);
        rating = findViewById(R.id.person_age);
        rv = findViewById(R.id.rv_main);
        rv.setLayoutManager(new LinearLayoutManager(this));
        FilmsAdapter.OnItemClickListener onItemClickListener = new FilmsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Films space) {
                Intent intent = new Intent(RecyclerActivity.this, DescriptionActivity.class);
                intent.putExtra(NAME_TEXT, space.getName());
                intent.putExtra(NAME_VALUE, space.getDesc());
                startActivity(intent);
            }
        };
        adapter = new FilmsAdapter(FilmUtils.getFilms(), onItemClickListener);
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_rating_filter:
                adapter.updateList(FilmUtils.filterNames());
                return true;
            case R.id.action_symbols_filter:
                adapter.updateList(FilmUtils.filterRatings());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
