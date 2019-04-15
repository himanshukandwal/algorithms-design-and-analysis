package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 200. Number of Islands
 * 
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and 
 * is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * 
 * Example 1:
 * 		11110
 * 		11010
 * 		11000
 * 		00000
 * 
 * Answer: 1
 * 
 * Example 2:
 * 		11000
 * 		11000
 * 		00100
 * 		00011
 * 
 * Answer: 3
 * 
 * @author Hxkandwal
 */
public class NumberOfIslands extends AbstractCustomTestRunner {
	
	private static NumberOfIslands _instance = new NumberOfIslands();
	
    private int [] rdir = { 1, 0, -1, 0 };
    private int [] cdir = { 0, 1, 0, -1 };
    
	// better approach
	public int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        int count = 0;
        for (int r = 0; r < grid.length; r ++) 
            for (int c = 0; c < grid [0].length; c ++) if (grid [r][c] == '1') { count ++; dfs (grid, r, c); }
        return count;
    }
    
    private void dfs (char [][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid [0].length || grid [row][col] == '0') return;
        grid [row][col] = '0';
        for (int idx = 0; idx < 4; idx ++) dfs (grid, row + rdir [idx], col + cdir [idx]);
    }
	
    // conventional approach
    public int _numIslandsConventional (char[][] grid) {
        if (grid.length == 0) return 0;
        boolean [][] seen = new boolean [grid.length][grid [0].length];
        int count = 0;
        for (int r = 0; r < grid.length; r ++) 
            for (int c = 0; c < grid [0].length; c ++) 
                if (!seen [r][c] && grid [r][c] == '1') { count ++; dfs (seen, grid, r, c); }
        return count;
    }
    
    private void dfs (boolean [][] seen, char [][] grid, int row, int col) {
        if (row < 0 || row >= seen.length || col < 0 || col >= grid [0].length 
            || grid [row][col] == '0' || seen [row][col]) return;
        seen [row][col] = true;
        for (int idx = 0; idx < 4; idx ++) dfs (seen, grid, row + rdir [idx], col + cdir [idx]);
    }
    
    // driver method
   	public static void main(String[] args) {
		_instance.runTest(new char [][] { "11000".toCharArray(), 
										  "11000".toCharArray(),
										  "00100".toCharArray(),
										  "00011".toCharArray() }, 3);
   	}

   	public void runTest(final char[][] grid, final int expectedOutput) {
   		List<Object> answers = runAll(getClass(), new Object[] { grid });

   		for (Object answer : answers)
   				assertThat((Integer) answer).isEqualTo(expectedOutput);

   		System.out.println("ok!");
   	}    

}
