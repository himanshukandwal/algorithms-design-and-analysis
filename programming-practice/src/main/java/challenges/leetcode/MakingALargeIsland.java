package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;

/**
 * 827. Making A Large Island
 *
 * In a 2D grid of 0s and 1s, we change at most one 0 to a 1. After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).
 *
 * Example 1:
 *
 *      Input: [[1, 0], [0, 1]]
 *      Output: 3
 *      Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 *
 * Example 2:
 *
 *      Input: [[1, 1], [1, 0]]
 *      Output: 4
 *      Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 1.
 *
 * Example 3:
 *
 *      Input: [[1, 1], [1, 1]]
 *      Output: 4
 *      Explanation: Can't change any 0 to 1, only one island with area = 1.
 *
 * Notes:
 *
 *   1 <= grid.length = grid[0].length <= 50.
 *   0 <= grid[i][j] <= 1.
 *
 * @author hxkandwal
 */
public class MakingALargeIsland extends AbstractCustomTestRunner {

    public static MakingALargeIsland _instance = new MakingALargeIsland();

    // better idea
    public int _largestIslandBetter(int[][] grid) {
        int max = 0, m = grid.length, n = grid[0].length;
        boolean hasZero = false; //To check if there is any zero in the grid
        for(int i = 0; i < grid.length; i++){
            for(int j = 0;j<grid[0].length;j++){
                if(grid[i][j] == 0){
                    grid[i][j] = 1;
                    max = Math.max(max,dfs(i,j,grid,new boolean[m][n]));
                    if(max == m*n) return max;
                    grid[i][j] = 0;
                    hasZero = true;
                }
            }
        }
        return hasZero?max:m*n;
    }

    private int dfs(int i, int j, int[][] grid,boolean[][] visited){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0||visited[i][j]) return 0;
        visited[i][j] = true;
        int result = 1+dfs(i-1,j,grid,visited)+dfs(i+1,j,grid,visited)+dfs(i,j+1,grid,visited)+dfs(i,j-1,grid,visited);
        return result;
    }


    class Node {
        Node p;
        int x, y;
        public Node (int x, int y) { this.x = x; this.y = y; this.p = this; }
    }

    public int _largestIsland(int[][] g) {
        Node[][] nodes = new Node[g.length][g [0].length];
        for (int r = 0; r < g.length; r ++) for (int c = 0; c < g [0].length; c ++) nodes [r][c] = new Node (r, c);

        for (int r = 0; r < g.length; r ++)
            for (int c = 0; c < g [0].length; c ++)
                if (g [r][c] == 1) dfs (nodes, g, r, c);

        Map<Node, Integer> islandSize = new HashMap<>();
        for (int r = 0; r < g.length; r ++)
            for (int c = 0; c < g [0].length; c ++)
                if (g [r][c] == 1) {
                    Node n = find (nodes [r][c]);
                    islandSize.put (n, islandSize.getOrDefault(n, 0) + 1);
                }

        int max = 1;
        for (int v : islandSize.values()) max = Math.max(max, v);

        for (int r = 0; r < g.length; r ++) {
            for (int c = 0; c < g [0].length; c ++) {
                if (g [r][c] == 0) {
                    int size = 0;
                    Set<Node> seen = new HashSet<>();
                    if (r + 1 < g.length && g [r + 1][c] == 1 && !seen.contains(find(nodes [r + 1][c]))) {
                        Node n = find(nodes [r + 1][c]);
                        seen.add(n);
                        size += islandSize.get (n);
                    }

                    if (c + 1 < g [0].length && g [r][c + 1] == 1 && !seen.contains(find(nodes [r][c + 1]))) {
                        Node n = find(nodes [r][c + 1]);
                        seen.add(n);
                        size += islandSize.get (n);
                    }

                    if (r - 1 >= 0 && g [r - 1][c] == 1 && !seen.contains(find(nodes [r - 1][c]))) {
                        Node n = find(nodes [r - 1][c]);
                        seen.add(n);
                        size += islandSize.get (n);
                    }

                    if (c - 1 >= 0 && g [r][c - 1] == 1 && !seen.contains(find(nodes [r][c - 1]))) {
                        Node n = find(nodes [r][c - 1]);
                        seen.add(n);
                        size += islandSize.get (n);
                    }

                    if (size > 0) size ++;
                    max = Math.max (max, size);
                }
            }
        }
        return max;
    }

    private void dfs (Node[][] n, int [][] g, int r, int c) {
        if (n [r][c].p != n [r][c]) return;
        Node node = n [r][c];
        dfs (n, g, node, r + 1, c);
        dfs (n, g, node, r, c + 1);
        dfs (n, g, node, r - 1, c);
        dfs (n, g, node, r, c - 1);
    }

    private void dfs (Node[][] n, int [][] g, Node p, int r, int c) {
        if (r < 0 || r >= g.length || c < 0 || c >= g[0].length || g [r][c] == 0 || n [r][c].p != n [r][c] || n [r][c] == p) return;
        Node node = n [r][c];
        node.p = p;
        dfs (n, g, p, r + 1, c);
        dfs (n, g, p, r, c + 1);
        dfs (n, g, p, r - 1, c);
        dfs (n, g, p, r, c - 1);
    }

    private Node find (Node n) {
        if (n.p != n)
            n.p = find (n.p);
        return n.p;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int[][] { { 1, 0 }, { 0, 1 } }, 3);
        _instance.runTest(new int[][] { { 1, 1 }, { 1, 0 } }, 4);
    }

    public void runTest(final int[][] g, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { g });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
