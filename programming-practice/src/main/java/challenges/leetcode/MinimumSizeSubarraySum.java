package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 209. Minimum Size Subarray Sum
 * 
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. 
 * If there isn't one, return 0 instead.
 * 
 * For example, 
 * 		given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length under the problem constraint.
 *  
 * @author Hxkandwal
 */
public class MinimumSizeSubarraySum extends AbstractCustomTestRunner {
	
    public int _minSubArrayLen(int s, int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (int idx = 0, start = 0, sum = 0; idx < nums.length; idx ++) {
            sum += nums [idx];
            while (sum >= s) {
                ans = Math.min (ans,  idx - start + 1);
                sum -= nums [start ++];
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

}
