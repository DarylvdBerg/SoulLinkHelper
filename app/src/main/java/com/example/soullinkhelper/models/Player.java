package com.example.soullinkhelper.models;

import android.widget.ImageView;

import com.google.firebase.database.Exclude;

public class Player {

    private String name;

    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
