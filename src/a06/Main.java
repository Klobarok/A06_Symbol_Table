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
		    challengePut(symbolTable);
		    challengeGet(symbolTable);
		    challengeDelete(symbolTable);
		    challengeContains(symbolTable);
		    challengeIsEmpty(symbolTable);
		    challengeSize(symbolTable);
		    challengeMinMax(symbolTable);
            challengeFloorCeiling(symbolTable);
		    challengeRank(symbolTable);
		    challengeSelect(symbolTable);
		    challengeDeleteMinMax(symbolTable);
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
    	System.out.println("Testing 'put' operation:");
    	Pokemon testPokemon = new Pokemon(152, "testPokemon","Normal", 100, 200, 300, 400, 500, 600);
    	
    	st.put(152, testPokemon);
    	System.out.println("Test Pokemon successfully addeed.");
    	printSymbolTable(st);
    	
       
    }

    /**
     * Challenge demonstrating the "get" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeGet(PokemonSymbolTable st) {
        System.out.println("Testing get() function:");
        Pokemon pokemon123 = st.get(123);
        
        System.out.println("Pokemon #123: ");
        System.out.print(pokemon123);
        System.out.println("----------------------------------");
       
    }

    /**
     * Challenge demonstrating the "delete" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeDelete(PokemonSymbolTable st) {
        System.out.println("Testing delete() function: ");
        System.out.println("Will delete Pokemon 152");
        st.delete(152);
        System.out.println("Deletion complete, printing table:");
        printSymbolTable(st);
        System.out.println("----------------------------------");
    }

    /**
     * Challenge demonstrating the "contains" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeContains(PokemonSymbolTable st) {
        System.out.println("Testing contains() function: ");
        
        System.out.println("Does the table contain Pokemon # 133?");
        System.out.println(st.contains(133));
        System.out.println();
        System.out.println("Does the table contain Pokemon @ 152?");
        System.out.println(st.contains(152));
        
        System.out.println("----------------------------------");
    }

    /**
     * Challenge demonstrating the "isEmpty" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeIsEmpty(PokemonSymbolTable st) {
        
    	System.out.println("Testing isEmpty() function: ");
        
        System.out.println("Is the table Empty?");
        System.out.println(st.isEmpty());
        System.out.println();
        System.out.println("We create a new empty table and test it");
        PokemonSymbolTable emptyTable = new PokemonSymbolTable();
        System.out.println("Is the table Empty?");
        System.out.println(emptyTable.isEmpty());
        
       
    	System.out.println("----------------------------------");
    }

    /**
     * Challenge demonstrating the "size" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeSize(PokemonSymbolTable st) {
        System.out.println("Testing the size: ");
        System.out.println("What is the size of the table?: " + st.size());
    	System.out.println("----------------------------------");
    }

    /**
     * Challenge demonstrating the "min" or "max" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeMinMax(PokemonSymbolTable st) {
        System.out.println("Testing the min and max:");
        System.out.println("What is the min key?: "+ st.min());
        System.out.println("What is the max key?: " + st.max());
    	 System.out.println("----------------------------------");
    }

    /**
     * Challenge demonstrating the "floor" or "ceiling" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeFloorCeiling(PokemonSymbolTable st) {
        System.out.println("Testing the Floor and Ceiling Functions: ");
        
        System.out.println("What is the floor of 142?: " + st.floor(142));
        System.out.println("What is the ceiling of 100?: " + st.ceiling(100));
    	 System.out.println("----------------------------------");
    }

    /**
     * Challenge demonstrating the "rank" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeRank(PokemonSymbolTable st) {
    	System.out.println(" Testing the rank function: ");
    	System.out.println("What is the rank of 100?:  "+ st.rank(100));
        // TODO: Implement this challenge
    	 System.out.println("----------------------------------");
    }

    /**
     * Challenge demonstrating the "select" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeSelect(PokemonSymbolTable st) {
    	System.out.println("Testing the Select function");
    	System.out.println("Which pokemon has ID 100?: "+ st.select(100));
        
    	 System.out.println("----------------------------------");
    }

    /**
     * Challenge demonstrating the "deleteMin" or "deleteMax" operation of the symbol table.
     *
     * @param st The symbol table to operate on.
     */
    public static void challengeDeleteMinMax(PokemonSymbolTable st) {
        System.out.println("Testing Delete Min and Max: ");
        System.out.println("Delete min and max");
        st.deleteMin();
        st.deleteMax();
        System.out.println("Deletion complete, here's the printed table. ");
        printSymbolTable(st);
    	 System.out.println("----------------------------------");
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

