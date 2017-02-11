package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 494. Target Sum
 * 
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, 
 * you should choose one from + and - as its new symbol.
 * 
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * 
 * Example 1:
 * 		Input: nums is [1, 1, 1, 1, 1], S is 3. 
 * 		Output: 5
 * 
 * 		Explanation:
 * 			-1+1+1+1+1 = 3
 * 			+1-1+1+1+1 = 3
 * 			+1+1-1+1+1 = 3
 * 			+1+1+1-1+1 = 3
 * 			+1+1+1+1-1 = 3
 * 
 * 		There are 5 ways to assign symbols to make the sum of nums be target 3.
 *  
 *  Note:
 *  	> The length of the given array is positive and will not exceed 20.
 *  	> The sum of elements in the given array will not exceed 1000.
 *  	> Your output answer is guaranteed to be fitted in a 32-bit integer.
 *  
 * @author Hxkandwal
 */
public class TargetSumWays extends AbstractCustomTestRunner {
	
	private static TargetSumWays _instance = new TargetSumWays();
	
	private TargetSumWays() {}
			
	public static int findTargetSumWays(int[] nums, int S) {
        
		return 0;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 1, 1, 1, 1 }, 3, 5);
	}
	
	public void runTest(final int[] nums, final int sum, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums, sum });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
