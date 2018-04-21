package com.mab.swg;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    private List<Users> items;

    public UserAdapter(List<Users> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_list_items_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(items.get(position).getName());
        holder.score.setText(String.valueOf(items.get(position).getScor()));
        holder.rank.setText(String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView score;
        public TextView name;
        public TextView rank;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.player_name);
            score = itemView.findViewById(R.id.player_score);
            rank = itemView.findViewById(R.id.player_index);
        }
    }
}
