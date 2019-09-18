package com.example.soullinkhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashScreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    @Override
    public void onStart(){
        super.onStart();
        Intent gameManager = new Intent(SplashScreen.this, GameManager.class);
        startActivity(gameManager);
        finish();
    }
}
