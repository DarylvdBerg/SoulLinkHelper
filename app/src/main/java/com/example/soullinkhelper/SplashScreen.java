package com.example.soullinkhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.soullinkhelper.database.DatabaseHelper;
import com.example.soullinkhelper.models.Game;
import com.example.soullinkhelper.models.Pair;
import com.example.soullinkhelper.models.Pokemon;
import com.example.soullinkhelper.service.FirebaseService;
import com.example.soullinkhelper.service.PokemonService;

import java.util.ArrayList;


public class SplashScreen extends AppCompatActivity {

    private TextView loadText;
    private ImageView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashScreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //REMOVE THIS
        Pokemon pokemon = new Pokemon("Pikachu");
        Pair pair = new Pair(pokemon, pokemon, "Route 1");
        ArrayList<Pair> pairs = new ArrayList<>();
        pairs.add(pair);
        Pair newPair = new Pair(pokemon, pokemon, "Route 2");
        pairs.add(newPair);
        FirebaseService.getFirebaseServiceInstance().savePairs("NUHAGT31FHRYN5GZXX4YEVIC5JRNTA3N", pairs);
        Game tmp = FirebaseService.getFirebaseServiceInstance().getGame("NUHAGT31FHRYN5GZXX4YEVIC5JRNTA3N");
//        Log.d("TestGetGame", tmp.toString());
//        ArrayList<Game> gamesTmp = FirebaseService.getFirebaseServiceInstance().getGames();
//        for (Game gameTmp : gamesTmp){
//            Log.d("AllGames", gamesTmp.toString());
//        }
        //

        loadText = findViewById(R.id.waitText);
        loading = findViewById(R.id.loading);

        Glide.with(this)
                .load(R.mipmap.loading_screen_logo)
                .into(loading);

        DatabaseHelper helper = DatabaseHelper.getHelper(this);
        getPokemonFromApi task = new getPokemonFromApi(this);


        if(helper.checkIfDbEmpty(getResources().getInteger(R.integer.pokemon_api_call_count))){
            helper.clearDatabase();
            task.execute();
        } else {
            switchIntent();
        }
    }

    private void switchIntent(){
        Intent homeIntent = new Intent(SplashScreen.this, GameManager.class);
        startActivity(homeIntent);
        finish();
    }

    private class getPokemonFromApi extends AsyncTask<Void, Void, Void> {

        private Context ctx;
        private PokemonService service;

        private getPokemonFromApi(Context ctx){
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute(){
            loadText.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            service = new PokemonService(ctx);
            service.savePokemons(getResources().getInteger(R.integer.pokemon_api_call_count), (ProgressBar)findViewById(R.id.progressBarSplashScreen));
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            loadText.setVisibility(View.GONE);
            loading.setVisibility(View.GONE);
            switchIntent();
        }

    }
}
