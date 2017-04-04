package me.hxkandwal.daily.challanges.leetcode;

import java.util.HashMap;
import java.util.Map;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 146. LRU Cache
 * 
 * Design and implement a data structure for Least Recently Used (LRU) cache. 
 * It should support the following operations: get and put.
 * 
 * 		get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * 		put(key, value) - Set or insert the value if the key is not already present. 
 * 
 * When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * 
 * Follow up: Could you do both operations in O(1) time complexity?
 * 
 * @author Hxkandwal
 */
public class LRUCache extends AbstractCustomTestRunner {

	private static class Node {
        int key;
        int value;
        Node () { this (0, 0); }
        Node (int key, int value) { this.key = key; this.value = value; }
        Node next;
        Node prev;
    }
    
    Map <Integer, Node> map = new HashMap<>();
    Node head;  Node tail;
    int size = 0; int capacity = 0;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node ();
        tail = new Node ();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node node = map.get (key);
        if (node == null) return -1;
        update (node);
        return node.value;
    }
    
    public void put(int key, int value) {
        Node node = map.get (key);
        if (node == null) {
            node = new Node (key, value);
            if (size + 1 > capacity) remove (head.next);
            add (node);
        } else { update (node); node.value = value; }
    }
    
    private void update (Node node) {
        remove (node);
        add (node);
    }
    
    private void add (Node node) {
        Node before = tail.prev;
        before.next = node;
        node.prev = before;
        node.next = tail;
        tail.prev = node;
        map.put (node.key, node);
        size ++;
    }
    
    private void remove (Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = node.next = null;
        map.remove (node.key);
        size --;
    }
    
	// driver method
	public static void main(String[] args) {
		LRUCache cache = new LRUCache(2);
		cache.put(2, 1);
		cache.put(1, 1);
		cache.put(2, 3);
		cache.put(4, 1);
		System.out.println(cache.get(1));
		System.out.println(cache.get(2));
	}
	
}
