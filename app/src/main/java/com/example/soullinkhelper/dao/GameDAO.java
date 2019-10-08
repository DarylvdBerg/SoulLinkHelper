package com.example.soullinkhelper.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.soullinkhelper.database.DatabaseHelper;
import com.example.soullinkhelper.database.DatabaseInfo;
import com.example.soullinkhelper.models.Game;

import java.util.ArrayList;

public class GameDAO {
    private Context context;
    private DatabaseHelper db;

    public GameDAO(Context ctx){
        this.context = ctx;
        db = DatabaseHelper.getHelper(context);
    }

    public void writeGameToDb(Game game){
        ContentValues values = new ContentValues();
        values.put("game_id", game.getGameId());
        values.put("game_name", game.getName());
        db.insert(DatabaseInfo.GameTable.GAME_TABLE, null, values);
    }

    public ArrayList<String> getAllGameNamesFromDb(){
        String name;
        ArrayList<String> gameNameList = new ArrayList<>();
        DatabaseHelper db = DatabaseHelper.getHelper(context);
        Cursor c = db.query(DatabaseInfo.GameTable.GAME_TABLE,
                new String[]{"*"}, null, null, null, null, null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            name = c.getString(c.getColumnIndex(DatabaseInfo.GameColumn.GAME_NAME));
            String nameCapped = name.substring(0, 1).toUpperCase()+name.substring(1);
            gameNameList.add(nameCapped);

        }
        return gameNameList;
    }
}
