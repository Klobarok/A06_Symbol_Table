package pokemon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Provides utility methods to read Pokémon data from a CSV file.
 *
 * @author Joseph Peat + Joel Berg
 * @version 1.0
 */
public class PokemonCSVReader {
    /**
     * Reads Pokémon data from the specified CSV file and returns a list of Pokémon objects.
     *
     * @param filename the path to the CSV file
     * @return a list of Pokémon objects parsed from the CSV file
     * @throws IOException if an error occurs while reading the file
     */
    public static List<Pokemon> readPokemonFromCSV(String filename) throws IOException {
        List<Pokemon> pokemonArrayList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Skip header
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                String type = values[2];
                int hp = Integer.parseInt(values[3]);
                int attack = Integer.parseInt(values[4]);
                int defense = Integer.parseInt(values[5]);
                int specialAttack = Integer.parseInt(values[6]);
                int specialDefense = Integer.parseInt(values[7]);
                int speed = Integer.parseInt(values[8]);
                String evolutionCondition = "";
                Pokemon pokemon;
                if (values.length >= 10) {
                    int evolvesToId = Integer.parseInt(values[9]);
                    if (values.length == 11) {
                        evolutionCondition = values[10];
                    }
                    pokemon = new Pokemon(id, name, type, hp, attack, defense,
                            specialAttack, specialDefense, speed,
                            Optional.of(evolvesToId),
                            Optional.of(evolutionCondition)
                    );
                } else {
                    pokemon = new Pokemon(id, name, type, hp, attack, defense,
                            specialAttack, specialDefense, speed,
                            Optional.empty(),
                            Optional.empty()
                    );
                }
                pokemon.setImagePath();
                pokemonArrayList.add(pokemon);
            }
        }
        return pokemonArrayList;
    }
   
	private static final String CSV_FILE_PATH = "src/data/pokemon151.csv";
	// Static field to hold the list of Pokemon
    private static List<Pokemon> pokemonArrayList = new ArrayList<>();

    static {
        try {
            // Load data when the class is loaded
            pokemonArrayList = readPokemonFromCSV(CSV_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions or leave the list empty
        }
    }
    
 // Static method to access the populated list
    public static List<Pokemon> getPokemonList() {
        return pokemonArrayList;
    }
    /**
     * Reads data from the specified CSV file and returns it as a 2D array.
     *
     * @param filename the path to the CSV file
     * @return a 2D array representing the data in the CSV file
     * @throws IOException if an error occurs while reading the file
     */
    public static String[][] getTable() {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            reader.readLine(); // Skip header
            String line;
            while ((line = reader.readLine()) != null) {
                rows.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Return an empty 2D array in case of IOException
            return new String[0][0];
        }

        String[][] table = new String[rows.size()][];
        rows.toArray(table);
        return table;
    }
}
