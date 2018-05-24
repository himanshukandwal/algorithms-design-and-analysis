package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 417. Pacific Atlantic Water Flow
 *
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and
 * the "Atlantic ocean" touches the right and bottom edges.
 *
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 *
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 *
 * Note: The order of returned grid coordinates does not matter. Both m and n are less than 150.
 *
 * Example:
 *      Given the following 5x5 matrix:
 *
 *      Pacific ~   ~   ~   ~   ~
 *          ~  1   2   2   3  (5) *
 *          ~  3   2   3  (4) (4) *
 *          ~  2   4  (5)  3   1  *
 *          ~ (6) (7)  1   4   5  *
 *          ~ (5)  1   1   2   4  *
 *            *   *   *   *   * Atlantic
 *
 * Return: [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 *
 * @author hxkandwal
 */
public class PacificAtlanticWaterFlow extends AbstractCustomTestRunner {

    public List<int[]> _pacificAtlantic(int[][] m) {
        List<int[]> ans = new ArrayList<>();
        if (m.length == 0) return ans;

        boolean[][] p = new boolean [m.length][m [0].length], a = new boolean [m.length][m [0].length];
        for (int r = 0; r < m.length; r ++) { dfs (m, p, r, 0); dfs (m, a, r, m [0].length - 1); }
        for (int c = 0; c < m [0].length; c ++) { dfs (m, p, 0, c); dfs (m, a, m.length - 1, c); }

        for (int r = 0; r < m.length; r ++)
            for (int c = 0; c < m [0].length; c ++)
                if (p [r][c] && a [r][c]) ans.add (new int [] { r, c });
        return ans;
    }

    int [] rdir = { 0, 1, 0, -1 };
    int [] cdir = { 1, 0, -1, 0 };

    private void dfs (int[][] m, boolean[][] visited, int r, int c) {
        visited [r][c] = true;
        for (int idx = 0; idx < 4; idx ++) {
            int nr = r + rdir [idx], nc = c + cdir [idx];
            if (nr < 0 || nr >= m.length || nc < 0 || nc >= m [0].length || visited [nr][nc]) continue;

            if (m [r][c] <= m [nr][nc]) dfs (m, visited, nr, nc);
        }

    }
}
