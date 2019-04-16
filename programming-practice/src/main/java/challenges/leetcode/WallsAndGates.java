package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 286. Walls and Gates
 *
 * You are given a m x n 2D grid initialized with these three possible values.
 *      -1 - A wall or an obstacle.
 *      0 - A gate.
 *      INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 *
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 *
 * Example:
 *          Given the 2D grid:
 *
 *          INF  -1  0  INF
 *          INF INF INF  -1
 *          INF  -1 INF  -1
 *          0  -1 INF INF
 *
 *          After running your function, the 2D grid should be:
 *
 *          3  -1   0   1
 *          2   2   1  -1
 *          1  -1   2  -1
 *          0  -1   3   4
 *
 * @author Hxkandwal
 */
public class WallsAndGates extends AbstractCustomTestRunner {

    private int [] rdir = { 0, 1, 0, -1 };
    private int [] cdir = { 1, 0, -1, 0 };

    // multi-point BFS. multiple concentric circles origination strategy.
    public void _wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
        final int INF = Integer.MAX_VALUE;
        Queue<int[]> queue = new LinkedList<>();

        for (int r = 0; r < rooms.length; r ++)
            for (int c = 0; c < rooms [0].length; c ++)
                if (rooms [r][c] == 0) queue.offer (new int [] { r, c });

        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance ++;
            while (size -- > 0) {
                int [] rc = queue.poll();
                int r = rc [0], c = rc [1];

                for (int k = 0; k < 4; k ++) {
                    int nr = r + rdir [k], nc = c + cdir [k];
                    if (nr < 0 || nr >= rooms.length || nc < 0 || nc >= rooms [0].length || rooms [nr][nc] != INF) continue;
                    rooms [nr][nc] = distance;
                    queue.offer (new int [] { nr, nc });
                }
            }
        }
    }

    // dfs (conditional) -> faster
    public void _wallsAndGatesDFS(int[][] rooms) {
        for (int r = 0; r < rooms.length; r ++)
            for (int c = 0; c < rooms [0].length; c ++)
                if (rooms [r][c] == 0) dfs (rooms, r, c, 0);
    }

    private void dfs (int[][] g, int r, int c, int d) {
        // the g [r][c] <= d, will fail as when we are starting the dfs call, we have g [r][c] == 0, and d == 0, so keep g [r][c] < d only. Hence, allowing first call to come through.
        if (r < 0 || r >= g.length || c < 0 || c >= g [0].length || g [r][c] < d) return;

        g [r][c] = d;
        dfs (g, r, c + 1, d + 1);
        dfs (g, r + 1, c, d + 1);
        dfs (g, r, c - 1, d + 1);
        dfs (g, r - 1, c, d + 1);
    }
}
