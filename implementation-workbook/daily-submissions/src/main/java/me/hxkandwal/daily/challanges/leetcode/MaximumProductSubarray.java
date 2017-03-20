package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 152. Maximum Product Subarray
 * 
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * 
 * For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
 *  
 * @author Hxkandwal
 */
public class MaximumProductSubarray extends AbstractCustomTestRunner {
	
    public int maxProduct(int[] nums) {
    	if (nums.length == 0) return 0;
        int max = nums [0], min = max, maxProduct = max;
        for (int idx = 1; idx < nums.length; idx ++) {
            int num = nums [idx], mx = max, mn = min;
            max = Math.max (Math.max (mx * num, num), Math.max (mn * num, num)); 
            min = Math.min (Math.min (mx * num, num), Math.min (mn * num, num)); 
            maxProduct = Math.max (maxProduct, max);
        }
        return maxProduct;
    }
    
}
