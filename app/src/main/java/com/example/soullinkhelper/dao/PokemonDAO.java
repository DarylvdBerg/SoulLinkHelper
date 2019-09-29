package com.example.soullinkhelper.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.soullinkhelper.database.DatabaseHelper;
import com.example.soullinkhelper.database.DatabaseInfo;

import java.util.ArrayList;
import java.util.List;

import me.sargunvohra.lib.pokekotlin.model.PokemonType;

public class PokemonDAO {
    private DatabaseHelper db;
    private Context context;

    public PokemonDAO(Context context){
        this.context = context;
        db = DatabaseHelper.getHelper(context);
    }

    /**
     * Write pokemon data to SQLite
     * Getting certain data from the API writing them to our SQLite db
     * @param name of Pokemon
     * @param types of Pokemon
     * @param sprite of Pokemon
     * @author Daryl
     */
    public void writePokemonToDb(String name, List<PokemonType> types, String sprite){
        ContentValues values = new ContentValues();
        values.put(DatabaseInfo.PokemonColumn.NAME, name);
        if(types.size() > 1){
            values.put(DatabaseInfo.PokemonColumn.TYPE_1, types.get(0).getType().component1());
            values.put(DatabaseInfo.PokemonColumn.TYPE_2, types.get(1).getType().component1());
        } else {
            values.put(DatabaseInfo.PokemonColumn.TYPE_1, types.get(0).getType().component1());
        }
        values.put(DatabaseInfo.PokemonColumn.SPRITE, sprite);
        db.insert(DatabaseInfo.PokemonTable.POKEMON_TABLE, null, values);
    }

    public ArrayList<String> getAllPokemonNamesFromDb(){
        String name;
        ArrayList<String> pokemonNameList = new ArrayList<>();
        DatabaseHelper db = DatabaseHelper.getHelper(context);
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
        Cursor c = DatabaseHelper.getHelper(context).query(DatabaseInfo.PokemonTable.POKEMON_TABLE, new String[]{"type_1, type_2"},
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
        Cursor c = DatabaseHelper.getHelper(context).query(DatabaseInfo.PokemonTable.POKEMON_TABLE, new String[]{"sprite"},
                "name=?", new String[]{pokemonName}, null, null, null);
        if(c.moveToFirst()){
            return c.getString(c.getColumnIndex("sprite"));
        }
        return null;
    }
}
