package com.example.soullinkhelper.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soullinkhelper.LinkPokemon;
import com.example.soullinkhelper.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomMenu extends BottomSheetDialogFragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_menu_layout, container, false);

        LinearLayout gameDetails = v.findViewById(R.id.gameDetails);
        LinearLayout linkpair = v.findViewById(R.id.linkPair);
        LinearLayout dataVis = v.findViewById(R.id.dataVis);
        LinearLayout closeMenu = v.findViewById(R.id.closeMenu);

        gameDetails.setOnClickListener(this);
        linkpair.setOnClickListener(this);
        dataVis.setOnClickListener(this);
        closeMenu.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linkPair:
                Intent intent = new Intent(getActivity(), LinkPokemon.class);
                startActivity(intent);
                break;
            case R.id.gameDetails:
                break;
            case R.id.dataVis:
                break;
            case R.id.closeMenu:
                dismiss();
        }
        dismiss();
    }
}
