package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 644. Maximum Average Subarray II
 * 
 * Given an array consisting of n integers, find the contiguous subarray whose length is greater than or equal to k that has 
 * the maximum average value. And you need to output the maximum average value.
 * 
 * Example 1:
 * 		Input	: [1,12,-5,-6,50,3], k = 4
 * 		Output	: 12.75
 * 
 * 		Explanation:	when length is 5, maximum average value is 10.8,
 * 						when length is 6, maximum average value is 9.16667.
 * 						Thus return 12.75.
 * 
 * Note:
 * 	-	1 <= k <= n <= 10,000.
 * 	-	Elements of the given array will be in range [-10,000, 10,000].
 * 	-	The answer with the calculation error less than 10-5 will be accepted.
 * 
 * @author Hxkandwal
 */
public class MaximumAverageSubarrayII extends AbstractCustomTestRunner {
	
	private static MaximumAverageSubarrayII _instance = new MaximumAverageSubarrayII();

	public double _findMaxAverage(int[] nums, int k) {
		double ans = Integer.MIN_VALUE;
        
        int [][] dp = new int [nums.length][nums.length];
        for (int r = 0; r < nums.length; r ++) {
            int sum = 0;
            for (int c = r; c < nums.length; c ++)  dp [r][c] = (sum += nums [c]);
        }
        
        for (;k <= nums.length; k ++)
            for (int idx = 0; idx + k  - 1 < nums.length; idx ++)
                ans = Math.max (ans, dp [idx][idx + k - 1] * 1d/k);
        
        return ans; 
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 12, -5, -6, 50, 3 }, 4, 12.75d);
	}

	public void runTest(final int[] nums, int k, final double expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums, k });

		for (Object answer : answers)
			assertThat((Double) answer).isWithin(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
