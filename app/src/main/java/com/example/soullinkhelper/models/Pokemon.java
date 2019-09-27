package com.example.soullinkhelper.models;

import android.widget.ImageView;

import java.util.ArrayList;

public class Pokemon {

    private String name;
    private ArrayList<String> types;
    private String nickname;
    private ImageView sprite;

    public Pokemon(String name, ArrayList<String> types, String nickname){
        this.name = name;
        this.types = types;
        this.nickname = nickname;
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<String> getTypes(){
        return this.types;
    }

    public void giveNickname(String nickname){
        this.nickname = nickname;
    }
}
