package challenges.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 706. Design HashMap
 *
 * Design a HashMap without using any built-in hash table libraries.
 *
 * To be specific, your design should include these functions:
 *
 *  -   put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
 *  -   get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 *  -   remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.
 *
 * Example:
 *  MyHashMap hashMap = new MyHashMap();
 *  hashMap.put(1, 1);
 *  hashMap.put(2, 2);
 *  hashMap.get(1);            // returns 1
 *  hashMap.get(3);            // returns -1 (not found)
 *  hashMap.put(2, 1);          // update the existing value
 *  hashMap.get(2);            // returns 1
 *  hashMap.remove(2);          // remove the mapping for 2
 *  hashMap.get(2);            // returns -1 (not found)
 *
 * Note:
 *  - All keys and values will be in the range of [0, 1000000].
 *  - The number of operations will be in the range of [1, 10000].
 *  - Please do not use the built-in HashMap library.
 *
 * @author hxkandwal
 */
public class DesignHashMap {

    class Pair {
        int k, v;
        public Pair(int k, int v) { this.k = k; this.v = v; }
    }

    private final int N = 1000;
    private final List[] table;

    /** Initialize your data structure here. */
    public DesignHashMap() {
        this.table = new ArrayList[N];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int idx = key % N;
        if (table [idx] == null) table [idx] = new ArrayList<Pair>();

        for (Pair p : (ArrayList<Pair>) table [idx]) if (p.k == key) { p.v = value; return; }
        table [idx].add (new Pair(key, value));
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int idx = key % N;
        ArrayList<Pair> value = (ArrayList<Pair>) table [idx];

        if (value != null) for (Pair p : value) if (p.k == key) return p.v;
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int idx = key % N;
        ArrayList<Pair> value = (ArrayList<Pair>) table [idx];

        if (value != null) {
            for (Iterator<Pair> itr = value.iterator(); itr.hasNext();) {
                Pair p = itr.next();
                if (p.k == key) itr.remove();
            }
        }
    }
}