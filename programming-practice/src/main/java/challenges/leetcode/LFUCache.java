package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.*;

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

    private Map<Integer, Integer> keyFreqMap = new HashMap <>();
    private Map<Integer, LinkedHashMap<Integer, Integer>> freqMap = new HashMap <>();
    private int size, minfrequency;
    
    public LFUCache(int capacity) {
        this.size = capacity;
    }
    
    private int update(int key, int value) {
        int freq = keyFreqMap.get (key);
        LinkedHashMap <Integer, Integer> map = freqMap.get (freq);
        int oValue = map.remove (key);

        if (map.size () == 0) {
            if (minfrequency == freq) minfrequency = freq + 1;
            freqMap.remove (freq);
        }

        freq ++;
        keyFreqMap.put (key, freq);
        freqMap.computeIfAbsent (freq, k -> new LinkedHashMap<>()).put (key, value == Integer.MAX_VALUE ? oValue : value);
        return value == Integer.MAX_VALUE ? oValue : value;
    }
    
    public int get(int key) {
        return (keyFreqMap.containsKey(key)) ? update (key, Integer.MAX_VALUE) : -1;
    }
    
    public void put(int key, int value) {
        if (size == 0) return;
        if (keyFreqMap.containsKey (key)) update (key, value);
        else {
            if (keyFreqMap.size () == size) {
                LinkedHashMap<Integer, Integer> map = freqMap.get (minfrequency);
                Iterator<Integer> itr = map.keySet ().iterator();
                int rkey = itr.next ();
                itr.remove ();
                keyFreqMap.remove (rkey);

                if (map.size () == 0) freqMap.remove (minfrequency); minfrequency = -1;
            }
            minfrequency = 1;
            keyFreqMap.put (key, 1);
            freqMap.computeIfAbsent (1, k -> new LinkedHashMap<>()).put (key, value);
        }
    }
    
    // driver method
 	public static void main(String[] args) {
 		LFUCache cache = new LFUCache(2);
 		cache.put(1, 1);
 		cache.put(2, 2);
        assertThat(cache.get(1)).isEqualTo(1);
 		cache.put(3, 3);
        assertThat(cache.get(2)).isEqualTo(-1);
        assertThat(cache.get(3)).isEqualTo(3);
 		cache.put(4, 4);
        assertThat(cache.get(1)).isEqualTo(-1);
        assertThat(cache.get(3)).isEqualTo(3);
        assertThat(cache.get(4)).isEqualTo(4);
        System.out.println("ok!");
 	}
     
}
