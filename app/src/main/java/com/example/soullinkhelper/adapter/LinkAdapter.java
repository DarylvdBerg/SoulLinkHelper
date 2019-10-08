package com.example.soullinkhelper.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.soullinkhelper.R;
import com.example.soullinkhelper.models.Pair;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.ViewHolder> {
    private ArrayList<Pair> pairArrayList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView pokemon1;
        private ImageView pokemon2;
        private TextView pokemonName1;
        private TextView pokemonName2;
        private TextView pokemonType1;
        private TextView pokemonType2;
        private TextView pokemonState;
        private TextView caughtRoute;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemon1 = itemView.findViewById(R.id.pokemon_1);
            pokemon2 = itemView.findViewById(R.id.pokemon_2);
            pokemonName1 = itemView.findViewById(R.id.pokemon_name_1);
            pokemonName2 = itemView.findViewById(R.id.pokemon_name_2);
            pokemonType1 = itemView.findViewById(R.id.pokemon_type_1);
            pokemonType2 = itemView.findViewById(R.id.pokemon_type_2);
            pokemonState = itemView.findViewById(R.id.pokemon_state);
            caughtRoute = itemView.findViewById(R.id.route_caught);
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

        holder.pokemonName1.setText(pairArrayList.get(position).getPokemon1().getNickname());
        holder.pokemonName2.setText(pairArrayList.get(position).getPokemon2().getNickname());
        holder.pokemonType1.setText(TextUtils.join(" / ", pairArrayList.get(position).getPokemon1().getTypes()));
        holder.pokemonType2.setText(TextUtils.join(" / ", pairArrayList.get(position).getPokemon2().getTypes()));
        holder.pokemonState.setText(pairArrayList.get(position).getState().toString());
        holder.caughtRoute.setText(pairArrayList.get(position).getRoute());
    }

    @Override
    public int getItemCount() {
        return pairArrayList.size();
    }
}
