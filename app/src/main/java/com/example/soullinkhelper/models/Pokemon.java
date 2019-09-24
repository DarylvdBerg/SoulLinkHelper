package com.example.soullinkhelper.models;

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
    }

    public String getName(){
        return this.name;
    }

    public void giveNickname(String nickname){
        this.nickname = nickname;
    }
}
