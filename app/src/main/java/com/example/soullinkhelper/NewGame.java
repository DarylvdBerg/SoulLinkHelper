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
        ImageView playerSpriteOne = ((ImageView)findViewById(R.id.characterSpritePlayerOne));
        ImageView playerSpriteTwo = ((ImageView)findViewById(R.id.characterSpritePlayerTwo));
        game = new Game(gameName, region, playerNameOne, playerNameTwo, playerSpriteOne, playerSpriteTwo);
        game.setGameId(RandomStringBuilder.randomString(32));
        gameDao.writeGameToDb(game.getGameId());
        FirebaseService.getFirebaseServiceInstance().saveGame(game);
    }

    //Test pair, remove this
    public void makePair(){
        Pokemon pokemon = new Pokemon("Charmander");
        Pair pair = new Pair(pokemon, pokemon, "Route 1");
        game.addPair(pair);
        FirebaseService.getFirebaseServiceInstance().savePair(game.getName(), game.getPairs());
    }

}
