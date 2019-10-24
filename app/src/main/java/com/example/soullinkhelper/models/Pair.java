package com.example.soullinkhelper.models;

import com.example.soullinkhelper.enums.State;
import com.example.soullinkhelper.service.FirebaseService;

public class Pair {

    private State state;
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private String route;

    public Pair(Pokemon pokemon1, Pokemon pokemon2, String route){
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.route = route;
        state = State.ALIVE;
    }

    public Pair(Pokemon pokemon1, Pokemon pokemon2, String route, State state){
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.route = route;
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public Pokemon getPokemon1() {
        return pokemon1;
    }

    public Pokemon getPokemon2() {
        return pokemon2;
    }

    public String getRoute() {
        return route;
    }

    /**
     * Set the Pokemon Pairs state to "DEAD".
     */
    public void die(int position){
        state = State.DEAD;
        FirebaseService.getFirebaseServiceInstance().savePair(GameManager.getInstance().getGameName(), this, position);
    }
}
