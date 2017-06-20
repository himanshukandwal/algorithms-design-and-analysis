package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 361. Bomb Enemy
 * 
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies 
 * you can kill using one bomb.
 * 
 * The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is 
 * too strong to be destroyed.
 * 
 * Note that you can only put the bomb at an empty cell.
 * 
 * Example:
 * 		For the given grid
 * 			
 * 			0 E 0 0
 * 			E 0 W E
 * 			0 E 0 0
 * 
 * 		return 3. (Placing a bomb at (1,1) kills 3 enemies)
 * 
 * @author Hxkandwal
 */
public class BombEnemy extends AbstractCustomTestRunner {

	public int maxKilledEnemiesOptimal(char[][] grid) {
        if (grid.length == 0) return 0;
        int rows = grid.length, cols = grid [0].length;
        int [] colsum = new int [cols];
        int maxKill = 0, rowsum = 0;
        
        for (int row = 0; row < rows; row ++) {
            for (int col = 0; col < cols; col ++) { 
                 if (col == 0 || grid [row][col - 1] == 'W') {
                     rowsum = 0;
                     for (int k = col; k < cols; k ++)  {
                        if (grid [row][k] == 'W') break;
                        if (grid [row][k] == 'E') rowsum ++;
                     }
                 }
                 if (row == 0 || grid [row - 1][col] == 'W') {
                     colsum [col] = 0;
                     for (int k = row; k < rows; k ++)  {
                        if (grid [k][col] == 'W') break;
                        if (grid [k][col] == 'E') colsum [col] ++;
                     }
                 }
                 
                 if (grid [row][col] == '0')
                    maxKill = Math.max (maxKill, rowsum + colsum [col]);
            }
        }
        return maxKill;
    }
	
	public int maxKilledEnemies(char[][] grid) {
        if (grid.length == 0) return 0;
        
        int max = 0;
        int [][] dp = new int [grid.length][grid [0].length];
        /** 
         * Ideal question of why we can't apply BFS/DFS here, every cell figures out for itself
         * we can't use one cells result for another, not recursive symmetry (fission, depth-fission)
         **/
        for (int row = 0; row < grid.length; row ++) {
            int col = 0;
            while (col < grid [row].length) {
                if (grid [row][col] == 'W') { col ++; continue; }
                else {
                    int val = 0, c = col;
                    for (; c < grid [row].length && grid [row][c] != 'W'; c ++) if (grid [row][c] == 'E') val ++;
                    while (col < c)  {
                        if (grid [row][col] == '0') max = Math.max (max, dp [row][col] += val);
                        col ++;
                    }
                }
            }
        }
        for (int col = 0; col < grid [0].length; col ++) {
            int row = 0;
            while (row < grid.length) {
                if (grid [row][col] == 'W') { row ++; continue; }
                else {
                    int val = 0, r = row;
                    for (; r < grid.length && grid [r][col] != 'W'; r ++) if (grid [r][col] == 'E') val ++;
                    while (row < r)  {
                        if (grid [row][col] == '0') max = Math.max (max, dp [row][col] += val);
                        row ++;
                    }
                }
            }
        }
        return max;
    }
	
}
