package com.example.soullinkhelper;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.soullinkhelper.fragments.LinkPokemonFragment;
import com.example.soullinkhelper.models.Pair;
import com.example.soullinkhelper.models.PairManager;
import com.example.soullinkhelper.models.Pokemon;
import com.example.soullinkhelper.utils.ToastMaker;

import java.util.ArrayList;

public class LinkPokemon extends AppCompatActivity {

    private LinkPokemonFragment fragment_1;
    private LinkPokemonFragment fragment_2;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_link_pokemon);

        fragment_1 = new LinkPokemonFragment();
        fragment_2 = new LinkPokemonFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fragment_1, fragment_1);
        manager.beginTransaction()
                .replace(R.id.fragment_2, fragment_2);
        spinner = findViewById(R.id.routeSelector);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.kantoRoutes));

        spinner.setAdapter(adapter);

    }

    private void createNewPair(){
        if(!fragment_1.isNickNameEmpty() && !fragment_2.isNickNameEmpty()){
            Pokemon p1 = new Pokemon(
                    fragment_1.getPokemonName(),
                    fragment_1.getPokemonType(),
                    fragment_1.getPokemonNickname()
                    );
            Pokemon p2 = new Pokemon(
                    fragment_2.getPokemonName(),
                    fragment_2.getPokemonType(),
                    fragment_2.getPokemonNickname()
            );

            linkNewPair(p1, p2, String.valueOf(spinner.getSelectedItemId()));
        } else {
            ToastMaker.makeToast(this, "Please fill in the nickname for the pokemons!", 0);
        }
    }

    private void linkNewPair(Pokemon pk1, Pokemon pk2, String route){
        Pair pair = new Pair(pk1, pk2, route);
        PairManager.getInstance().addPair(pair);
    }
}
