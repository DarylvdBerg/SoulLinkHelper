package com.example.soullinkhelper.models;

import android.widget.ImageView;

import java.util.ArrayList;

public class Game {
    private String gameId;
    private String name;
    private String region;
    private ArrayList<Pair> pairs;
    private Player playerOne;
    private Player playerTwo;

    public Game(String name, String region, String namePlayerOne, String namePlayerTwo, ImageView spritePlayerOne, ImageView spritePlayerTwo){
        this.name = name;
        this.region = region;
        this.pairs = new ArrayList<>();
        playerOne = new Player(namePlayerOne, spritePlayerOne);
        playerTwo = new Player(namePlayerTwo, spritePlayerTwo);
        pairs = new ArrayList<>();
    }

    public String getGameId(){
        return this.gameId;
    }

    public void setGameId(String gameId){
        this.gameId = gameId;
    }

    public void addPair(Pair pair){
        pairs.add(pair);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Pair> getPairs() {
        return pairs;
    }

    public String getRegion() {
        return region;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }
}
