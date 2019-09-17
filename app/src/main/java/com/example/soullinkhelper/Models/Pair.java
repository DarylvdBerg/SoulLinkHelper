package com.example.soullinkhelper.Models;

public class Pair {

    enum State{DEAD, ALIVE}
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

    /**
     * Set the Pokemon Pairs state to "DEAD".
     */
    public void die(){
        state = State.DEAD;
    }
}
