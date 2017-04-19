package challenges.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 460. LFU Cache
 * 
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
 * 	get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * 	put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least 
 * 					  frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys 
 * 					 that have the same frequency), the least recently used key would be evicted.
 * 
 * Follow up:
 * 		Could you do both operations in O(1) time complexity?
 * 		Example:
 * 
 * 		LFUCache cache = new LFUCache( 2 /* capacity */ /**);
 * 		cache.put(1, 1);
 * 		cache.put(2, 2);
 * 		cache.get(1);       // returns 1
 * 		cache.put(3, 3);    // evicts key 2
 * 		cache.get(2);       // returns -1 (not found)
 * 		cache.get(3);       // returns 3.
 * 		cache.put(4, 4);    // evicts key 1.
 * 		cache.get(1);       // returns -1 (not found)
 * 		cache.get(3);       // returns 3
 * 		cache.get(4);       // returns 4
 */
public class LFUCache extends AbstractCustomTestRunner {
	
	int min, capacity;
    Map<Integer, Integer> map;
    Map<Integer, Map<Integer, Integer>> freq;
    
    public LFUCache(int capacity) {
        this.min = 0;
        this.map = new HashMap<>();
        this.freq = new HashMap<>();
        this.capacity = capacity;
    }
    
    private int update(int key, int v) {
        int count = map.get(key);
        Map<Integer, Integer> values = freq.get(count);
        
        //remove the key from values map
        int ans = values.remove(key);
        
        //values map is empty remove freq bucket and update min
        if (values.size() == 0) {
            if (min == count) min = count + 1;
            freq.remove(count);
        }
        
        count ++;
        
        //create new freq bucket and add key and ans to its values
        if (!freq.containsKey(count)) freq.put(count, new LinkedHashMap<>());
        freq.get(count).put(key, v == Integer.MIN_VALUE ? ans : v);
        map.put(key, count);
        return ans;
    }
    
    public int get(int key) {
        if (!map.containsKey(key) || capacity == 0) return -1;
        return update(key, Integer.MIN_VALUE);
    }
    
    public void put(int key, int value) {
        if (capacity == 0) return;
        if (map.containsKey(key)) {
            update(key, value);
            return;
        }
        if (map.size() == capacity) {
            Map<Integer, Integer> values = freq.get(min);
            Integer firstKey = values.keySet().iterator().next();
            values.remove(firstKey);
        
            //values map is empty remove freq bucket
            if(values.size() == 0) freq.remove(min);
            map.remove(firstKey);
        }
        
        //create new freq bucket and add key and value to its values
        if (!freq.containsKey(1)) freq.put(1, new LinkedHashMap<>());
        freq.get(1).put(key, value);
        map.put(key, 1);
        min = 1;
    }
    
}
