package com.example.soullinkhelper.models;

import java.util.ArrayList;

public class GameManager {
    private ArrayList<Game> gameList;
    private static GameManager mInstance;
    private String gameID;

    private GameManager(){
        gameList = new ArrayList<>();
    }

    public static GameManager getInstance(){
        if(mInstance == null){
            mInstance = new GameManager();
        }
        return mInstance;
    }

    public ArrayList<Game> getGameList(){
        return this.gameList;
    }

    public void setGameList(ArrayList<Game> gameList) {
        this.gameList = gameList;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public String getGameID() {
        return gameID;
    }
}
