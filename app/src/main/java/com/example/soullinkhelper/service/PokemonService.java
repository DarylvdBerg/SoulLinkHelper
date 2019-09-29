package com.example.soullinkhelper.service;

import android.content.Context;
import android.widget.ProgressBar;

import com.example.soullinkhelper.dao.PokemonDAO;
import com.example.soullinkhelper.database.DatabaseHelper;
import java.util.List;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonType;

public class PokemonService {
    private DatabaseHelper helper;
    private PokeApi api;
    private PokemonDAO pokemonDAO;

    public PokemonService(Context ctx){
        helper = DatabaseHelper.getHelper(ctx);
        api = new PokeApiClient();
        pokemonDAO = new PokemonDAO(ctx);
    }

    public void savePokemons(int pokemons, ProgressBar pBar){
        for(int i = 1; i <= pokemons; i ++){
            Pokemon pokemon = api.getPokemon(i);
            String pokemonName = pokemon.getName();
            List<PokemonType> types = pokemon.getTypes();
            String sprites = pokemon.getSprites().getFrontDefault();

            pokemonDAO.writePokemonToDb(pokemonName, types, sprites);
            pBar.setProgress(i);
        }
    }
}
