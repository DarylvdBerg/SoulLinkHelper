package com.example.soullinkhelper.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.soullinkhelper.R;
import com.example.soullinkhelper.models.Pair;

import java.util.ArrayList;

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.ViewHolder> {
    private ArrayList<Pair> pairArrayList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView pokemon1;
        private ImageView pokemon2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemon1 = itemView.findViewById(R.id.pokemon_1);
            pokemon2 = itemView.findViewById(R.id.pokemon_2);
        }
    }

    public LinkAdapter(ArrayList<Pair> pairList){
        this.pairArrayList = pairList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.link_list, parent, false);



     return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(pairArrayList.get(position).getPokemon1().getSprite())
                .into(holder.pokemon1);

        Glide.with(holder.itemView.getContext())
                .load(pairArrayList.get(position).getPokemon2().getSprite())
                .into(holder.pokemon2);
    }

    @Override
    public int getItemCount() {
        return pairArrayList.size();
    }
}
