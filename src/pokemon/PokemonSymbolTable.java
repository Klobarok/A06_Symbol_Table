package pokemon;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.RedBlackBST;
/**
 *  Creates a RedBlackBST symbol table for quickly retrieving pokemon. 
 *  @author Joel Berg + Joseph Peat
 */
public class PokemonSymbolTable {
    private RedBlackBST<Integer, Pokemon> rbst;
    public static List<Pokemon> pokemonList = PokemonCSVReader.getPokemonList();
    
    public static PokemonSymbolTable getPokemonST() {
    	// Create a symbol table
        PokemonSymbolTable symbolTable = new PokemonSymbolTable();

        // Populate the symbol table with Pokémon data
        for (Pokemon pokemon : pokemonList) {
            symbolTable.put(pokemon.getId(), pokemon);
        }
    	return symbolTable;
    }

    public PokemonSymbolTable() {
        this.rbst = new RedBlackBST<>();
    }

    // Add a Pokemon to the symbol table
    public void put(int id, Pokemon pokemon) {
        rbst.put(pokemon.getId(), pokemon);
    }

    // Retrieve a Pokemon by ID
    public Pokemon get(int id) {
        return rbst.get(id);
    }

    // Delete a Pokemon by ID
    public void delete(int id) {
        rbst.delete(id);
    }

    // Check if a Pokemon ID exists in the table
    public boolean contains(int id) {
        return rbst.contains(id);
    }

    // Check if the symbol table is empty
    public boolean isEmpty() {
        return rbst.isEmpty();
    }

    // Get the size of the symbol table
    public int size() {
        return rbst.size();
    }

    // Get the Pokemon with the smallest ID
    public Pokemon min() {
        return rbst.get(rbst.min());
    }

    // Get the Pokemon with the largest ID
    public Pokemon max() {
        return rbst.get(rbst.max());
    }

    // Get Pokemon just less than given ID
    public Pokemon floor(int id) {
        return rbst.get(rbst.floor(id));
    }

    // Get Pokemon just more than given ID
    public Pokemon ceiling(int id) {
        return rbst.get(rbst.ceiling(id));
    }

    // Get the rank of Pokemon by ID
    public int rank(int id) {
        return rbst.rank(id);
    }

    // Get the Pokemon with the given rank
    public Pokemon select(int rank) {
        return rbst.get(rbst.select(rank));
    }

    // Delete the Pokemon with the smallest ID
    public void deleteMin() {
        rbst.deleteMin();
    }

    // Delete the Pokemon with the largest ID
    public void deleteMax() {
        rbst.deleteMax();
    }

    // Get all Pokemon IDs in the table
    public Iterable<Integer> keys() {
        return rbst.keys();
    }
    
public static String[][] symbolTableTo2DArray() {
    	
        List<String[]> rows = new ArrayList<>();
        PokemonSymbolTable symbolTable = getPokemonST();
        for (int key : symbolTable.keys()) {
            Pokemon pokemon = symbolTable.get(key);
            String[] row = {
                String.valueOf(pokemon.getId()),
                pokemon.getName(),
                pokemon.getType(),
                // Add other fields of Pokemon as needed
            };
            rows.add(row);
        }

        String[][] table = new String[rows.size()][];
        rows.toArray(table);

        return table;
    }

}
