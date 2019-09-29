package com.example.soullinkhelper.models;

import android.widget.ImageView;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;

public class Pokemon {

    private String name;
    private ArrayList<String> types;
    private String nickname;
    private ImageView sprite;

    public Pokemon(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public String getNickname() {
        return nickname;
    }

    @Exclude
    public ImageView getSprite() {
        return sprite;
    }

    public void giveNickname(String nickname){
        this.nickname = nickname;
    }
}
