package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 463. Island Perimeter
 * 
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected 
 * horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more 
 * connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a 
 * square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 * 
 * Example:
 * 
 * 		[[0,1,0,0],
 * 		 [1,1,1,0],
 * 		 [0,1,0,0],
 * 		 [1,1,0,0]]
 * 
 * Answer: 16
 * 
 * @author Hxkandwal
 */
public class IslandPerimeter extends AbstractCustomTestRunner {
	
	private static IslandPerimeter _instance = new IslandPerimeter();
	
	private IslandPerimeter() {}
	
	public static int _islandPerimeter(int[][] grid) {
		int perimeter = 0;

        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++)
                if (grid[i][j] == 1) {
                	perimeter += 4; // count islands
                	
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) perimeter -= 2; // count down neighbours
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1) perimeter -= 2; // count right neighbours
                }

        return perimeter;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [][] {{ 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 1, 1, 0, 0 }}, 16);
	}

	public void runTest(final int[][] grid, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { grid });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
	
}
