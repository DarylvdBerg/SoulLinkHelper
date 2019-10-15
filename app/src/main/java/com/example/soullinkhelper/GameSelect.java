package com.example.soullinkhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.GameManagerScreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_manager);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Button newGameButton = findViewById(R.id.buttonNewGame);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newGameIntent = new Intent(GameSelect.this, NewGame.class);
                startActivity(newGameIntent);
            }
        });
        Button loadGameButton = findViewById(R.id.buttonLoadGame);
        loadGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivityIntent = new Intent(GameSelect.this, LoadGame.class);
                startActivity(mainActivityIntent);
            }
        });
    }
}