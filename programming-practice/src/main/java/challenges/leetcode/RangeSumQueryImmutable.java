package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 303. Range Sum Query - Immutable
 * 
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * 
 * Example:
 * 		Given nums = [-2, 0, 3, -5, 2, -1]
 * 		sumRange(0, 2) -> 1 
 * 		sumRange(2, 5) -> -1 
 * 		sumRange(0, 5) -> -3
 * 
 * Note: 
 * 	a) You may assume that the array does not change.
 * 	b) There are many calls to sumRange function.
 * 
 * @author Hxkandwal
 */
public class RangeSumQueryImmutable extends AbstractCustomTestRunner {

	private int[] nums;
	
	public RangeSumQueryImmutable (int[] nums) {
        this.nums = nums;
        for (int idx = 1; idx < nums.length; idx ++) nums [idx] += nums [idx - 1];
    }
    
    public int sumRange(int i, int j) {
        return nums [j] - ((i > 0) ? nums [i - 1] : 0);
    }
    
}