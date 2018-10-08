package com.example.hw3_b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import adapter.SpaceAdapter;

public class MainActivity extends AppCompatActivity {

    public final String NAME_TEXT = "text";
    public final String NAME_VALUE = "value";

    ImageView image;
    TextView name;
    TextView desc;
    RecyclerView rv;
    SpaceAdapter adapter;

    List<Space> list= new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.iv_main);
        name = findViewById(R.id.tv_name_main);
        desc = findViewById(R.id.tv_desc_main);
        rv = findViewById(R.id.rv_main);
        rv.setLayoutManager(new LinearLayoutManager(this));
        SpaceAdapter.OnItemClickListener onItemClickListener = new SpaceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Space space) {
                Intent intent = new Intent(MainActivity.this, Description.class);
                intent.putExtra(NAME_TEXT, space.getName());
                intent.putExtra(NAME_VALUE, space.getDesc());
                startActivity(intent);
            }
        };
        adapter = new SpaceAdapter(fillIn(), onItemClickListener);
        rv.setAdapter(adapter);
    }

    public List<Space> fillIn(){
        list.add(new Space("Темная материя", "Тёмная материя, являющаяся одной из самых великих тайн " +
                "в современной астрофизике," +
                " представляет собой гипотетическую материю, которую невозможно увидеть с помощью телескопов. " +
                "Тем не менее, считается, что приблизительно 85 процентов материи " +
                "во Вселенной является тёмной материей.", R.drawable.hey));
        list.add(new Space("Пульсар", "Пульсар представляет собой плотную," +
                " сильно намагниченную, вращающуюся нейтронную звезду, которая испускает луч " +
                "электромагнитного излучения. " +
                "В прошлом астрономы считали, что излучение, которое можно наблюдать, " +
                "когда оно направлено в сторону Земли, было инопланетной формой общения. ", R.drawable.pulsar));
        list.add(new Space("Красный карлик", "Относительно маленькие и холодные " +
                "красные карлики являются наиболее распространёнными" +
                " звёздами в Млечном Пути и составляют три четверти звёзд в галактике. " +
                "Наиболее близко расположенным к Солнцу (примерно в 4,3 световых годах)" +
                " и возможно самым знаменитым красным карликом является Проксима Центавра (Proxima Centauri).", R.drawable.red_dwarf));
        list.add(new Space("Химико","Химико + desc", R.drawable.himiko ));
        list.add(new Space("Сверхзвуковые звезды", "Сверхзвуковые звезды + desc",R.drawable.hypervelocity ));
        list.add(new Space("Магнетар", "Магнетар + desc",R.drawable.magnetar ));
        list.add(new Space("Тройная туманность", "Тройная туманность + desc", R.drawable.nebula));
        list.add(new Space("Психея", "Психея + desc", R.drawable.psyche));
        list.add(new Space("Квазары", "Квазары + desc", R.drawable.quasar));
        list.add(new Space("Сверхгигант", "Сверхгигант + desc", R.drawable.supergiant));
        return list;
    }
}