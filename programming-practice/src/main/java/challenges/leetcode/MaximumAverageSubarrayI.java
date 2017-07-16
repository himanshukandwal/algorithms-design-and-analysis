package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 643. Maximum Average Subarray I
 * 
 * Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. 
 * And you need to output the maximum average value.
 * 
 * Example 1:
 * 		Input	: [1,12,-5,-6,50,3], k = 4
 * 		Output	: 12.75
 * 
 * 		Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
 * 
 * Note:
 * 	-	1 <= k <= n <= 30,000.
 * 	-	Elements of the given array will be in the range [-10,000, 10,000].
 * 
 * @author Hxkandwal
 */
public class MaximumAverageSubarrayI extends AbstractCustomTestRunner {

	public double findMaxAverage(int[] nums, int k) {
        double ans = Integer.MIN_VALUE;
        for (int idx = 0, start = 0, sum = 0; idx <= nums.length; idx ++) {
            if (idx - start < k) sum += nums [idx];
            else {
                ans = Math.max (ans, sum * 1d/k);
                if (idx < nums.length) { 
                    sum -= nums [start ++];
                    sum += nums [idx];
                }
            }
        }
        return ans;
    }
	
}
