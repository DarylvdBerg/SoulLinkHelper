package com.example.soullinkhelper.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static SQLiteDatabase mSQLDB;
    private static DatabaseHelper mInstance;
    public static final String dbName = "pokemon.db";
    public static final int dbVersion = 1;

    public DatabaseHelper(Context ctx){
        super(ctx, dbName, null, dbVersion);
    }

    public static synchronized DatabaseHelper getHelper(Context ctx){
        if(mInstance == null){
            mInstance = new DatabaseHelper(ctx);
            mSQLDB = mInstance.getWritableDatabase();
        }
        return mInstance;
    }

    public void insert(String table, String nullColumnHack, ContentValues values){
        mSQLDB.insert(table, nullColumnHack, values);
    }

    public Cursor query(String table, String[] columns, String selection,
                        String[] selectArgs, String groupBy, String having, String orderBy){
        return mSQLDB.query(table, columns, selection, selectArgs, groupBy, having, orderBy);
    }

    public boolean checkIfDbEmpty(int pokemonAmount){
        String count = "SELECT COUNT(*) FROM "+DatabaseInfo.PokemonTable.POKEMON_TABLE ;
        Cursor c = mSQLDB.rawQuery(count, null);
        c.moveToFirst();
        int counter = c.getInt(0);
        c.close();
        return counter < pokemonAmount;
    }

    public void clearDatabase(){
        mSQLDB.execSQL("DELETE FROM "+DatabaseInfo.PokemonTable.POKEMON_TABLE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+DatabaseInfo.PokemonTable.POKEMON_TABLE +" ("+
                BaseColumns._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                DatabaseInfo.PokemonColumn.NAME+" TEXT,"+
                DatabaseInfo.PokemonColumn.TYPE_1+" TEXT,"+
                DatabaseInfo.PokemonColumn.TYPE_2+" TEXT,"+
                DatabaseInfo.PokemonColumn.SPRITE+" TEXT);");

        db.execSQL("CREATE TABLE "+DatabaseInfo.GameTable.GAME_TABLE+"("+
                BaseColumns._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                DatabaseInfo.GameColumn.GAME_ID+" TEXT," +
                DatabaseInfo.GameColumn.GAME_NAME+" TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+DatabaseInfo.PokemonTable.POKEMON_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+DatabaseInfo.GameTable.GAME_TABLE);
        onCreate(db);

    }
}
