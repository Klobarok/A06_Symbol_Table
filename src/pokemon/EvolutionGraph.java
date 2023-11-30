package pokemon;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents the evolution graph for Pokémon.
 * Each Pokémon is represented by its ID, and evolutions are stored as edges in the graph.
 * @author Joseph Peat
 */
public class EvolutionGraph {
    private Map<Integer, Set<Integer>> evolutionMap;
    private Map<Integer, Integer> preEvolutionMap;

    public EvolutionGraph(PokemonSymbolTable symbolTable) {
        this.evolutionMap = new HashMap<>();
        this.preEvolutionMap = new HashMap<>();
        for (Integer pokemonId : symbolTable.keys()) {
            Pokemon pokemon = symbolTable.get(pokemonId);
            int evolvesToId = pokemon.getEvolvesTo();

            if (evolvesToId != 0) {
                addEvolution(pokemon.getId(), evolvesToId);
                preEvolutionMap.put(evolvesToId, pokemon.getId());
            }
        }
        addEeveeEvolutions();
    }

    /**
     * Adds an evolution from one Pokémon to another.
     *
     * @param fromId The ID of the original Pokémon.
     * @param toId   The ID of the Pokémon it evolves into.
     */
    public void addEvolution(int fromId, int toId) {
        evolutionMap.computeIfAbsent(fromId, k -> new HashSet<>()).add(toId);
    }

    /**
     * Gets the evolutions of a Pokémon.
     *
     * @param pokemonId The ID of the Pokémon.
     * @return A set of IDs representing the Pokémon it can evolve into.
     */
    public Set<Integer> getEvolutions(int pokemonId) {
        return evolutionMap.getOrDefault(pokemonId, Collections.emptySet());
    }

    /**
     * Removes an evolution from the graph.
     *
     * @param fromId The ID of the original Pokémon.
     * @param toId   The ID of the evolved Pokémon.
     */
    public void removeEvolution(int fromId, int toId) {
        if (evolutionMap.containsKey(fromId)) {
            evolutionMap.get(fromId).remove(toId);
            if (evolutionMap.get(fromId).isEmpty()) {
                evolutionMap.remove(fromId);
            }
        }
    }

    /**
     * Checks if there is a direct evolution from one Pokémon to another.
     *
     * @param fromId The ID of the original Pokémon.
     * @param toId   The ID of the evolved Pokémon.
     * @return true if there is a direct evolution, false otherwise.
     */
    public boolean hasDirectEvolution(int fromId, int toId) {
        return evolutionMap.containsKey(fromId) && evolutionMap.get(fromId).contains(toId);
    }

    /**
     * Gets the entire evolution path for a Pokémon.
     *
     * @param pokemonId The ID of the starting Pokémon.
     * @return A list of IDs representing the entire evolution path.
     */
    public List<Integer> getEvolutionPath(int pokemonId) {
        List<Integer> evolutionPath = new ArrayList<>();
        collectEvolutions(pokemonId, evolutionPath);
        return evolutionPath;
    }

    /**
     * Recursively collects the evolutions of a Pokémon.
     *
     * @param pokemonId     The ID of the Pokémon.
     * @param evolutionPath The list accumulating the evolution path.
     */
    private void collectEvolutions(int pokemonId, List<Integer> evolutionPath) {
        if (evolutionMap.containsKey(pokemonId)) {
            for (Integer evolvesToId : evolutionMap.get(pokemonId)) {
                evolutionPath.add(evolvesToId);
                collectEvolutions(evolvesToId, evolutionPath);
            }
        }
    }

    /**
     * Gets the complete evolution chain for a Pokémon.
     *
     * @param pokemonId The ID of the Pokémon.
     * @return A list of IDs representing the entire evolution chain.
     */
    public List<Integer> getCompleteEvolutionChain(int pokemonId) {
        List<Integer> evolutionChain = new ArrayList<>();

        // Collect pre-evolutions
        Integer preEvoId = preEvolutionMap.get(pokemonId);
        while (preEvoId != null) {
            evolutionChain.add(0, preEvoId);
            preEvoId = preEvolutionMap.get(preEvoId);
        }

        // Add the original Pokémon
        evolutionChain.add(pokemonId);

        // Collect evolutions
        collectEvolutions(pokemonId, evolutionChain);

        return evolutionChain;
    }

    /**
     * Adds Eevee's unique evolutions. Cause Eevee just has to be extra.
     */
    private void addEeveeEvolutions() {
        int eeveeId = 133; // Eevee
        int vaporeonId = 134; // Vaporeon
        int jolteonId = 135; // Jolteon
        int flareonId = 136; // Flareon

        addEvolution(eeveeId, vaporeonId);
        addEvolution(eeveeId, jolteonId);
        addEvolution(eeveeId, flareonId);
    }

    /**
     * Retrieves a list of Pokémon objects representing the complete evolution chain of the specified Pokémon.
     * This method takes the Pokémon ID and a symbol table, fetches the evolution chain IDs,
     * and converts them into Pokémon objects.
     *
     * @param pokemonId The ID of the Pokémon whose evolution chain is to be retrieved.
     * @param symbolTable The symbol table containing Pokémon data.
     * @return A List of Pokémon objects representing the complete evolution chain.
     */
    public List<Pokemon> getCompleteEvolutionChainPokemons(int pokemonId, PokemonSymbolTable symbolTable) {
        List<Integer> ids = getCompleteEvolutionChain(pokemonId);
        return ids.stream().map(symbolTable::get).collect(Collectors.toList());
    }

    // Example main method to demonstrate usage
    public static void main(String[] args) throws IOException {
        PokemonSymbolTable symbolTable;
        String csvFilePath = "src/data/pokemon151.csv";

        // Initialize the symbol table and load Pokémon data from the CSV file
        symbolTable = new PokemonSymbolTable();
        List<Pokemon> pokemonList = PokemonCSVReader.readPokemonFromCSV(csvFilePath);
        for (Pokemon pokemon : pokemonList) {
            symbolTable.put(pokemon.getId(), pokemon);
        }

        EvolutionGraph graph = new EvolutionGraph(symbolTable);

        System.out.println("Evolutions of Eevee: " + graph.getEvolutions(133));
        System.out.println("Does Eevee evolve directly to Vaporeon? " + graph.hasDirectEvolution(133, 134));
        System.out.println("Does Eevee evolve directly to Jolteon? " + graph.hasDirectEvolution(133, 135));
        System.out.println("Does Eevee evolve directly to Flareon? " + graph.hasDirectEvolution(133, 136));

        System.out.println("Evolutions of Charmander: " + graph.getEvolutionPath(4));
        System.out.println("Complete evolution chain of Charmeleon: " + graph.getCompleteEvolutionChain(5));

    }
}

