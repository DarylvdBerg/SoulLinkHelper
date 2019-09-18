package com.example.soullinkhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.soullinkhelper.Models.Game;

public class NewGame extends AppCompatActivity {

    private int maxSprites = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.NewGameScreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
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
        String gameName = findViewById(R.id.gameNameEditText).toString();
        String region = ((Spinner)findViewById(R.id.regionSpinner)).getSelectedItem().toString();
        String playerNameOne = findViewById(R.id.namePlayerOne).toString();
        String playerNameTwo = findViewById(R.id.namePlayerTwo).toString();
        ImageView spritePlayerOne = findViewById(R.id.characterSpritePlayerOne);
        ImageView spritePlayerTwo = findViewById(R.id.characterSpritePlayerTwo);
        Game game = new Game(gameName, region, playerNameOne, playerNameTwo, spritePlayerOne, spritePlayerTwo);
    }

}
