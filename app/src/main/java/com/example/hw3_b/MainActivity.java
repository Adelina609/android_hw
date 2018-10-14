package com.example.hw3_b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import adapter.FilmsAdapter;

public class MainActivity extends AppCompatActivity {

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
                Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);
                intent.putExtra(NAME_TEXT, space.getName());
                intent.putExtra(NAME_VALUE, space.getDesc());
                startActivity(intent);
            }
        };
        adapter = new FilmsAdapter(fillIn(), onItemClickListener);
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
                FilmDiffUtilCallBack diff = new FilmDiffUtilCallBack(fillIn(), filterNames());
                DiffUtil.DiffResult result = DiffUtil.calculateDiff(diff);
                adapter.setList(filterNames());
                result.dispatchUpdatesTo(adapter);
            case R.id.action_symbols_filter:
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    public List<Films> fillIn(){
        list.add(new Films("Начало", 7, R.drawable.inception));
        list.add(new Films("Шестое чувство", 74, R.drawable.sense));
        list.add(new Films("1+1", 5, R.drawable.intouchables));
        list.add(new Films("Остров проклятых", 37, R.drawable.island));
        list.add(new Films("Эффект бабочки", 79, R.drawable.butterfly));
        list.add(new Films("А в душе я танцую", 96, R.drawable.soul));
        list.add(new Films("ВАЛЛ·И", 31, R.drawable.wall_y));
        return list;
    }
    @TargetApi(24)
    public List<Films> filterNames(){
        List<Films> newList = fillIn();
        Collections.sort(newList, new SymbolsComparator());
        return newList;
    }
}