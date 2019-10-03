package com.example.soullinkhelper.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.soullinkhelper.R;
import com.example.soullinkhelper.dao.PokemonDAO;
import com.example.soullinkhelper.models.Player;
import com.example.soullinkhelper.models.PlayerManager;

import java.util.ArrayList;

public class LinkPokemonFragment extends Fragment {

    private String sprite;
    private EditText pokemonNickname;
    private Player caughtBy;
    private String pokemonName;
    private ImageView pokemonSprite;
    private TextView pokemonType;

    private ArrayList<String> pokemonNameList;
    private ArrayList<Player> playerList;

    private PokemonDAO pokemonDAO;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){
        View v = inflater.inflate(R.layout.link_pokemon_fragment, container, false);

        pokemonDAO = new PokemonDAO(v.getContext());

        pokemonNameList = pokemonDAO.getAllPokemonNamesFromDb();
        playerList = PlayerManager.getInstance().getPlayerList();

        Spinner spinner = v.findViewById(R.id.pokemonSearch);
        Spinner caughtBySpinner = v.findViewById(R.id.caughtBy);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, pokemonNameList);
        ArrayAdapter<Player> caughtByAdapter = new ArrayAdapter<Player>(v.getContext(),
                android.R.layout.simple_list_item_1, playerList);

        spinner.setAdapter(adapter);

        caughtBySpinner.setAdapter(caughtByAdapter);

        pokemonSprite = v.findViewById(R.id.pokemonImage);
        pokemonType = v.findViewById(R.id.pokemonType);
        pokemonNickname = v.findViewById(R.id.pokemonNickname);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pokemonName = pokemonNameList.get(i);
                ArrayList<String> types = pokemonDAO.getPokemonTypesDb(pokemonNameList.get(i).toLowerCase());
                sprite = pokemonDAO.getPokemonSpriteFromDb(pokemonNameList.get(i).toLowerCase());
                pokemonType.setText(types.get(0));
                if(types.size() > 1){
                    pokemonType.setText(types.get(0)+" / "+types.get(1));
                }

                Glide.with(view.getContext())
                        .load(sprite)
                        .into(pokemonSprite);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        caughtBySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                caughtBy = playerList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return v;
    }

    public String getPokemonName() {
        return this.pokemonName;
    }

    public ArrayList<String> getPokemonType(){
        ArrayList<String> types = new ArrayList<>();
        String[] typeString = pokemonType.getText().toString().split("/");
        types.add(typeString[0].trim());
        if(types.size() > 1){
            types.add(typeString[1].trim());
        }

        return types;
    }

    public Player getCaughtBy(){
        return this.caughtBy;
    }

    public String getPokemonSprite(){
        return this.sprite;
    }

    public String getPokemonNickname(){
        return pokemonNickname.getText().toString();
    }

    public boolean isNickNameEmpty(){
        return pokemonNickname.getText().toString().isEmpty();
    }
}
