package com.example.soullinkhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soullinkhelper.models.GameManager;
import com.example.soullinkhelper.models.PairManager;
import com.example.soullinkhelper.utils.ToastMaker;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class GameDetails extends AppCompatActivity {
    private ClipboardManager clipboardManager;
    private ClipData clipData;

    private PieChart pairChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.GameDetails);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        setupPairPieChart();
        setData();

//        Log.i("GameName", GameManager.getInstance().getGameName());

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

    protected void setupPairPieChart(){
        pairChart = (PieChart) findViewById(R.id.chart);
        Description description = new Description();
        description.setText("Resterende Pairs");
        description.setTextSize(20f);
        pairChart.setDescription(description);
        pairChart.setTouchEnabled(false);
        pairChart.setDrawSliceText(true);
        pairChart.getLegend().setEnabled(false);
        pairChart.setTransparentCircleColor(Color.rgb(130, 130, 130));
        pairChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
    }

    private void setData(){
        int aantalPairs = PairManager.getInstance().getPairList().size();
        int maxPairs = getResources().getStringArray(R.array.kantoRoutes).length;

        List<PieEntry> yValues = new ArrayList<>();
        List<PieEntry> xValues = new ArrayList<>();

        yValues.add(new PieEntry(aantalPairs, 0));
        xValues.add(new PieEntry(aantalPairs, "Aantal Pairs"));

        yValues.add(new PieEntry(maxPairs - aantalPairs, 1));
        xValues.add(new PieEntry(maxPairs - aantalPairs, "Resterende pairs"));

        ArrayList<Integer> colors = new ArrayList<>();
        if (aantalPairs <10) {
            colors.add(Color.rgb(255,68,77));
        } else if (aantalPairs < 20){
            colors.add(Color.rgb(255,100,100));
        } else if  (aantalPairs < 40) {
            colors.add(Color.rgb(255,150,150));
        } else {
            colors.add(Color.rgb(67,160,71));
        }
        colors.add(Color.rgb(50,93,255));

        PieDataSet dataSet = new PieDataSet(yValues, "Pairs");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        pairChart.setData(data);
        pairChart.invalidate();
    }
}
