package com.example.soullinkhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.soullinkhelper.database.DatabaseHelper;
import com.example.soullinkhelper.service.PokemonService;


public class SplashScreen extends AppCompatActivity {

    private TextView loadText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashScreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        loadText = findViewById(R.id.waitText);

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
        Intent homeIntent = new Intent(SplashScreen.this, MainActivity.class);
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
            service.savePokemons(getResources().getInteger(R.integer.pokemon_api_call_count));
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            loadText.setVisibility(View.GONE);
            switchIntent();
        }

    }
}
