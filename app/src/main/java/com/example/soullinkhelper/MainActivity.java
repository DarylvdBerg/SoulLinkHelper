package com.example.soullinkhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.soullinkhelper.models.PairManager;
import com.example.soullinkhelper.models.PlayerManager;

public class MainActivity extends AppCompatActivity {

    // TODO: Get correct games
    // TODO: Show all linked pokemon
    // TODO: Show Player name

    private ImageView linkPokemonBtn;
    private TextView noLinksFound;
    private PairManager pairManager;
    private PlayerManager playerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.NewGameScreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pairManager = PairManager.getInstance();

        linkPokemonBtn = findViewById(R.id.linkPokemonBtn);
        linkPokemonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linkPokemonActivity();
            }
        });

        noLinksFound = findViewById(R.id.noLinksFound);
        noLinksFound.setVisibility(pairManager.getPairList().size() < 1 ?
                View.VISIBLE :
                View.GONE);
    }

    private void linkPokemonActivity(){
        startActivity(new Intent(this, LinkPokemon.class));
    }
}
