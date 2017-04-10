package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 419. Battleships in a Board
 * 
 * Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots 
 * are represented with '.'s. You may assume the following rules:
 * 
 * You receive a valid board, made of only battleships or empty slots.
 * Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) 
 * or Nx1 (N rows, 1 column), where N can be of any size.
 * 
 * At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
 * 
 * Example:
 * 		X..X
 * 		...X
 * 		...X
 * 
 * @author Hxkandwal
 */
public class BattleshipsInABoard extends AbstractCustomTestRunner {

	// O (n) solution with O (1) memory requirements
	public int countBattleshipsOptimized(char[][] board) {
        if (board == null || board.length == 0) return 0;
        int count = 0;
        for (int row = 0; row < board.length; row ++) {
            for (int col = 0; col < board [0].length; col ++) {
                if (board [row][col] == 'X' && (row == 0 || board [row - 1][col] != 'X')) {
                    count ++;
                    while (col + 1 < board[0].length && board [row][col + 1] == 'X') col ++;
                }
            }
        }
        return count;
    }
	
	// O (n) solution with O(n) memory requirements
	public int countBattleships(char[][] board) {
        if (board == null || board.length == 0) return 0;
        
        boolean[][] seen = new boolean [board.length][board[0].length];
        int count = 0;
        for (int row = 0; row < board.length; row ++) {
            for (int col = 0; col < board [0].length; col ++) {
                if (!seen [row][col] && board [row][col] == 'X') {
                    count ++;
                    int r = row, c = col;
                    while (c + 1 < board[0].length && board [r][c + 1] == 'X') { seen[r][c + 1] = true; c ++; }
                    while (r + 1 < board.length && board [r + 1][c] == 'X') { seen[r + 1][c] = true; r ++; }
                }
            }
        }
        return count;
    }
	
}
