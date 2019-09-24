package com.example.soullinkhelper.models;

import android.widget.ImageView;

import java.util.ArrayList;

public class Game {

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
        playerOne = new Player(namePlayerTwo, spritePlayerTwo);
    }

}
