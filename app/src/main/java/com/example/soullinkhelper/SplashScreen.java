package com.example.soullinkhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.soullinkhelper.database.DatabaseHelper;
import com.example.soullinkhelper.service.PokemonService;

import java.io.File;

public class SplashScreen extends AppCompatActivity {

    private ProgressDialog p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashScreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
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

        public getPokemonFromApi(Context ctx){
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute(){
            // TODO: Show loading animation with text
            super.onPreExecute();
            p = new ProgressDialog(SplashScreen.this);
            p.setMessage("Please wait...It is downloading");
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();
            Log.i("Processing", "Getting the pokemans");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            service = new PokemonService(ctx);
            service.savePokemons(getResources().getInteger(R.integer.pokemon_api_call_count));
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            p.hide();
            switchIntent();
        }

    }
}
