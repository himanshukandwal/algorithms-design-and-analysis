package me.hxkandwal.daily.challanges.leetcode;

import java.util.HashSet;
import java.util.Set;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 217. Contains Duplicate
 * 
 * Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears 
 * at least twice in the array, and it should return false if every element is distinct.
 * 
 * @author Hxkandwal
 */
public class ContainsDuplicate extends AbstractCustomTestRunner {
	
	private static ContainsDuplicate _instance = new ContainsDuplicate();
	
	private ContainsDuplicate() {}
	
	public boolean _containsDuplicate(int[] nums) {
		Set<Integer> set = new HashSet<>();
        for (int num : nums) if (!set.add(num)) return true;
        return false;
    }

}
