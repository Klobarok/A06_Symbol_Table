package a06;

import java.util.*;

public class PokemonSymbolTable {
    private TreeMap<Integer, Pokemon> table = new TreeMap<>();

    // Put a Pokemon into the table
    public void put(int id, Pokemon pokemon) {
        table.put(id, pokemon);
    }

    // Retrieve a Pokemon from the table by its ID
    public Pokemon get(int id) {
        return table.get(id);
    }
}