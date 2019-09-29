package com.example.soullinkhelper.dao;

import android.content.ContentValues;
import android.content.Context;

import com.example.soullinkhelper.database.DatabaseHelper;
import com.example.soullinkhelper.database.DatabaseInfo;

public class GameDAO {
    private Context context;
    private DatabaseHelper db;

    public GameDAO(Context ctx){
        this.context = ctx;
        db = DatabaseHelper.getHelper(context);
    }

    public void writeGameToDb(String gameId){
        ContentValues values = new ContentValues();
        values.put("game_id", gameId);
        db.insert(DatabaseInfo.GameTable.GAME_TABLE, null, values);
    }
}
