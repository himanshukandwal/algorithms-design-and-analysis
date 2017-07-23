package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 130. Surrounded Regions
 * 
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * 
 * For example,
 * 		X X X X
 * 		X O O X
 * 		X X O X
 * 		X O X X
 * 
 * After running your function, the board should be:
 * 		X X X X
 * 		X X X X
 * 		X X X X
 * 		X O X X
 * 
 * @author Hxkandwal
 */
public class SurroundedRegions extends AbstractCustomTestRunner {
	
	private static SurroundedRegions _instance = new SurroundedRegions();
	
	public void solve(char[][] board) {
        if (board.length == 0) return;
        for (int r = 0; r < board.length; r ++) if (board [r][0] == 'O') dfs (board, r, 0);
        for (int c = 0; c < board [0].length; c ++) if (board [0][c] == 'O') dfs (board, 0, c);
        for (int r = 0; r < board.length; r ++) if (board [r][board [0].length - 1] == 'O') dfs (board, r, board [0].length - 1);
        for (int c = 0; c < board [0].length; c ++) if (board [board.length - 1][c] == 'O') dfs (board, board.length - 1, c);
        
        for (int r = 0; r < board.length; r ++)
            for (int c = 0; c < board [0].length; c ++)
                if (board [r][c] == 'S') board [r][c] = 'O';
                else if (board [r][c] == 'O') board [r][c] = 'X';
    }
    
    int [] rowdir = { 0, 1, 0, -1 };
    int [] coldir = { 1, 0, -1, 0 };
    
    private void dfs (char [][] board, int row, int col) {
        board [row][col] = 'S';
        for (int idx = 0; idx < 4; idx ++) {
            int r = row + rowdir [idx], c = col + coldir [idx];
            // do not let it make calls to edge again.
            if (r < 1 || r >= board.length - 1 || c < 1 || c >= board [0].length - 1 || board [r][c] != 'O') continue;
            dfs (board, r, c);
        }
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new char [][] { "O".toCharArray() }, new char [][] { "O".toCharArray() });
		_instance.runTest(new char [][] { "XXXX".toCharArray(), 
										  "XOOX".toCharArray(), 
										  "XXOX".toCharArray(), 
										  "XOXX".toCharArray()},
						  new char [][] { "XXXX".toCharArray(),
										  "XXXX".toCharArray(),
										  "XXXX".toCharArray(),
										  "XOXX".toCharArray()});
	}

	public void runTest(final char[][] board, final char[][] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { board });

		for (Object answer : answers)
			assertThat((char[][]) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}    

}
