package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 198. House Robber
 * 
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you 
 * from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were 
 * broken into on the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without 
 * alerting the police.
 * 
 * @author Hxkandwal
 */
public class HouseRobber extends AbstractCustomTestRunner {
	
	// after analyzing states and dependencies needed, optimal solution.
    public int robBetter(int[] nums) {
        int fprev = 0, sprev = 0;
        for (int n : nums) {
            int cur = Math.max (sprev + n, fprev);
            sprev = fprev;
            fprev = cur;
        }
        return fprev;
    }
    
	// original solution.
	public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums [0];
        if (nums.length == 2) return Math.max (nums [0], nums [1]);
        
        int [] dp = new int [nums.length];
        dp [0] = nums [0];
        dp [1] = Math.max(nums [0], nums [1]);
        for (int idx = 2; idx < nums.length; idx ++)
            dp [idx] = Math.max (dp [idx - 1], dp [idx - 2] + nums [idx]);
            
        return dp [nums.length - 1];
    }

}
