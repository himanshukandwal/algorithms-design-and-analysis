package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 377. Combination Sum IV
 * 
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up 
 * to a positive integer target.
 * 
 * Example:
 * 		nums = [1, 2, 3]
 * 		target = 4
 * 
 * The possible combination ways are:
 * 		(1, 1, 1, 1)
 * 		(1, 1, 2)
 * 		(1, 2, 1)
 * 		(1, 3)
 * 		(2, 1, 1)
 * 		(2, 2)
 * 		(3, 1)
 *
 * Note that different sequences are counted as different combinations.
 * 
 * Therefore the output is 7.
 * 
 * Follow up:
 * 		What if negative numbers are allowed in the given array?
 * 		How does it change the problem?
 * 		What limitation we need to add to the question to allow negative numbers?
 * 
 * @author Hxkandwal
 */
public class CombinationSumIV extends AbstractCustomTestRunner {
	
	private static CombinationSumIV _instance = new CombinationSumIV();
	
	private CombinationSumIV() {}
	
	public int _combinationSum4(int[] nums, int target) {
		int [] dp = new int [target + 1];
		Arrays.fill(dp, -1);
		return dpCombinationSum4(dp, nums, target);
    }
    
	public int dpCombinationSum4 (int[] dp, int [] nums, int target) {
		int total = 0;
        if (target == 0) return 0;
        for (int num : nums) {
        	if (num < target) {
        		int response = 0;
        		if (dp [target - num] == -1) 
        			dp [target - num] = response = dpCombinationSum4(dp, nums, target - num);
        		else
        			response = dp [target - num];
        		total += response;
        	} else if (num == target) {
        		total += 1;
        	}
        }
        return total;
	}
	
   	// driver method
   	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, 2, 3 }, 4, 7);
		_instance.runTest(new int [] { 1, 2, 3 }, 32, 181997601);
   	}

   	public void runTest(final int[] nums, final int target, final int expectedOutput) {
   		List<Object> answers = runAll(getClass(), new Object[] { nums, target });

   		for (Object answer : answers)
   				assertThat((Integer) answer).isEqualTo(expectedOutput);

   		System.out.println("ok!");
   	}
   	
}
