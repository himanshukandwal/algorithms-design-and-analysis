package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

/**
 * 694. Number of Distinct Islands
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.
 *
 * Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or
 * reflected) to equal the other.
 *
 *      Example 1:
 *              11000
 *              11000
 *              00011
 *              00011
 *
 *      Given the above grid map, return 1.
 *
 *      Example 2:
 *              11011
 *              10000
 *              00001
 *              11011
 *
 *      Given the above grid map, return 3.
 *      Notice that:    11   and    1
 *                      1          11
 *                      are considered different island shapes, because we do not consider reflection / rotation.
 *
 * Note: The length of each dimension in the given grid does not exceed 50.
 */
public class NumberOfDistinctIslands extends AbstractCustomTestRunner {

    public int numDistinctIslands(int[][] grid) {
        Set<String> ans = new HashSet<>();
        for (int r = 0; r < grid.length; r ++) {
            for (int c = 0; c < grid[0].length; c ++) {
                if (grid [r][c] == 1) {
                    List<int[]> area = new ArrayList<>();
                    dfs (grid, r, c, area);

                    Collections.sort(area, (a, b) -> a [0] == b [0] ? a [1] - b [1] : a [0] - b [0]);
                    StringBuilder key = new StringBuilder();
                    int br = area.get(0)[0], bc = area.get(0)[1];
                    for (int idx = 0; idx < area.size(); idx ++) {
                        area.get(idx)[0] -= br; area.get(idx)[1] -= bc;
                        key.append(Arrays.toString(area.get(idx)));
                    }
                    ans.add (key.toString());
                }
            }
        }
        return ans.size();
    }

    private int[] rdir = { 0, 1, 0, -1 };
    private int[] cdir = { 1, 0, -1, 0 };

    private void dfs (int[][] grid, int r, int c, List<int[]> area) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid [0].length || grid [r][c] < 1) return;
        grid [r][c] = -1;
        for (int k = 0; k < 4; k ++) dfs (grid, r + rdir [k], c + cdir[k], area);
        area.add (new int [] { r, c });
    }

}
