package a06;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Define the path to the CSV file
        String csvFilePath = "src/data/pokemon151.csv";

        try {
            // Read the Pokémon data from the CSV
            List<Pokemon> pokemons = PokemonCSVReader.readPokemonFromCSV(csvFilePath);

            // Create a symbol table
            PokemonSymbolTable symbolTable = new PokemonSymbolTable();

            // Populate the symbol table with Pokémon data
            for (Pokemon pokemon : pokemons) {
                symbolTable.put(pokemon.getId(), pokemon);
            }

//            printSymbolTable(symbolTable);

//            // Execute challenges related to the symbol table
//            challengePut(symbolTable);
//            challengeGet(symbolTable);
//            challengeDelete(symbolTable);
//            challengeContains(symbolTable);
//            challengeIsEmpty(symbolTable);
//            challengeSize(symbolTable);
//            challengeMinMax(symbolTable);
//            challengeFloorCeiling(symbolTable);
//            challengeRank(symbolTable);
//            challengeSelect(symbolTable);
//            challengeDeleteMinMax(symbolTable);
            challengeKeys(symbolTable);

        } catch (IOException e) {
            System.out.println("Error reading the CSV file: " + e.getMessage());
        }
    }

    /**
     * Challenge demonstrating the "put" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengePut(PokemonSymbolTable st) {
        // TODO: Implement this challenge
    }

    /**
     * Challenge demonstrating the "get" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeGet(PokemonSymbolTable st) {
        // TODO: Implement this challenge
    }

    /**
     * Challenge demonstrating the "delete" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeDelete(PokemonSymbolTable st) {
        // TODO: Implement this challenge
    }

    /**
     * Challenge demonstrating the "contains" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeContains(PokemonSymbolTable st) {
        // TODO: Implement this challenge
    }

    /**
     * Challenge demonstrating the "isEmpty" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeIsEmpty(PokemonSymbolTable st) {
        // TODO: Implement this challenge
    }

    /**
     * Challenge demonstrating the "size" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeSize(PokemonSymbolTable st) {
        // TODO: Implement this challenge
    }

    /**
     * Challenge demonstrating the "min" or "max" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeMinMax(PokemonSymbolTable st) {
        // TODO: Implement this challenge
    }

    /**
     * Challenge demonstrating the "floor" or "ceiling" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeFloorCeiling(PokemonSymbolTable st) {
        // TODO: Implement this challenge
    }

    /**
     * Challenge demonstrating the "rank" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeRank(PokemonSymbolTable st) {
        // TODO: Implement this challenge
    }

    /**
     * Challenge demonstrating the "select" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeSelect(PokemonSymbolTable st) {
        // TODO: Implement this challenge
    }

    /**
     * Challenge demonstrating the "deleteMin" or "deleteMax" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeDeleteMinMax(PokemonSymbolTable st) {
        // TODO: Implement this challenge
    }

    /**
     * Challenge demonstrating the "keys" operation of the symbol table.
     * This challenge will retrieve all Pokémon IDs from the symbol table
     * and display them.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeKeys(PokemonSymbolTable st) {
        System.out.println("Challenge: Keys");
        System.out.println("----------------------------------------------------");
        System.out.println("All Pokémon IDs in the symbol table:");

        Iterable<Integer> ids = st.keys();
        for (Integer id : ids) {
            System.out.print(id + "\n");
        }

        System.out.println("----------------------------------------------------");
    }


    /**
     * Prints the content of the given symbol table.
     * Really just used to check that the pokemon were getting added correctly.
     *
     * @param st The symbol table to print.
     */
    public static void printSymbolTable(PokemonSymbolTable st) {
        System.out.println("----------------------------------------------------");
        System.out.println("| ID | Name        | Type          | HP | Attack | Defense | Sp.Attack | Sp.Defense | Speed |");
        System.out.println("----------------------------------------------------");

        for (Integer id : st.keys()) {
            Pokemon pokemon = st.get(id);
            System.out.printf("| %2d | %-10s | %-13s | %2d | %6d | %7d | %9d | %10d | %5d |\n",
                    pokemon.getId(), pokemon.getName(), pokemon.getType(), pokemon.getHp(),
                    pokemon.getAttack(), pokemon.getDefense(), pokemon.getSpecialAttack(),
                    pokemon.getSpecialDefense(), pokemon.getSpeed());
        }
        System.out.println("----------------------------------------------------");
    }
}

