package com.example.hw3_b;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    public List<SongOls> getList() {
        return list;
    }

    List<SongOls> list;
    OnItemClickListener onItemClickListener;

    public MusicAdapter(List<SongOls> list, OnItemClickListener onItemClickListener) {
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MusicViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        public MusicViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_nameOfSong);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SongOls song = list.get(getLayoutPosition());
                    onItemClickListener.onItemClick(song);
                }
            });
        }

        public void bind(SongOls song) {
            tv_name.setText(song.getName());
        }
    }

        public interface OnItemClickListener{
            void onItemClick(SongOls song);
        }
}
