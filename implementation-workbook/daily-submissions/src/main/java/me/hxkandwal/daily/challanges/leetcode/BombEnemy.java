package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 361. Bomb Enemy
 * 
 * @author Hxkandwal
 */
public class BombEnemy extends AbstractCustomTestRunner {

	public int maxKilledEnemies(char[][] grid) {
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
	
}
