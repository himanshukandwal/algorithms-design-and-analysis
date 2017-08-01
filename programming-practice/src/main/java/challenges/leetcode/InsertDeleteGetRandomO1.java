package challenges.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import challenges.AbstractCustomTestRunner;

/**
 * 380. Insert Delete GetRandom O(1)
 * 
 * Design a data structure that supports all following operations in average O(1) time.
 * 
 * 	insert(val): Inserts an item val to the set if not already present.
 * 	remove(val): Removes an item val from the set if present.
 * 	getRandom: 	Returns a random element from current set of elements. 
 * 				Each element must have the same probability of being returned.
 * 
 * Example:
 * 
 * 	// Init an empty set.
 * 	RandomizedSet randomSet = new RandomizedSet();
 * 	
 * 	// Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * 	randomSet.insert(1);
 * 
 * 	// Returns false as 2 does not exist in the set.
 * 	randomSet.remove(2);
 * 
 * 	// Inserts 2 to the set, returns true. Set now contains [1,2].
 * 	randomSet.insert(2);
 * 
 * 	// getRandom should return either 1 or 2 randomly.
 * 	randomSet.getRandom();
 * 
 * 	// Removes 1 from the set, returns true. Set now contains [2].
 * 	randomSet.remove(1);
 * 
 * 	// 2 was already in the set, so return false.
 * 	randomSet.insert(2);
 * 
 * 	// Since 2 is the only number in the set, getRandom always return 2.
 * 	randomSet.getRandom();
 * 
 * @author Hxkandwal
 */
public class InsertDeleteGetRandomO1 extends AbstractCustomTestRunner {

    List<Integer> data = new ArrayList<>();
    Map<Integer, Integer> bookkeeping = new HashMap<>();
    
    /** Initialize your data structure here. */
    public InsertDeleteGetRandomO1() { }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (bookkeeping.containsKey (val)) return false;
        data.add (val);
        bookkeeping.put (val, data.size () - 1);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!bookkeeping.containsKey (val)) return false;
        int idx = bookkeeping.remove (val);
        data.set (idx, data.get (data.size() - 1));
        data.remove (data.size() - 1);
        if (idx < data.size ()) bookkeeping.put (data.get (idx), idx);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return data.get (new Random ().nextInt (bookkeeping.size()));
    }
    
}
