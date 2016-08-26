package me.hxkandwal.daily.challanges.personal;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

/**
 * Write an implementation for Least Frequently (Least Recently Used) Used Cache.
 * 
 * Technical Reference : http://mcicpc.cs.atu.edu/archives/2012/mcpc2012/lru/lru.html
 * 
 * @author Heman
 *
 */
public class LFUCache<T extends Comparable<T>> {

	private final int LIMIT = 1 + 5; 	// position idx 0 has been reserved to null. (swap operations)
	private int size = 0;
	private Entry<T>[] entries = new Entry[LIMIT];
	private static int indexer = 0; 
	
	private class Entry<T> {
		int hitCount;
		T value;
		
		public Entry(T value) {
			this.value = value;
			this.hitCount = indexer ++;
		}
		
		@Override
		public String toString() {
			return value.toString() + "(" + hitCount + ")";
		}
	}
	
	public void insert(T value) {
		int index = find(value);
		
		if (index > 0)
			entries[index].hitCount = indexer ++;
		else {
			if ((size + 1) < LIMIT) {
				entries[++ size] = new Entry<>(value);	
			} else {
				System.out.println("removing least reacently used : " + entries[1].value.toString());
				entries[1] = new Entry<>(value);
			}
		}
		
		maintainHeap();
	}
	
	public int find(T value) {
		int index = -1;
		for (int idx = 1; idx <= size; idx ++) {
			if (entries[idx].value.compareTo(value) == 0) {
				index = idx;
				break;
			}
		}
		return index;
	}
	
	private void maintainHeap() {
		for (int idx = size/2; idx > 0; idx --)
			heapify(idx);
	}
	
	private void heapify(int idx) {
		if (idx <= 0)
			return;
		
		int swapIndex = -1;
		
		if ((2 * idx) <= size && entries[idx].hitCount > entries[2 * idx].hitCount) 
			swapIndex = ((2 * idx + 1) <= size && (entries[2 * idx].hitCount > entries[2 * idx + 1].hitCount)) ? (2 * idx + 1) : (2 * idx) ;
		
		else if ((2 * idx + 1) <= size && (entries[idx].hitCount > entries[2 * idx + 1].hitCount)) 
			swapIndex = (2 * idx + 1);
		
		if (swapIndex > 0) {
			entries[0] = entries[swapIndex];
			entries[swapIndex] = entries[idx];
			entries[idx] = entries[0];
			entries[0] = null;

			heapify(swapIndex);
		}
	}
	
	public String stringify() {
		StringBuilder builder = new StringBuilder();
		
		for (int idx = 1; idx <= size; idx ++) 
			builder.append(entries[idx].value.toString());
		
		return builder.toString();
	}

	/*
	 * Driver code
	 */
	public static void main(String[] args) {
		assertEquals(new String[] {"ABC", "CDFAE", "DEFAB"},  runAndReturn("ABC!DEAF!B!"));
	}

	// runner method
	private static String[] runAndReturn(String input) {
		LFUCache<String> cache = new LFUCache<>();
		
		List<String> output = new ArrayList<>();
		
		for (int idx = 0; idx < input.length(); idx ++) {
			if (input.charAt(idx) == '!')
				output.add(cache.stringify());
			else
				cache.insert(String.valueOf(input.charAt(idx)));
		}
		
		return output.toArray(new String[0]);
	}
	
}
