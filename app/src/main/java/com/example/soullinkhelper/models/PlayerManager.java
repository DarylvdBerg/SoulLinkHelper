package com.example.soullinkhelper.models;

import java.util.ArrayList;

public class PlayerManager {
    private ArrayList<Player> playerList;
    private static PlayerManager mInstance;

    private PlayerManager(){
        playerList = new ArrayList<>();
    }

    public static PlayerManager getInstance(){
        if(mInstance == null){
            mInstance = new PlayerManager();
        }
        return mInstance;
    }

    public ArrayList<Player> getPlayerList(){
        return this.playerList;
    }

    public void addPlayer(Player player){
        this.playerList.add(player);
    }

    public void clearPlayerList(){
        playerList.clear();
    }

    public ArrayList<String> getPlayerNames(){
        ArrayList<String> playerNames = new ArrayList<>();
        for(Player p : playerList){
            playerNames.add(p.getName());
        }
        return playerNames;
    }

}
