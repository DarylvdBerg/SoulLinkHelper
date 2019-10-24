package com.example.soullinkhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.soullinkhelper.dao.GameDAO;
import com.example.soullinkhelper.models.Game;
import com.example.soullinkhelper.models.GameManager;
import com.example.soullinkhelper.models.Player;
import com.example.soullinkhelper.service.FirebaseService;
import com.example.soullinkhelper.utils.RandomStringBuilder;

public class NewGame extends AppCompatActivity {

    private int maxSprites = 4;
    private GameDAO gameDao;

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.NewGameScreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        gameDao = new GameDAO(this);
    }

    public void makeGame(View view){
        String gameName = ((EditText)findViewById(R.id.gameNameEditText)).getText().toString();
        String region = ((Spinner)findViewById(R.id.regionSpinner)).getSelectedItem().toString();
        String playerNameOne = ((EditText)findViewById(R.id.namePlayerOne)).getText().toString();
        String playerNameTwo = ((EditText)findViewById(R.id.namePlayerTwo)).getText().toString();

        Player playerOne = new Player(playerNameOne, null);
        Player playerTwo = new Player(playerNameTwo, null);
        game = new Game(gameName, region, playerOne, playerTwo);
        String gameID = RandomStringBuilder.randomString(32);
        game.setGameId(gameID);
        GameManager.getInstance().setGameID(gameID);
        gameDao.writeGameToDb(game);
        FirebaseService.getFirebaseServiceInstance().saveGame(game);

        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.game_id), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.game_id), gameID);
        editor.commit();

        switchIntent(gameID);
    }

    private void switchIntent(String gameID){
        GameManager.getInstance().setGameID(gameID);
        Intent mainActivityIntent = new Intent(NewGame.this, MainActivity.class);
        startActivity(mainActivityIntent);
    }
}
