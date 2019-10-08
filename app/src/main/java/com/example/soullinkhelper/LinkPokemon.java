package com.example.soullinkhelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.soullinkhelper.fragments.LinkPokemonFragment;
import com.example.soullinkhelper.models.Pair;
import com.example.soullinkhelper.models.PairManager;
import com.example.soullinkhelper.models.Pokemon;
import com.example.soullinkhelper.service.FirebaseService;
import com.example.soullinkhelper.utils.ToastMaker;

public class LinkPokemon extends AppCompatActivity{

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

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_1, fragment_1);
        transaction.replace(R.id.fragment_2, fragment_2);
        transaction.commit();

        spinner = findViewById(R.id.routeSelector);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.kantoRoutes));

        spinner.setAdapter(adapter);

        Button savePairBtn = findViewById(R.id.linkPairBtn);
        savePairBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewPair();
            }
        });

    }

    private void createNewPair(){
        if(!fragment_1.isNickNameEmpty() && !fragment_2.isNickNameEmpty()){
            Pokemon p1 = new Pokemon(
                    fragment_1.getPokemonName(),
                    fragment_1.getPokemonType(),
                    fragment_1.getPokemonNickname(),
                    fragment_1.getPokemonSprite(),
                    fragment_1.getCaughtBy()
                    );
            Pokemon p2 = new Pokemon(
                    fragment_2.getPokemonName(),
                    fragment_2.getPokemonType(),
                    fragment_2.getPokemonNickname(),
                    fragment_2.getPokemonSprite(),
                    fragment_2.getCaughtBy()
            );

            linkNewPair(p1, p2, String.valueOf(spinner.getSelectedItem()));
        } else {
            ToastMaker.makeToast(this, "Please fill in the nickname for the pokemons!", 0);
        }
    }

    private void linkNewPair(Pokemon pk1, Pokemon pk2, String route){
        Pair pair = new Pair(pk1, pk2, route);
        FirebaseService.getFirebaseServiceInstance().savePair("NUHAGT31FHRYN5GZXX4YEVIC5JRNTA3N", pair, PairManager.getInstance().getPairList().size());
        startActivity(new Intent(this, MainActivity.class));
    }
}
