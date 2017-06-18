package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 624. Maximum Distance in Arrays
 * 
 * Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different arrays (each array picks 
 * one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a-b|. 
 * 
 * Your task is to find the maximum distance.
 * 
 * Example 1:
 * 		Input:	[[1,2,3],
 * 				 [4,5],
 * 				 [1,2,3]]
 * 
 * 		Output: 4
 * 
 * 		Explanation: One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
 * 
 * Note:
 * 	-	Each given array will have at least 1 number. There will be at least two non-empty arrays.
 * 	-	The total number of the integers in all the m arrays will be in the range of [2, 10000].
 * 	-	The integers in the m arrays will be in the range of [-10000, 10000].
 * 
 * @author Hxkandwal
 */
public class MaximumDistanceInArrays extends AbstractCustomTestRunner {

	public int maxDistance(int[][] arrays) {
        int ans = Integer.MIN_VALUE, min = arrays [0][0], max = arrays [0][arrays [0].length - 1];
        
        for (int idx = 1; idx < arrays.length; idx ++) {
            ans = Math.max (ans, Math.max (max - arrays [idx][0], arrays [idx][arrays [idx].length - 1] - min));
            max = Math.max (max, arrays [idx][arrays [idx].length - 1]);
            min = Math.min (min, arrays [idx][0]);
        }
        
        return ans;
    }
	
}
