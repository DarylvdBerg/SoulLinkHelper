package com.example.soullinkhelper.service;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.example.soullinkhelper.database.DatabaseHelper;
import com.example.soullinkhelper.database.DatabaseInfo;
import java.util.List;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonType;

public class PokemonService {
    private DatabaseHelper helper;
    private PokeApi api;

    public PokemonService(Context ctx){
        helper = DatabaseHelper.getHelper(ctx);
        api = new PokeApiClient();
    }

    public void savePokemons(int pokemons, ProgressBar pBar){
        for(int i = 1; i <= pokemons; i ++){
            Pokemon pokemon = api.getPokemon(i);
            String pokemonName = pokemon.getName();
            List<PokemonType> types = pokemon.getTypes();
            String sprites = pokemon.getSprites().getFrontDefault();

            writePokemonToDb(pokemonName, types, sprites);
            pBar.setProgress(i);
        }
    }


    private void writePokemonToDb(String name, List<PokemonType> types, String sprite){
        ContentValues values = new ContentValues();
        values.put(DatabaseInfo.PokemonColumn.NAME, name);
        if(types.size() > 1){
            values.put(DatabaseInfo.PokemonColumn.TYPE_1, types.get(0).getType().component1());
            values.put(DatabaseInfo.PokemonColumn.TYPE_2, types.get(1).getType().component1());
        } else {
            values.put(DatabaseInfo.PokemonColumn.TYPE_1, types.get(0).getType().component1());
        }
        values.put(DatabaseInfo.PokemonColumn.SPRITE, sprite);
        helper.insert(DatabaseInfo.PokemonTable.POKEMON_TABLE, null, values);
    }
}
