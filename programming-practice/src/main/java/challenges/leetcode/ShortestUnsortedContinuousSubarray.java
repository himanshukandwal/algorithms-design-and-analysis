package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 581. Shortest Unsorted Continuous Subarray
 * 
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the 
 * whole array will be sorted in ascending order, too.
 * 
 * You need to find the shortest such subarray and output its length.
 * 
 * Example 1:
 * 		Input: [2, 6, 4, 8, 10, 9, 15]
 * 		Output: 5
 * 		Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * 
 * Note:
 * 	-	Then length of the input array is in range [1, 10,000].
 * 	-	The input array may contain duplicates, so ascending order here means <=.
 * 
 * @author Hxkandwal
 */
public class ShortestUnsortedContinuousSubarray extends AbstractCustomTestRunner {

	public int findUnsortedSubarrayBest (int[] nums) {
        int start = -1, end = -2, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int idx = 0; idx < nums.length; idx ++) {
            max = Math.max (max, nums [idx]);
            min = Math.min (min, nums [nums.length - 1 - idx]);
            if (nums [idx] < max) end = idx;
            if (nums [nums.length - 1 - idx] > min) start = nums.length - 1 - idx;
        }
        return end - start + 1;
    }
	
	// get narrower and narrower
	public int findUnsortedSubarray(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int idx = start; idx <= end; idx ++) {
                min = Math.min (min, nums [idx]);
                max = Math.max (max, nums [idx]);
            }
            if (nums [start] == min) start ++;
            else if (nums [end] == max) end --;
            else break;
        }
        return start >= end ? 0 : end - start + 1;
    }
	
}
