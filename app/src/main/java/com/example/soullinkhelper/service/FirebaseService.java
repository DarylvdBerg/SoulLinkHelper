package com.example.soullinkhelper.service;

import android.util.Log;

import com.example.soullinkhelper.models.Game;
import com.example.soullinkhelper.models.Pair;
import com.example.soullinkhelper.utility.RandomStringBuilder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class FirebaseService {

    private static FirebaseService firebaseService;

    public static FirebaseService getFirebaseServiceInstance(){
        if (firebaseService == null){
            firebaseService = new FirebaseService();
        }
        return firebaseService;
    }

    public void saveGame(Game game){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference gameRef = database.getReference(RandomStringBuilder.randomString(32));
        gameRef.setValue(game);
    }

    public void getGame(){

    }

    public void savePair(String gameName, ArrayList<Pair> pairs){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference gameRef = database.getReference(gameName);
        gameRef.child("pairs").child(Integer.toString(pairs.size()-1)).setValue(pairs.get(pairs.size()-1));
    }

    public void getPairs() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

    }
}
