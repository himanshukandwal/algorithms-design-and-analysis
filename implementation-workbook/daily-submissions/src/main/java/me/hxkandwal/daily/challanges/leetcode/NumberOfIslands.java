package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
	
	private NumberOfIslands() {}
	
	public int _numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        boolean [][] seen = new boolean [grid.length][grid[0].length];
        
        int count = 0;
        for (int row = 0; row < grid.length; row ++) {
            for (int col = 0; col < grid [0].length; col ++) {
                if (grid [row][col] == '1' && !seen [row][col]) {
                    count ++;
                    visit(grid, seen, row, col);
                }
            }
        }
        return count;
    }
    
    public void visit(char[][] grid, boolean [][] seen, int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer (new int[] { row, col });
        
        while (! queue.isEmpty()) {
            int[] point = queue.poll();
            seen [point[0]][point[1]] = true;
            
            if (point[0] + 1 < grid.length && !seen[point[0] + 1][point[1]] && grid [point[0] + 1][point[1]] == '1')
                queue.offer (new int [] { point[0] + 1, point[1] });
                
            if (point[0] - 1 >= 0 && !seen[point[0] - 1][point[1]] && grid [point[0] - 1][point[1]] == '1')
                queue.offer (new int [] { point[0] - 1, point[1] });                
            
            if (point[1] + 1 < grid[0].length && !seen [point[0]][point[1] + 1] && grid [point[0]][point[1] + 1] == '1')
                queue.offer (new int [] { point[0], point[1] + 1 });

            if (point[1] - 1 >= 0 && !seen [point[0]][point[1] - 1] && grid [point[0]][point[1] - 1] == '1')
                queue.offer (new int [] { point[0], point[1] - 1 });                
        }
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
