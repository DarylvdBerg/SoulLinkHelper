package com.example.soullinkhelper.service;

import android.content.Context;
import android.database.Cursor;
import android.widget.ProgressBar;
import android.database.Cursor;
import android.util.Log;

import android.view.View;
import android.widget.ProgressBar;


import com.example.soullinkhelper.dao.PokemonDAO;
import com.example.soullinkhelper.database.DatabaseHelper;
import com.example.soullinkhelper.database.DatabaseInfo;

import java.util.ArrayList;
import java.util.List;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonType;

/**
 * PokemonService class for getting and saving pokemons from SQLite DB and making API calls
 * @author Daryl
 */
public class PokemonService {
    private DatabaseHelper helper;
    private PokeApi api;
    private PokemonDAO pokemonDAO;
    private Context ctx;

    public PokemonService(Context ctx) {
        this.ctx = ctx;
        helper = DatabaseHelper.getHelper(ctx);
        api = new PokeApiClient();
        pokemonDAO = new PokemonDAO(ctx);
    }

    /**
     * Save pokemons
     * @param pokemons entry of pokemon according to the pokedex
     * @author Daryl
     */
    public void savePokemons(int pokemons, ProgressBar pBar){
        for(int i = 1; i <= pokemons; i ++){
            Pokemon pokemon = api.getPokemon(i);
            String pokemonName = pokemon.getName();
            List<PokemonType> types = pokemon.getTypes();
            String sprites = pokemon.getSprites().getFrontDefault();

            pokemonDAO.writePokemonToDb(pokemonName, types, sprites);
            pBar.setProgress(i);
        }
    }

    public ArrayList<String> getAllPokemonNamesFromDb(){
        String name;
        ArrayList<String> pokemonNameList = new ArrayList<>();
        DatabaseHelper db = DatabaseHelper.getHelper(ctx);
        Cursor c = db.query(DatabaseInfo.PokemonTable.POKEMON_TABLE,
                new String[]{"*"}, null, null, null, null, null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            name = c.getString(c.getColumnIndex("name"));
            String nameCapped = name.substring(0, 1).toUpperCase()+name.substring(1);
            pokemonNameList.add(nameCapped);

        }
        return pokemonNameList;
    }

    public ArrayList<String> getPokemonTypesDb(String pokemonName){
        ArrayList<String> types = new ArrayList<>();
        Cursor c = DatabaseHelper.getHelper(ctx).query(DatabaseInfo.PokemonTable.POKEMON_TABLE, new String[]{"type_1, type_2"},
                "name=?", new String[]{pokemonName}, null, null, null);
        if(c.moveToFirst()){
            types.add(c.getString(c.getColumnIndex("type_1")));
            if(c.getString(c.getColumnIndex("type_2")) != null){
                types.add(c.getString(c.getColumnIndex("type_2")));
            }
            return types;
        }
        return null;
    }

    public String getPokemonSpriteFromDb(String pokemonName){
        Cursor c = DatabaseHelper.getHelper(ctx).query(DatabaseInfo.PokemonTable.POKEMON_TABLE, new String[]{"sprite"},
                "name=?", new String[]{pokemonName}, null, null, null);
        if(c.moveToFirst()){
            return c.getString(c.getColumnIndex("sprite"));
        }
        return null;
    }

    /**
     * Write pokemon data to SQLite
     * Getting certain data from the API writing them to our SQLite db
     * @param name of Pokemon
     * @param types of Pokemon
     * @param sprite of Pokemon
     * @author Daryl
     */
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
