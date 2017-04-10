package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 213. House Robber II
 * 
 * Note: This is an extension of House Robber.
 * 
 * After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. 
 * This time, all houses at this place are arranged in a circle. 
 * 
 * That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the 
 * previous street.
 * 
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight 
 * without alerting the police.
 * 
 * @author Hxkandwal
 */
public class HouseRobberII extends AbstractCustomTestRunner {
	
	public int rob(int[] nums) {
        if (nums.length == 1) return nums [0];
        return Math.max (robRange(nums, 0, nums.length - 2), robRange(nums, 1, nums.length - 1));
    }
    
     public int robRange(int[] nums, int start, int end) {
        int fprev = 0, sprev = 0;
        for (int idx = start; idx <= end; idx ++) {
            int cur = Math.max (sprev + nums [idx], fprev);
            sprev = fprev;
            fprev = cur;
        }
        return fprev;
    }

}
