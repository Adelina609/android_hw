package adapter;
//
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.hw3_b.Films;
//import com.example.hw3_b.R;
//
//import java.util.List;
//
//public class RVAdapter extends RecyclerView.Adapter<RVAdapter.FilmViewHolder>{
//
//    List<Films> list;
//
//    public RVAdapter(List<Films> list) {
//        this.list = list;
//    }
//
//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//    }
//
//    @Override
//    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
//        return new FilmViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(FilmViewHolder holder, int position) {
//        holder.name.setText(list.get(position).getName());
//        //holder.rating.setText(""+ list.get(position).getRating());
//        holder.photo.setImageResource(list.get(position).getImage());
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public static class FilmViewHolder extends RecyclerView.ViewHolder {
//        CardView cv;
//        TextView name;
//        TextView rating;
//        ImageView photo;
//
//        FilmViewHolder(View itemView) {
//            super(itemView);
//            cv = (CardView)itemView.findViewById(R.id.cv);
//            name = (TextView)itemView.findViewById(R.id.film_name);
//            rating = (TextView)itemView.findViewById(R.id.film_rating);
//            photo = (ImageView)itemView.findViewById(R.id.film_photo);
//        }
//    }
//
//}