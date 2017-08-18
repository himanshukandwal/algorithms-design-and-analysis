package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

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

	public int _findTargetSumWays(int[] nums, int S) {
		return dfs (nums, S, 0);
	}

	private int dfs (int [] nums, int S, int index) {
		if (index >= nums.length) return (S == 0 ? 1 : 0);
		return dfs (nums, S - nums [index], index + 1) + dfs (nums, S + nums [index], index + 1);
	}

	// another approach
	public int findTargetSumWays(int[] nums, int s) {
		int sum = 0;
		for (int n : nums) sum += n;
		return sum < s || (s + sum) % 2 > 0 ? 0 : subsetSum(nums, (s + sum) >>> 1);
	}

	public int subsetSum(int[] nums, int s) {
		int[] dp = new int[s + 1];
		dp[0] = 1;
		for (int n : nums)
			for (int i = s; i >= n; i--)
				dp[i] += dp[i - n];
		return dp[s];
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
