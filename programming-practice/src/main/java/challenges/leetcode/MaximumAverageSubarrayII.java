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

	public double findMaxAverage(int[] nums, int k) {
        double max_val = Integer.MIN_VALUE;
        double min_val = Integer.MAX_VALUE;
        for (int n: nums) {
            max_val = Math.max(max_val, n);
            min_val = Math.min(min_val, n);
        }
        double prev_mid = max_val, error = Integer.MAX_VALUE;
        while (error > 0.00001) {
            double mid = (max_val + min_val) * 0.5;
            if (check(nums, mid, k))
                min_val = mid;
            else
                max_val = mid;
            error = Math.abs(prev_mid - mid);
            prev_mid = mid;
        }
        return min_val;
    }
    public boolean check(int[] nums, double mid, int k) {
        double sum = 0, prev = 0, min_sum = 0;
        for (int i = 0; i < k; i++)
            sum += nums[i] - mid;
        if (sum >= 0)
            return true;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - mid;
            prev += nums[i - k] - mid;
            min_sum = Math.min(prev, min_sum);
            if (sum >= min_sum)
                return true;
        }
        return false;
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
