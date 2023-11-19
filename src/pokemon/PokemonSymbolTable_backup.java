package pokemon;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PokemonSymbolTable_backup {

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
    	if(!table.containsKey(key)) {
    		throw new IllegalArgumentException("Key not found in table.");
    	}
        table.remove(key);
    }

    public boolean contains(Integer key) {
        
        return table.containsKey(key);
    }

    public boolean isEmpty() {
        
        return table.isEmpty();
    }

    public int size() {
        
        return table.size();
    }

    public Integer min() {
    	if(table.isEmpty()) {
    		return null;
    	}
    	// cycle through all of the keys and return the smallest one. 
    	Integer minKey = Integer.MAX_VALUE; // initialize it to the max value of an Integer
    	for(Integer key : table.keySet()) { // loop through all key values to find min. 
    		if(key < minKey) {
    			minKey = key;
    		}
    	}
        return minKey;
    }

    public Integer max() {
    	if(table.isEmpty()) {
    		return null;
    	}
    	// cycle through all of the keys and return the smallest one. 
    	Integer maxKey = Integer.MIN_VALUE; // initialize it to the max value of an Integer
    	for(Integer key : table.keySet()) { // loop through all key values to find min. 
    		if(key > maxKey) {
    			maxKey = key;
    		}
    	}
        return maxKey;
    }
    /**
     * The 'floor' method to fetch the Pokémon whose ID is just below a given ID.
     * @param key
     * @return floorKey
     */
    public Integer floor(Integer key) {
    	if(!table.containsKey(key)) {
    		throw new IllegalArgumentException("Key not found in table.");
    	}
    	// loop through and find a key value that is less than the key argument. 
    	if(table.isEmpty()) return null;
    	
    	Integer floorKey = null;
    	
    	for(Integer currentKey : table.keySet()) {
    		// If the current key is less than the given key and (either we haven't found 
    		// a floor key yet or the current key is greater than the previiously found floor key
    		if(currentKey < key && (floorKey == null || currentKey > floorKey)) {
    			floorKey = currentKey;
    			
    		}
    	}
        return floorKey;
    }
    /**
     * the 'ceiling' method to fetch the Pokémon whose ID is just above a given ID.
     * @param key
     * @return ceilingKey
     */
    public Integer ceiling(Integer key) {
    	if(!table.containsKey(key)) {
    		throw new IllegalArgumentException("Key not found in table.");
    	}
    	// loop through and find a key value that is less than the key argument. 
    	if(table.isEmpty()) return null;
    	
    	Integer ceilingKey = null;
    	
    	for(Integer currentKey : table.keySet()) {
    		// If the current key is greater than the given key and (either we haven't found 
    		// a floor key yet or the current key is less than the previously found floor key
    		if(currentKey > key && (ceilingKey == null || currentKey < ceilingKey)) {
    			ceilingKey = currentKey;
    			
    		}
    	}
        return ceilingKey;
    }
    /**
     * The 'rank' method to get the rank of a Pokémon by ID (i.e., how many Pokémon have lower IDs).
     * @param key
     * @return the count of Pokemon that have an lower IDs than the one given. 
     */
    public int rank(Integer key) {
    	if(!table.containsKey(key)) {
    		throw new IllegalArgumentException("Key not found in table.");
    	}
    	int count = 0;
    	for(Integer currentKey : table.keySet()) {
    		if(currentKey <= key) {
    			count++;
    		}
    	}
        return count;
    }
    /**
     * Selects the name of the pokemon. 
     * @param k is the key
     * @return name of pokemon. 
     */
    public String select(int k) {
    	if(!table.containsKey(k)) {
    		throw new IllegalArgumentException("Key not found in table.");
    	}
    	String name = table.get(k).getName();

        return name;
    }
    /**
     * deletes thie minimum 
     */
    public void deleteMin() {
    	if(table.isEmpty()) return;
    	Integer minKey = min();
    	
    	if(minKey != null) {
    		table.remove(minKey);
    	}
      
    	
    }

    public void deleteMax() {
        if(table.isEmpty()) return;
        
        Integer maxKey = max();
        if(maxKey != null) {
        	table.remove(maxKey);
        }
    }
}