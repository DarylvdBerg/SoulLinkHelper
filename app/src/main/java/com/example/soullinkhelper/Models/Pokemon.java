package com.example.soullinkhelper.Models;

import android.widget.ImageView;

import java.util.ArrayList;

public class Pokemon {

    private int id;
    private String name;
    private ArrayList<String> types;
    private String nickname;
    private ImageView sprite;

    public Pokemon(int id, String name){
        this.id = id;
        this.name = name;

        /*
        types from JSON file
        sprite from JSON file
         */
    }

    public void giveNickname(String nickname){
        this.nickname = nickname;
    }
}
