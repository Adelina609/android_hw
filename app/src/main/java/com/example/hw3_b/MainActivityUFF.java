package com.example.hw3_b;

//public class MainActivityUFF extends AppCompatActivity {
//
//    public final String NAME_TEXT = "text";
//    public final String NAME_VALUE = "value";
//    ImageView image;
//    TextView name;
//    TextView rating;
//    RecyclerView activity_main;
//    //FilmsAdapter adapter;
//    RVAdapter adapter;
//    List<Films> persons;
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        activity_main = findViewById(R.id.activity_main);
//        activity_main.setLayoutManager(new LinearLayoutManager(this));
////        image = findViewById(R.id.film_photo);
////        name = findViewById(R.id.film_name);
////        rating = findViewById(R.id.film_rating);
//        persons = fillIn();
//        adapter = new RVAdapter(persons);
//        activity_main.setAdapter(adapter);
////        FilmsAdapter.OnItemClickListener onItemClickListener = new FilmsAdapter.OnItemClickListener() {
////            @Override
////            public void onItemClick(Films space) {
////                Intent intent = new Intent(MainActivityHUI.this, DescriptionActivity.class);
////                intent.putExtra(NAME_TEXT, space.getName());
////                intent.putExtra(NAME_VALUE, space.getDesc());
////                startActivity(intent);
////            }
////        };
//    }
//
//    public List<Films> fillIn(){
//        persons.add(new Films("Темная материя", 2, R.drawable.sense));
//        persons.add(new Films("Пульсар", 3, R.drawable.island));
//        persons.add(new Films("Красный карлик", 1, R.drawable.butterfly));
//        persons.add(new Films("Химико",4, R.drawable.soul));
//        persons.add(new Films("Сверхзвуковые звезды", 6,R.drawable.vechnoe ));
//        persons.add(new Films("Магнетар", 5,R.drawable.intouchables ));
//        persons.add(new Films("Тройная туманность", 7, R.drawable.wall_y));
//        persons.add(new Films("Психея", 8, R.drawable.psyche));
//        persons.add(new Films("Квазары", 9, R.drawable.quasar));
//        persons.add(new Films("Сверхгигант", 10, R.drawable.supergiant));
//        return persons;
//    }
//
//
//}