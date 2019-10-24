package com.example.soullinkhelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soullinkhelper.adapter.LinkAdapter;
import com.example.soullinkhelper.fragments.BottomMenu;
import com.example.soullinkhelper.models.GameManager;
import com.example.soullinkhelper.models.PairManager;
import com.example.soullinkhelper.models.PlayerManager;
import com.example.soullinkhelper.service.FirebaseService;
import com.example.soullinkhelper.utils.ToastMaker;

public class MainActivity extends AppCompatActivity{

    private PlayerManager playerManager;
    private RecyclerView.Adapter mAdapter;
    private PairManager pairManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.LinksScreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPlayersFromFirebase();
        pairManager = PairManager.getInstance();

        ImageView linkPokemonBtn = findViewById(R.id.linkPokemonBtn);
        linkPokemonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomMenu menu = new BottomMenu();
                menu.show(getSupportFragmentManager(), "Menu");

            }
        });

        final RecyclerView rView = findViewById(R.id.links);

        rView.setHasFixedSize(true);
        RecyclerView.LayoutManager lManager = new LinearLayoutManager(this);
        mAdapter = new LinkAdapter(PairManager.getInstance().getPairList());
        rView.setLayoutManager(lManager);
        rView.setAdapter(mAdapter);

        getPairsFromFirebase(mAdapter);


    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    private void getPlayersFromFirebase(){
        FirebaseService.getFirebaseServiceInstance().playerList(GameManager.getInstance().getGameID());
    }

    private void getPairsFromFirebase(RecyclerView.Adapter adapter){
        FirebaseService.getFirebaseServiceInstance().getPairs(GameManager.getInstance().getGameID(), adapter);
    }
}
