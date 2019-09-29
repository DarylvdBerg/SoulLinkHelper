package com.example.soullinkhelper.models;

import android.widget.ImageView;

import com.google.firebase.database.Exclude;

public class Player {

    private String name;
    private ImageView sprite;

    public Player(String name, ImageView sprite){
        this.name = name;
        this.sprite = sprite;
    }

    public String getName() {
        return name;
    }

    @Exclude
    public ImageView getSprite() {
        return sprite;
    }
}
