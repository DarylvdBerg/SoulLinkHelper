package com.example.soullinkhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.soullinkhelper.adapter.LinkAdapter;
import com.example.soullinkhelper.models.GameManager;
import com.example.soullinkhelper.models.PairManager;
import com.example.soullinkhelper.models.PlayerManager;
import com.example.soullinkhelper.service.FirebaseService;

public class MainActivity extends AppCompatActivity {

    private PlayerManager playerManager;
    private RecyclerView.Adapter mAdapter;
    private TextView noLinksFound;
    private PairManager pairManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashScreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPlayersFromFirebase();
        pairManager = PairManager.getInstance();

        Log.i("Player size", PlayerManager.getInstance().getPlayerList().size()+"");
        Log.i("Pair size", pairManager.getPairList().size()+"");

        ImageView linkPokemonBtn = findViewById(R.id.linkPokemonBtn);
        linkPokemonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linkPokemonActivity();
            }
        });

        RecyclerView rView = findViewById(R.id.links);
//        noLinksFound = findViewById(R.id.noLinksFound);

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
//        setListTextVisibility();
    }

    private void linkPokemonActivity(){
        startActivity(new Intent(this, LinkPokemon.class));
    }

    private void setListTextVisibility(){
        noLinksFound.setVisibility(pairManager.getPairList().size() < 1 ?
                View.VISIBLE :
                View.GONE);
    }

    private void getPlayersFromFirebase(){
        FirebaseService.getFirebaseServiceInstance().playerList(GameManager.getInstance().getGameID());
    }

    private void getPairsFromFirebase(RecyclerView.Adapter adapter){
        FirebaseService.getFirebaseServiceInstance().getPairs(GameManager.getInstance().getGameID(), adapter);
    }
}
