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
        values.put(DatabaseInfo.GameColumn.GAME_ID, game.getGameId());
        values.put(DatabaseInfo.GameColumn.GAME_NAME, game.getName().toLowerCase());
        db.insert(DatabaseInfo.GameTable.GAME_TABLE, null, values);
        GameManager.getInstance().setGameName(game.getName());
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

    public String getGameIdByName(String gameName){
        Cursor c = DatabaseHelper.getHelper(context).query(DatabaseInfo.GameTable.GAME_TABLE, new String[]{DatabaseInfo.GameColumn.GAME_ID},
                DatabaseInfo.GameColumn.GAME_NAME + "=?", new String[]{gameName}, null, null, null);
        if(c.moveToFirst()){
            return c.getString(c.getColumnIndex(DatabaseInfo.GameColumn.GAME_ID));
        }
        return null;
    }
}
