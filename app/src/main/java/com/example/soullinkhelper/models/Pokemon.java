package com.example.soullinkhelper.models;

import android.widget.ImageView;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;

public class Pokemon {

    private String name;
    private ArrayList<String> types;
    private String nickname;
    private String sprite;
    private Player caughtBy;

    public Pokemon(String name, ArrayList<String> types, String nickname, String sprite, Player caughtBy){
        this.name = name;
        this.types = types;
        this.nickname = nickname;
        this.sprite = sprite;
        this.caughtBy = caughtBy;
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

    public String getSprite(){
        return this.sprite;
    }

    public Player getCaughtBy(){
        return this.caughtBy;
    }

    public void giveNickname(String nickname){
        this.nickname = nickname;
    }
}
