package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 64. Minimum Path Sum
 * 
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum 
 * of all numbers along its path.
 * 
 * @author Hxkandwal
 */
public class MinimumPathSum extends AbstractCustomTestRunner {
	
	private static MinimumPathSum _instance = new MinimumPathSum();
	
	public int _minPathSum(int[][] grid) {
		if (grid.length == 0) return 0;
        int [][] dp = new int [grid.length][grid [0].length];
        
        dp [0][0] = grid [0][0];
        for (int row = 1; row < grid.length; row ++) dp [row][0] = grid [row][0] + dp [row - 1][0];
        for (int col = 1; col < grid [0].length; col ++) dp [0][col] = grid [0][col] + dp [0][col - 1];
        
        for (int row = 1; row < grid.length; row ++) 
            for (int col = 1; col < grid [0].length; col ++)
                dp [row][col] = grid [row][col] + Math.min (dp [row - 1][col], dp [row][col - 1]);  
            
        return dp [grid.length - 1][grid [0].length - 1];
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[][] { new int [] { 1, 3, 1 }, 
										new int [] { 1, 5, 1 },
										new int [] { 4, 2, 1 }}, 7);
	}
	
	public void runTest(final int[][] grid, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { grid });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
