package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 416. Partition Equal Subset Sum
 * 
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets 
 * such that the sum of elements in both subsets is equal.
 * 
 * Note:
 * 		Each of the array element will not exceed 100.
 * 		The array size will not exceed 200.
 * 
 * Example 1:
 * 		Input: [1, 5, 11, 5]
 * 		Output: true
 * 
 * 		Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * 
 * @author Hxkandwal
 */
public class PartitionEqualSubsetSum extends AbstractCustomTestRunner {
	
	public boolean _canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 == 1) return false; sum/= 2;
        boolean [][] dp = new boolean [nums.length] [sum + 1];
		
		for (int row = 0; row < nums.length; row ++) dp [row][0] = true;
		
		for (int row = 0; row < nums.length; row ++) {
			for (int col = 0; col <= sum; col ++) {
				if (nums [row] > col) 
					dp [row][col] = (row == 0) ? false : dp [row - 1][col];
				else 
					dp [row][col] = col - nums [row] == 0 ? true : ((row == 0) ? false : dp [row - 1][col] || dp [row - 1][col - nums [row]]);
			}
		}
		
		return dp [nums.length - 1][sum];
	}
	
}
