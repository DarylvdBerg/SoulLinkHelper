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
import com.example.soullinkhelper.service.PokemonService;

import java.util.ArrayList;

public class LinkPokemonFragment extends Fragment {

    private ImageView pokemonSprite;
    private TextView pokemonType;
    private Spinner spinner;
    private ArrayList<String> pokemonNameList;
    private String sprite;
    private EditText pokemonNickname;

    private PokemonDAO pokemonDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){
        View v = inflater.inflate(R.layout.link_pokemon_fragment, container, false);

        pokemonDAO = new PokemonDAO(v.getContext());

        pokemonNameList = pokemonDAO.getAllPokemonNamesFromDb();

        spinner = v.findViewById(R.id.pokemonSearch);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, pokemonNameList);

        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        pokemonSprite = v.findViewById(R.id.pokemonImage);
        pokemonType = v.findViewById(R.id.pokemonType);
        pokemonNickname = v.findViewById(R.id.pokemonNickname);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
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

        return v;
    }

    public String getPokemonName(){
        return String.valueOf(spinner.getSelectedItemId());
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

    public String getPokemonSpirte(){
        return this.sprite;
    }

    public String getPokemonNickname(){
        return pokemonNickname.getText().toString();
    }

    public boolean isNickNameEmpty(){
        return pokemonNickname.getText().toString().isEmpty();
    }
}
