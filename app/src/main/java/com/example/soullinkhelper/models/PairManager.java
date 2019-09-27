package com.example.soullinkhelper.models;

import java.util.ArrayList;

public class PairManager {

    private ArrayList<Pair> pairList;
    private static PairManager mInstance;

    private PairManager(){
        pairList = new ArrayList<>();
    }

    public static PairManager getInstance(){
        if(mInstance == null){
            mInstance = new PairManager();
        }
        return mInstance;
    }

    public ArrayList<Pair> getPairList(){
        return this.pairList;
    }

    public void addPair(Pair pair){
        pairList.add(pair);
        //TODO: Send to fireBase
    }

    public void removePair(Pair pair){
        pairList.remove(pair);
    }

    public void clearPairList(){
        pairList.clear();
    }

    public Pair getPair(int pairIndex){
        return pairList.get(pairIndex);
    }
}
