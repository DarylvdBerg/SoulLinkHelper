package com.example.soullinkhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soullinkhelper.models.GameManager;
import com.example.soullinkhelper.utils.ToastMaker;
import com.google.android.material.button.MaterialButton;

public class GameDetails extends AppCompatActivity {
    private ClipboardManager clipboardManager;
    private ClipData clipData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.GameDetails);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        Log.i("GameName", GameManager.getInstance().getGameName());

        TextView detailsGameName = findViewById(R.id.detailsGameName);
        detailsGameName.setText(GameManager.getInstance().getGameName());

        MaterialButton gameIdBtn = findViewById(R.id.copy_game_id);
        gameIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                clipData = ClipData.newPlainText("gameId", GameManager.getInstance().getGameID());
                clipboardManager.setPrimaryClip(clipData);
                ToastMaker.makeToast(getApplicationContext(), "Game ID has beed copied to clipboard", Toast.LENGTH_SHORT);
            }
        });
    }
}
