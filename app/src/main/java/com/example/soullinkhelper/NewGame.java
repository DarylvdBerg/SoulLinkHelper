package com.example.soullinkhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.soullinkhelper.dao.GameDAO;
import com.example.soullinkhelper.models.Game;
import com.example.soullinkhelper.models.Pair;
import com.example.soullinkhelper.models.Player;
import com.example.soullinkhelper.models.Pokemon;
import com.example.soullinkhelper.service.FirebaseService;
import com.example.soullinkhelper.utility.RandomStringBuilder;

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

    public void switchSprite(View view){
        ImageView imageView = (ImageView)view;
        switch (imageView.getTag().toString()){
            case "characterSpritePlayerOne":
                imageView.setImageResource(R.mipmap.trainer_sprite_2);
                imageView.setTag("characterSpritePlayerTwo");
                break;
            case "characterSpritePlayerTwo":
                imageView.setImageResource(R.mipmap.trainer_sprite_1);
                imageView.setTag("characterSpritePlayerOne");
                break;
        }
    }

    public void makeGame(View view){
        String gameName = ((EditText)findViewById(R.id.gameNameEditText)).getText().toString();
        String region = ((Spinner)findViewById(R.id.regionSpinner)).getSelectedItem().toString();
        String playerNameOne = ((EditText)findViewById(R.id.namePlayerOne)).getText().toString();
        String playerNameTwo = ((EditText)findViewById(R.id.namePlayerTwo)).getText().toString();

        Player playerOne = new Player(playerNameOne, null);
        Player playerTwo = new Player(playerNameTwo, null);
        game = new Game(gameName, region, playerOne, playerTwo);
        game.setGameId(RandomStringBuilder.randomString(32));
        gameDao.writeGameToDb(game);
        FirebaseService.getFirebaseServiceInstance().saveGame(game);
    }
}
