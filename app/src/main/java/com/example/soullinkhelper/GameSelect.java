package com.example.soullinkhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.soullinkhelper.models.GameManager;
import com.example.soullinkhelper.utils.ToastMaker;

public class GameSelect extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.LinksScreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select);

        sharedPreferences = this.getSharedPreferences(getString(R.string.game_id), Context.MODE_PRIVATE);
        GameManager.getInstance().setGameID(sharedPreferences.getString(getString(R.string.game_id), ""));
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
        final Button loadGameButton = findViewById(R.id.buttonLoadGame);
        loadGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoadGameIntent = new Intent(GameSelect.this, LoadGame.class);
                startActivity(LoadGameIntent);
            }
        });
        Button continueGame = findViewById(R.id.buttonContinue);
        continueGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!sharedPreferences.getString(getString(R.string.game_id), "").equals("")){
                    Intent mainActivityIntent = new Intent(GameSelect.this, MainActivity.class);
                    mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainActivityIntent);
                    finish();
                } else {
                    ToastMaker.makeToast(view.getContext(), "There is no previous game!", 0);
                }
            }
        });
    }
}
