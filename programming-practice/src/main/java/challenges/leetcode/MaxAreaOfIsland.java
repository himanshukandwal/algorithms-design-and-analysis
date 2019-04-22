package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 695. Max Area of Island
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.
 *
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 *
 * Example 1:
 *          [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *           [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *           [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *           [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *           [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *           [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *           [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *           [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 *
 *          Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
 *
 * Example 2:
 *          [[0,0,0,0,0,0,0,0]]
 *
 *          Given the above grid, return 0.
 *
 * Note: The length of each dimension in the given grid does not exceed 50.
 *
 * @author Hxkandwal
 */
public class MaxAreaOfIsland extends AbstractCustomTestRunner {

    public int _maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        for (int r = 0; r < grid.length; r ++)
            for (int c = 0; c < grid [0].length; c ++)
                if (grid [r][c] == 1) ans = Math.max (ans, dfs (grid, r, c, 0));
        return ans;
    }

    private int dfs (int[][] g, int r, int c, int count) {
        if (r < 0 || r >= g.length || c < 0 || c >= g [0].length || g [r][c] != 1) return count;
        g [r][c] = -1;
        count ++;
        count = dfs (g, r + 1, c, count);
        count = dfs (g, r, c + 1, count);
        count = dfs (g, r - 1, c, count);
        count = dfs (g, r, c - 1, count);
        return count;
    }
}
