package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 63. Unique Paths II
 * 
 * Follow up for "Unique Paths":
 * 
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * 
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * 
 * For example,
 * 	There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * 
 * 	[
 *  	[0,0,0],
 *   	[0,1,0],
 *   	[0,0,0]
 * 	]
 * 
 * The total number of unique paths is 2.
 * 
 * Note: m and n will be at most 100.
 * 
 * @author Hxkandwal
 */
public class UniquePathsII extends AbstractCustomTestRunner {
	
	public int uniquePathsWithObstaclesSpaceEfficient(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0 || obstacleGrid[0][0] == 1) return 0;
        int m = obstacleGrid.length, n = obstacleGrid [0].length;
        int [] dp = new int [n];
        dp [0] = 1;
        
        for (int row = 0; row < m; row ++) {
            for (int col = 0; col < n; col ++) {
                if (obstacleGrid [row][col] == 1) 
                    dp [col] = 0;
                else if (col > 0) dp [col] = dp [col] + dp [col - 1];
            }
        }
                                    
        return dp [n - 1];
    }

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0 || obstacleGrid[0][0] == 1) return 0;
        int m = obstacleGrid.length, n = obstacleGrid [0].length;
        int [][] dp = new int [m][n];
        for (int row = 0; row < m; row ++) dp [row][0] = (obstacleGrid [row][0] == 0 && (row == 0 || row - 1 >= 0 && dp [row - 1][0] != -1)) ? 1 : -1; 
        for (int col = 0; col < n; col ++) dp [0][col] = (obstacleGrid [0][col] == 0 && (col == 0 || col - 1 >= 0 && dp [0][col - 1] != -1)) ? 1 : -1;
        
        for (int row = 1; row < m; row ++)
            for (int col = 1; col < n; col ++)
                if (obstacleGrid [row][col] == 0) 
                    dp [row][col] = (dp [row - 1][col] == -1 ? 0 : dp [row - 1][col]) + (dp [row][col - 1] == -1 ? 0 : dp [row][col - 1]);
                    
        return dp [m - 1][n - 1] == -1 ? 0 : dp [m - 1][n - 1];
    }
	
}
