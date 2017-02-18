package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 1. Two Sum
 * 
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * 
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * 
 * Example:
 * 		Given nums = [2, 7, 11, 15], target = 9,
 * 
 * 		Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
 * 
 * @author Hxkandwal
 */
public class TwoSum extends AbstractCustomTestRunner {
	
	private static TwoSum _instance = new TwoSum();
	
	private TwoSum() {}
	
    public int[] _twoSum(int[] nums, int target) {
    	Map<Integer, Integer> map = new HashMap<>();
    	for (int idx = 0; idx < nums.length; idx ++)
    		if (map.containsKey(target - nums [idx]))
    			return new int [] { map.get(target - nums [idx]), idx };
    		else 
    			map.put (nums [idx], idx);
        return new int [] {};
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 3, 2, 4 }, 6, new int[] { 1, 2 });
	}

	public void runTest(final int[] nums, final int target, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums, target });

		for (Object answer : answers)
				assertThat((int[]) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
