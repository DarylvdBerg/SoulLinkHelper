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

/**
 * PokemonService class for getting and saving pokemons from SQLite DB and making API calls
 * @author Daryl
 */
public class PokemonService {
    private DatabaseHelper helper;
    private PokeApi api;
    private PokemonDAO pokemonDAO;
    private Context ctx;

    public PokemonService(Context ctx) {
        this.ctx = ctx;
        helper = DatabaseHelper.getHelper(ctx);
        api = new PokeApiClient();
        pokemonDAO = new PokemonDAO(ctx);
    }

    /**
     * Save pokemons
     * @param pokemons entry of pokemon according to the pokedex
     * @author Daryl
     */
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
