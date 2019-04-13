package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 713. Subarray Product Less Than K
 *
 * Your are given an array of positive integers nums.
 *
 * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.
 *
 * Example 1:
 *              Input: nums = [10, 5, 2, 6], k = 100
 *              Output: 8
 *              Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 *                           Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 *
 * Note:
 *  0 < nums.length <= 50000.
 *  0 < nums[i] < 1000.
 *  0 <= k < 10^6.
 *
 * @author Hxkandwal
 */
public class SubarrayProductLessThanK extends AbstractCustomTestRunner {

    public int _numSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0, start = 0;
        long val = 1;
        for (int idx = 0; idx < nums.length; idx ++) {
            while (start <= idx && val * nums [idx] > Integer.MAX_VALUE) val /= nums [start ++];    // safety check (adds time as long multiplication takes time)
            while (start <= idx && val * nums [idx] >= k) val /= nums [start ++];                   // maintenance
            if (start <= idx) {
                val *= nums [idx];
                ans += (idx - start + 1);
            } else {
                val = 1;
            }
        }
        return ans;
    }

    public int _numSubarrayProductLessThanKFaster(int[] nums, int k) {
        int ans = 0, start = 0, val = 1;
        for (int idx = 0; idx < nums.length; idx ++) {
            val *= nums [idx];
            while (start <= idx && val >= k) val /= nums [start ++];
            ans += (idx - start + 1);
        }
        return ans;
    }
}
