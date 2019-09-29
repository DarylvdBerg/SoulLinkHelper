package com.example.soullinkhelper.dao;

import android.content.ContentValues;
import android.content.Context;

import com.example.soullinkhelper.database.DatabaseHelper;
import com.example.soullinkhelper.database.DatabaseInfo;

import java.util.List;

import me.sargunvohra.lib.pokekotlin.model.PokemonType;

public class PokemonDAO {
    private DatabaseHelper db;
    private Context context;

    public PokemonDAO(Context context){
        this.context = context;
        db = DatabaseHelper.getHelper(context);
    }

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
}
