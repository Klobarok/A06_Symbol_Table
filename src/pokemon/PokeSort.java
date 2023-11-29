package pokemon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import edu.princeton.cs.algs4.RedBlackBST;
/**
 * Contains custom sorting and filtering algorithms for the pokemon data in a 2D array. 
 *  @author Joel Berg + Joseph Peat
 */
public class PokeSort{
	
	public static String[][] sortBySpeed(String[][] data) {
        Arrays.sort(data, new Comparator<String[]>() {
            @Override
            public int compare(String[] row1, String[] row2) {
                int speed1 = Integer.parseInt(row1[4]); // Assuming Speed is at index 4
                int speed2 = Integer.parseInt(row2[4]);
                return Integer.compare(speed1, speed2);
            }
        });
        return data;
    }
	
	public static String[][] sortByAttack(String[][] data) {
		Arrays.sort(data, new Comparator<String[]>() {
            @Override
            public int compare(String[] row1, String[] row2) {
                // Check if both rows have at least 4 elements
                if (row1.length > 3 && row2.length > 3) {
                    int attack1 = Integer.parseInt(row1[3]);
                    int attack2 = Integer.parseInt(row2[3]);
                    return Integer.compare(attack1, attack2);
                }
                return 0; // Or handle this case differently
            }
        });
        return data;
    }
	
	public static String[][] sortByHP(String[][] data) {
        Arrays.sort(data, new Comparator<String[]>() {
            @Override
            public int compare(String[] row1, String[] row2) {
                int hp1 = Integer.parseInt(row1[2]); // Assuming HP is at index 2
                int hp2 = Integer.parseInt(row2[2]);
                return Integer.compare(hp1, hp2);
            }
        });
        return data;
    }
	
	public static String[][] filterAndSortByTypes(String[][] data, boolean[] typeSelections) {
        String[] types = {"Bug", "Dragon", "Electric", "Fairy", "Fighting", "Fire", "Ghost",
        		"Grass", "Ground", "Ice", "Normal", "Poison", "Psychic", "Rock", "Water"};
        
        List<String[]> filteredList = Arrays.stream(data)
            .filter(row -> {
                for (int i = 0; i < typeSelections.length; i++) {
                    if (typeSelections[i] && row[2].contains(types[i])) {
                        return true; // Include row if it matches any selected type
                    }
                }
                return false;
            })
            .sorted((row1, row2) -> row1[1].compareTo(row2[1])) // Sort by name
            .collect(Collectors.toList());

        // Convert the filtered list back to a 2D array
        String[][] filteredArray = new String[filteredList.size()][];
        filteredArray = filteredList.toArray(filteredArray);

        return filteredArray;
    }
	
	public static String[][] sortByName(String[][] array) {
        // Sort the array by the "name" column (index 1)
        Arrays.sort(array, new Comparator<String[]>() {
            @Override
            public int compare(String[] row1, String[] row2) {
                return row1[1].compareTo(row2[1]);
            }
        });

        return array; // Return the sorted array
    }

    
    /**
     * Prints a 2D array to the console.
     *
     * @param array The 2D array to be printed.
     */
    public static void print2DArray(String[][] array) {
        for (String[] row : array) {
            for (String value : row) {
                System.out.print(value + " ");
            }
            System.out.println(); // New line at the end of each row
        }
    }
    
    // Example usage
    public static void main(String[] args) {
        //String[][] sampleArray = PokemonCSVReader.getTable();
    	String[][] original = PokemonSymbolTable.symbolTableTo2DArray();
    	
        //print2DArray(PokemonSymbolTable.symbolTableTo2DArray());
        
       String[][] sortedByName = sortByName(original);
        print2DArray(sortedByName);
    }
/*
 * 
 */

}
