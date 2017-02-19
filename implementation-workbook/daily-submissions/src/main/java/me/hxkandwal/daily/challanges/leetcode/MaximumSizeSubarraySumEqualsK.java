package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 325. Maximum Size Subarray Sum Equals k
 * 
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 * 
 * Note: The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
 * 
 * Example 1:
 * 		Given nums = [1, -1, 5, -2, 3], k = 3, return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
 * 
 * Example 2:
 * 		Given nums = [-2, -1, 2, 1], k = 1, return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 * 
 * Follow Up: Can you do it in O(n) time?
 * 
 * @author Hxkandwal
 */
public class MaximumSizeSubarraySumEqualsK extends AbstractCustomTestRunner {
	
	private static MaximumSizeSubarraySumEqualsK _instance = new MaximumSizeSubarraySumEqualsK();
	
	private MaximumSizeSubarraySumEqualsK() {}
	
	// bruteforce O(n^2) solution
	public int maxSubArrayLen(int[] nums, int k) {
        int max = 0, localsum = 0;
        for (int idx = 0; idx < nums.length; idx ++) {
            localsum = 0;
            for (int innerIdx = idx; innerIdx < nums.length; innerIdx ++) {
                localsum = localsum + nums [innerIdx];
                if (localsum == k) max = Math.max (max, innerIdx - idx + 1);
            }
        }
        return max;
    }

}
