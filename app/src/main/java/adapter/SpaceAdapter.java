package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.hw3_b.*;

import java.util.ArrayList;
import java.util.List;


public class SpaceAdapter extends RecyclerView.Adapter<SpaceAdapter.SpaceViewHolder>{

    List<Space> list;
    OnItemClickListener onItemClickListener;
    public SpaceAdapter(List<Space> list, OnItemClickListener onItemClickListener) {
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    public List<Space> getList() {
        return list;
    }


    @Override
    public SpaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new SpaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SpaceViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SpaceViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;
        TextView desc;
        public SpaceViewHolder(View view){
            super(view);
            image = view.findViewById(R.id.iv_main);
            name = view.findViewById(R.id.tv_name_main);
            desc = view.findViewById(R.id.tv_desc_main);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Space space = list.get(getLayoutPosition());
                    onItemClickListener.onItemClick(space);
                }
            });
        }

        public void bind(Space object ){
            name.setText(object.getName());
            desc.setText(object.getDesc());
            image.setImageResource(object.getImage());
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Space space);
    }
}
