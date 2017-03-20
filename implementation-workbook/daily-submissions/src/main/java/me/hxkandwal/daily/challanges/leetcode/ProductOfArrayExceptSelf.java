package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 238. Product of Array Except Self
 * 
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of 
 * all the elements of nums except nums[i].
 * 
 * Solve it without division and in O(n).
 * For example, given [1,2,3,4], return [24,12,8,6].
 * 
 * Follow up: Could you solve it with constant space complexity? (Note: The output array does not count as extra space 
 * 			  for the purpose of space complexity analysis.)
 * 
 * @author Hxkandwal
 */
public class ProductOfArrayExceptSelf extends AbstractCustomTestRunner {

	/**
	 * nums   = [1, 2, 3, 4]
	 * output = product of nums[left of i] * product of nums[right of i]
	 * 	 
	 * output = [
	 * 		  24, // left: init=1     nums[i]=1  right: 2 * 3 * 4 
	 * 		  12, // left: 1          nums[i]=2  right: 3 * 4
	 * 		  8,  // left: 1 * 2      nums[i]=3  right: 4
	 * 		  6,  // left: 1 * 2 * 3  nums[i]=4  right: init=1 
	 * ]
	 **/
    public int[] productExceptSelf(int[] nums) {
        int [] res = new int [nums.length];
        for (int idx = 0, temp = 1; idx < nums.length; idx ++) { res [idx] = temp; temp *= nums [idx]; }
        for (int idx = nums.length - 1, temp = 1; idx >= 0; idx --) { res [idx] *= temp; temp *= nums [idx]; }
        return res;
    }
    
}
