package com.example.soullinkhelper.models;

import android.widget.ImageView;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;

public class Pokemon {

    private String name;
    private ArrayList<String> types;
    private String nickname;
    private String sprite;

    public Pokemon(String name, ArrayList<String> types, String nickname, String sprite){
        this.name = name;
        this.types = types;
        this.nickname = nickname;
        this.sprite = sprite;
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

    public void giveNickname(String nickname){
        this.nickname = nickname;
    }
}
