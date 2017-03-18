package me.hxkandwal.daily.challanges.leetcode;

import java.util.HashMap;
import java.util.Map;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 170. Two Sum III - Data structure design
 * 
 * Design and implement a TwoSum class. It should support the following
 * operations: add and find.
 * 
 * add - Add the number to an internal data structure. find - Find if there
 * exists any pair of numbers which sum is equal to the value.
 * 
 * For example, add(1); add(3); add(5); find(4) -> true find(7) -> false
 * 
 * @author Hxkandwal
 */
public class TwoSumIIIDataStructureDesign extends AbstractCustomTestRunner {

	private Map<Integer, Integer> map;
	private int size;

	/** Initialize your data structure here. */
	public TwoSumIIIDataStructureDesign() {
		map = new HashMap<>();
	}

	/** Add the number to an internal data structure.. */
	public void add(int number) {
		map.put(number, map.getOrDefault(number, 0) + 1);
		size++;
	}

	/**
	 * Find if there exists any pair of numbers which sum is equal to the value.
	 */
	public boolean find(int value) {
		if (size > 1) {
			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
				int num = entry.getKey();
				if (value - num == num) { if (entry.getValue() > 1) return true; } 
				else if (map.containsKey(value - num)) return true;
			}
		}
		
		return false;
	}
}
