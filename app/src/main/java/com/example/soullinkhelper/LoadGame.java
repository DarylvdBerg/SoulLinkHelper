package com.example.soullinkhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.soullinkhelper.dao.GameDAO;
import com.example.soullinkhelper.models.Game;
import com.example.soullinkhelper.models.GameManager;
import com.example.soullinkhelper.service.FirebaseService;

import java.util.ArrayList;

public class LoadGame extends AppCompatActivity {

    private GameDAO gameDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_game);
        gameDAO = new GameDAO(this);
    }

    @Override
    protected void onStart(){
        super.onStart();

        ArrayList<String> gameNames = gameDAO.getAllGameNamesFromDb();

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gameNames);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.spinnerAllGames);
        spinner.setAdapter(arrayAdapter);

        Button buttonStartGame = findViewById(R.id.buttonStartGame);
        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gameID = gameDAO.getGameIdByName(((Spinner)findViewById(R.id.spinnerAllGames)).getSelectedItem().toString());
                switchIntent(gameID);
            }
        });
        Button buttonAddGame = findViewById(R.id.buttonAddGame);
        buttonAddGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gameID = ((EditText)findViewById(R.id.editTextAddGame)).getText().toString();
                FirebaseService.getFirebaseServiceInstance().getGame(gameID, gameDAO);
                switchIntent(gameID);
            }
        });
    }
    private void switchIntent(String gameID){
        GameManager.getInstance().setGameID(gameID);
        Intent mainActivityIntent = new Intent(LoadGame.this, MainActivity.class);
        startActivity(mainActivityIntent);
    }
}
