package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 329. Longest Increasing Path in a Matrix
 * 
 * Given an integer matrix, find the length of the longest increasing path.
 * 
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or 
 * move outside of the boundary (i.e. wrap-around is not allowed).
 * 
 * Example 1:
 * 		nums = [[9,9,4],
 * 				[6,6,8],
 * 				[2,1,1]
 * 			   ]
 * 		Return 4
 * 		The longest increasing path is [1, 2, 6, 9].
 * 
 * Example 2:
 * 		nums = [[3,4,5],
 * 				[3,2,6],
 * 				[2,2,1]
 * 			   ]
 * 		Return 4
 * 		The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * 
 * @author Hxkandwal
 */
public class LongestIncreasingPathInAMatrix extends AbstractCustomTestRunner {

	public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        int [][] dp = new int [matrix.length][matrix [0].length];
        for (int r = 0; r < matrix.length; r ++) 
            for (int c = 0; c < matrix [0].length; c ++) dp [r][c] = -1;
        
        int ans = 0;
        for (int r = 0; r < matrix.length; r ++) 
            for (int c = 0; c < matrix [0].length; c ++) 
                if (dp [r][c] == -1) ans = Math.max (ans, dfs (dp, matrix, r, c));
        
        return ans;
    }
    
    private int [] rdir = { 1, 0, -1, 0 };
    private int [] cdir = { 0, 1, 0, -1 };
    
    private int dfs (int [][] dp, int [][] matrix, int row, int col) {
        if (dp [row][col] >= 0) return dp [row][col];
        int ans = 1;
        for (int idx = 0; idx < 4; idx ++) {
            int r = row + rdir [idx], c = col + cdir [idx];
            if (r < 0 || r >= matrix.length || c < 0 || c >= matrix [0].length || matrix [r][c] <= matrix [row][col]) continue;
            ans = Math.max (ans, dfs (dp, matrix, r, c) + 1);   
        }
        return dp [row][col] = ans;
    }
}
