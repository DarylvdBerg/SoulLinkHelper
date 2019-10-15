package com.example.soullinkhelper.adapter;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.soullinkhelper.R;
import com.example.soullinkhelper.enums.State;
import com.example.soullinkhelper.models.Pair;

import java.util.ArrayList;

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.ViewHolder> {
    private ArrayList<Pair> pairArrayList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView pokemon1;
        private ImageView pokemon2;
        private TextView pokemonName1;
        private TextView pokemonName2;

        private ImageView[] pokemonTypes1;
        private ImageView[] pokemonTypes2;

        private TextView pokemonState;
        private TextView caughtRoute;
        private LinearLayout pairLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemon1 = itemView.findViewById(R.id.pokemon_1);
            pokemon2 = itemView.findViewById(R.id.pokemon_2);
            pokemonName1 = itemView.findViewById(R.id.pokemon_name_1);
            pokemonName2 = itemView.findViewById(R.id.pokemon_name_2);

            pokemonTypes1 = new ImageView[]{itemView.findViewById(R.id.pokemon1_type_1),
                    itemView.findViewById(R.id.pokemon1_type_2)
            };
            pokemonTypes2 = new ImageView[]{itemView.findViewById(R.id.pokemon2_type_1),
                    itemView.findViewById(R.id.pokemon2_type_2)
            };

            pokemonState = itemView.findViewById(R.id.pokemon_state);
            caughtRoute = itemView.findViewById(R.id.route_caught);
            pairLayout = itemView.findViewById(R.id.linearLayoutPair);
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
        holder.pokemonState.setText(pairArrayList.get(position).getState().toString());
        holder.caughtRoute.setText(pairArrayList.get(position).getRoute());

        changeLinearLayout(holder, position);
        typeBackground(holder, pairArrayList.get(position).getPokemon1().getTypes(), holder.pokemonTypes1);
        typeBackground(holder, pairArrayList.get(position).getPokemon2().getTypes(), holder.pokemonTypes2);
    }

    private void changeLinearLayout(ViewHolder holder, int position){
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filterDead = new ColorMatrixColorFilter(matrix);
        matrix.setSaturation(1);
        ColorMatrixColorFilter filterAlive = new ColorMatrixColorFilter(matrix);

        State state = pairArrayList.get(position).getState();

        holder.pokemon1.setColorFilter((state.equals(State.DEAD)) ?
                filterDead : filterAlive);
        holder.pokemon1.setBackground((state.equals(State.DEAD)) ?
                ContextCompat.getDrawable(holder.itemView.getContext(), R.mipmap.pokemon_1_background_dead) :
                ContextCompat.getDrawable(holder.itemView.getContext(), R.mipmap.pokemon_1_background));
        holder.pokemon2.setColorFilter((state.equals(State.DEAD)) ?
                filterDead : filterAlive);
        holder.pokemon2.setBackground((state.equals(State.DEAD)) ?
                ContextCompat.getDrawable(holder.itemView.getContext(), R.mipmap.pokemon_2_background_dead) :
                ContextCompat.getDrawable(holder.itemView.getContext(), R.mipmap.pokemon_2_background));

        holder.pairLayout.setBackground((state.equals(State.DEAD)) ?
                ContextCompat.getDrawable(holder.itemView.getContext(), R.mipmap.link_background_dead):
                ContextCompat.getDrawable(holder.itemView.getContext(), R.mipmap.link_background));

        //Werkt niet
//        for (ImageView v : holder.pokemonTypes1){
//            v.setColorFilter((state.equals(State.DEAD)) ? filterDead : filterAlive);
//        }
//        for (ImageView v : holder.pokemonTypes2){
//            v.setColorFilter((state.equals(State.DEAD)) ? filterDead : filterAlive);
//        }
    }

    //Hoe maak ik dit mooier?
    private void typeBackground(ViewHolder holder, ArrayList<String> types, ImageView[] pokemonTypes){
        int counter = 0;
        if (types.size() == 1){
            pokemonTypes[1].setBackground(null);
        }
        for (String type : types){
            ImageView pokemon1Type1 = pokemonTypes[counter];
            counter++;
            switch (type.toLowerCase()){
                case "water":
                    pokemon1Type1.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),
                            R.mipmap.water));
                    break;
                case "steel":
                    pokemon1Type1.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),
                            R.mipmap.steel));
                    break;
                case "rock":
                    pokemon1Type1.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),
                            R.mipmap.rock));
                    break;
                case "psychic":
                    pokemon1Type1.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),
                            R.mipmap.psychic));
                    break;
                case "poison":
                    pokemon1Type1.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),
                            R.mipmap.poison));
                    break;
                case "normal":
                    pokemon1Type1.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),
                            R.mipmap.normal));
                    break;
                case "ice":
                    pokemon1Type1.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),
                            R.mipmap.ice));
                    break;
                case "ground":
                    pokemon1Type1.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),
                            R.mipmap.ground));
                    break;
                case "grass":
                    pokemon1Type1.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),
                            R.mipmap.grass));
                    break;
                case "ghost":
                    pokemon1Type1.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),
                            R.mipmap.ghost));
                    break;
                case "flying":
                    pokemon1Type1.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),
                            R.mipmap.flying));
                    break;
                case "fire":
                    pokemon1Type1.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),
                            R.mipmap.fire));
                    break;
                case "fighting":
                    pokemon1Type1.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),
                            R.mipmap.fighting));
                    break;
                case "electric":
                    pokemon1Type1.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),
                            R.mipmap.electric));
                    break;
                case "dragon":
                    pokemon1Type1.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),
                            R.mipmap.dragon));
                    break;
                case "dark":
                    pokemon1Type1.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),
                            R.mipmap.dark));
                    break;
                case "bug":
                    pokemon1Type1.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),
                            R.mipmap.bug));
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return pairArrayList.size();
    }
}
