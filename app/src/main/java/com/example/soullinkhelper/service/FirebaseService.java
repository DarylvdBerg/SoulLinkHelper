package com.example.soullinkhelper.service;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.soullinkhelper.models.Game;
import com.example.soullinkhelper.models.Pair;
import com.example.soullinkhelper.models.Player;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FirebaseService {

    private static FirebaseService firebaseService;
    private Game game;
    private ArrayList<Game> games;

    public static FirebaseService getFirebaseServiceInstance(){
        if (firebaseService == null){
            firebaseService = new FirebaseService();
        }
        return firebaseService;
    }

    public void saveGame(Game game){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference gameRef = database.getReference("Games");
        gameRef.child(game.getGameId()).setValue(game);
    }

    public Game getGame(String gameID){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference gameRef = database.getReference("Games");
        gameRef.child(gameID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Game game = makeGame(dataSnapshot);
                setGame(game);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Wat moet hier???
            }
        });
        return this.game;
    }

    public ArrayList<Game> getGames() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference gameRef = database.getReference("Games");
        Log.d("COOL", gameRef.toString());
        gameRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("COOL", dataSnapshot.toString());
                ArrayList<Game> games = new ArrayList<>();
                for (DataSnapshot dataSnapshotGame : dataSnapshot.getChildren()){
                    Game game = makeGame(dataSnapshotGame);
                    games.add(game);
                }
                setGames(games);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("COOL", "PLS NO CANCEL");
                //Wat moet hier???
            }
        });
        return this.games;
    }

    public void savePair(String gameName, Pair pair, int pairsListSize){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference gameRef = database.getReference("Games");
        gameRef.child(gameName).child("pairs").child(Integer.toString(pairsListSize-1)).setValue(pair);
    }

    public void savePairs(String gameName, ArrayList<Pair> pairs){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference gameRef = database.getReference("Games");
        for (int i = 0; i < pairs.size(); i++){
            gameRef.child(gameName).child("pairs").child(Integer.toString(i)).setValue(pairs.get(i));
        }
    }

    private Game makeGame(DataSnapshot dataSnapshot){
        Game game;

        String name = dataSnapshot.child("name").getValue().toString();
        String region = dataSnapshot.child("region").getValue().toString();

        String namePlayerOne = dataSnapshot.child("playerOne").child("name").getValue().toString();
        String namePlayerTwo = dataSnapshot.child("playerTwo").child("name").getValue().toString();
        Player playerOne = new Player(namePlayerOne);
        Player playerTwo = new Player(namePlayerTwo);

        game = new Game(name, region, playerOne, playerTwo);

        ArrayList<Pair> pairs = (ArrayList<Pair>)dataSnapshot.child("pairs").getValue();

        game.setPairs(pairs);

        return game;
    }

    private void setGames(ArrayList<Game> games){
        this.games = games;
    }

    private void setGame(Game game){
        this.game = game;
        Log.d("TESTCOOL", "setGame: " + game.getName() + game.getPairs().toString());
    }
}
