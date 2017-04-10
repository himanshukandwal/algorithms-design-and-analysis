package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 62. Unique Paths
 * 
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * 
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner
 * of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 * 
 * @author Hxkandwal
 */
public class UniquePaths extends AbstractCustomTestRunner {
	
	/**
	 * binomial coefficients (https://rosettacode.org/wiki/Evaluate_binomial_coefficients#Java)
	 * nCk == n... n-(k-1)/ 1....k
	 * 
	 * In total the robot should walk m + n - 2 steps, m - 1 steps to right and n - 1 steps to bottom, so all what we should do is to select which m - 1 
	 * steps to be right, therefore the problem is actually a combination problem, 
	 * then we just need to calculate (n + m - 2)! / ((m - 1)! * (n - 1)!)
	 */
    public int uniquePathsMath(int m, int n) {
        m --; n --;
        long res = 1;
        for (int i = 1; i <= Math.min(n , m); i ++) 
            res = res * (m + n + 1 - i)/ i;					// <<<<<<<< 1 is added here to normalize as m = m - 1 and n = n - 1 (from 1st line), visualize with 10!/6!*4!
        
        return Math.toIntExact(res);
    }
    
	// with space utilization (overwriting)
	public int uniquePathsSpaceEfficient(int m, int n) {
        int [] dp = new int [n];
        for (int idx = 0; idx < n; idx ++) dp [idx] = 1;
        
        for (int row = 1; row < m; row ++)
            for (int col = 1; col < n; col ++) 
                dp [col] = dp [col] + dp [col - 1];
                
        return dp [n - 1];
    }
	
	// original idea.
	public int uniquePaths(int m, int n) {
        int [][] dp = new int [m][n];
        for (int idx = 0; idx < n; idx ++) dp [0][idx] = 1;
        for (int idx = 0; idx < m; idx ++) dp [idx][0] = 1;
        
        for (int row = 1; row < m; row ++)
            for (int col = 1; col < n; col ++) 
                dp [row][col] = dp [row - 1][col] + dp [row][col - 1];
        return dp [m - 1][n - 1];
    }

}
