package a06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides utility methods to read Pokémon data from a CSV file.
 *
 * @author YourName
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
        List<Pokemon> pokemons = new ArrayList<>();
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
                Pokemon pokemon = new Pokemon(id, name, type, hp, attack, defense, specialAttack, specialDefense, speed);
                pokemons.add(pokemon);
            }
        }
        return pokemons;
    }
}
