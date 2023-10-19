package a06;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PokemonSymbolTable {

    private Map<Integer, Pokemon> table = new HashMap<>();

    public Pokemon get(Integer key) {
        return table.get(key);
    }

    public void put(Integer key, Pokemon value) {
        table.put(key, value);
    }

    public Set<Integer> keys() {
        return table.keySet();
    }

    public void delete(Integer key) {
        //TODO: Implement the 'delete' method to remove a Pokémon from the table using its ID.
    }

    public boolean contains(Integer key) {
        // TODO: Implement the 'contains' method to check if a Pokémon with a specific ID exists in the table.
        return false;
    }

    public boolean isEmpty() {
        // TODO: Implement the 'isEmpty' method to check if the symbol table is empty.
        return false;
    }

    public int size() {
        // TODO: Implement the 'size' method to get the total number of Pokémon in the table.
        return 0;
    }

    public Integer min() {
        // TODO: Implement the 'min' method to fetch the Pokémon with the lowest ID.
        return null;
    }

    public Integer max() {
        // TODO: Implement the 'max' method to fetch the Pokémon with the highest ID.
        return null;
    }

    public Integer floor(Integer key) {
        // TODO: Implement the 'floor' method to fetch the Pokémon whose ID is just below a given ID.
        return null;
    }

    public Integer ceiling(Integer key) {
        // TODO: Implement the 'ceiling' method to fetch the Pokémon whose ID is just above a given ID.
        return null;
    }

    public int rank(Integer key) {
        // TODO: Implement the 'rank' method to get the rank of a Pokémon by ID (i.e., how many Pokémon have lower IDs).
        return 0;
    }

    public Integer select(int k) {
        // TODO: Implement the 'select' method to retrieve a Pokémon by its rank.
        return null;
    }

    public void deleteMin() {
        // TODO: Implement the 'deleteMin' method to remove the Pokémon with the lowest ID.
    }

    public void deleteMax() {
        // TODO: Implement the 'deleteMax' method to remove the Pokémon with the highest ID.
    }
}