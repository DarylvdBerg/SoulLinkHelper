package com.example.soullinkhelper.service;


import android.app.Application;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.soullinkhelper.dao.GameDAO;
import com.example.soullinkhelper.models.Game;
import com.example.soullinkhelper.models.GameManager;
import com.example.soullinkhelper.models.Pair;
import com.example.soullinkhelper.models.PairManager;
import com.example.soullinkhelper.models.Player;
import com.example.soullinkhelper.models.PlayerManager;
import com.example.soullinkhelper.models.Pokemon;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        DatabaseReference gameRef = database.getReference("Games");
        gameRef.child(game.getGameId()).setValue(game);
    }

    public void getGame(String gameID, final GameDAO gameDAO){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference gameRef = database.getReference("Games");
        gameRef.child(gameID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Game game = makeGame(dataSnapshot);
                gameDAO.writeGameToDb(game);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Wat moet hier???
            }
        });
    }

    public void playerList(String gameId){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference gameRef = database.getReference("Games");
        gameRef.child(gameId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Player playerOne = new Player(dataSnapshot.child("playerOne").child("name").getValue().toString(),
                        null);
                Player playerTwo = new Player(dataSnapshot.child("playerTwo").child("name").getValue().toString(),
                        null);

                PlayerManager.getInstance().addPlayer(playerOne);
                PlayerManager.getInstance().addPlayer(playerTwo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getPairs(String gameId){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference gameRef = database.getReference("Games");
        gameRef.child(gameId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getGames() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference gameRef = database.getReference("Games");
        Log.d("COOL", gameRef.toString());
        gameRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Game> games = new ArrayList<>();
                for (DataSnapshot dataSnapshotGame : dataSnapshot.getChildren()){
                    Game game = makeGame(dataSnapshotGame);
                    games.add(game);
                }
                GameManager.getInstance().setGameList(games);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Wat moet hier???
            }
        });
    }

    public void savePair(String gameName, Pair pair, int pairsListSize){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference gameRef = database.getReference("Games");
        gameRef.child(gameName).child("pairs").child(Integer.toString(pairsListSize-1)).setValue(pair);
    }

    public void savePlayerOne(String gameName, Player player){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference gameRef = database.getReference("Games");
        gameRef.child(gameName).child("playerOne").setValue(player);
    }

    public void savePlayerTwo(String gameName, Player player){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference gameRef = database.getReference("Games");
        gameRef.child(gameName).child("playerTwo").setValue(player);
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
        Player playerOne = new Player(namePlayerOne, null);
        Player playerTwo = new Player(namePlayerTwo, null);

        game = new Game(name, region, playerOne, playerTwo);

        ArrayList<Pair> pairs = (ArrayList<Pair>)dataSnapshot.child("pairs").getValue();

        game.setPairs(pairs);

        return game;
    }
}
