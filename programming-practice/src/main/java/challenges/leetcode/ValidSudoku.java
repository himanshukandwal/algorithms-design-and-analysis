package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * 36. Valid Sudoku
 * 
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * 
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * 
 * Note: A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
 * 
 * @author Hxkandwal
 *
 */
public class ValidSudoku extends AbstractCustomTestRunner {
	
	private static ValidSudoku _instance = new ValidSudoku();

	public boolean isValidSudoku(char[][] board) {
		for (int r = 0; r < board.length; r ++)
			for (int c = 0; c < board [0].length; c ++)
				if (board[r][c] != '.' && !valid(board, r, c)) return false;
		return true;
	}

	private boolean valid(char[][] board, int row, int col) {
		char ch = board [row][col];
		// validate row
		for (int c = 0; c < board [0].length; c ++) if (c != col && board [row][c] == ch) return false;

		// validate col
		for (int r = 0; r < board.length; r ++) if (r != row && board [r][col] == ch) return false;

		// validate box
		int rs = 0, re = 0, cs = 0, ce = 0;
		if (row / 3 == 0) { rs = 0; re = 2; }
		else if (row / 3 == 1) { rs = 3; re = 5; }
		else if (row / 3 == 2) { rs = 6; re = 8; }

		if (col / 3 == 0) { cs = 0; ce = 2; }
		else if (col / 3 == 1) { cs = 3; ce = 5; }
		else if (col / 3 == 2) { cs = 6; ce = 8; }

		for (int r = rs; r <= re; r ++)
			for (int c = cs; c <= ce; c ++)
				if (r != row && c != col && board [r][c] == ch) return false;

		return true;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new char[][] 
						  {".87654321".toCharArray(),
		                   "2........".toCharArray(),
		                   "3........".toCharArray(),
		                   "4........".toCharArray(),
		                   "5........".toCharArray(),
		                   "6........".toCharArray(),
		                   "7........".toCharArray(),
		                   "8........".toCharArray(),
		                   "9........".toCharArray()}
						, true);
		
		_instance.runTest(new char[][] 
				  		  {"7...4....".toCharArray(),
                 		   "...865...".toCharArray(),
                 		   ".1.2.....".toCharArray(),
                 		   ".....9...".toCharArray(),
                 		   "....5.5..".toCharArray(),
                 		   ".........".toCharArray(),
                 		   "......2..".toCharArray(),
                 		   ".........".toCharArray(),
                 		   ".........".toCharArray()}
						, false);

		_instance.runTest(new char[][] 
		  		  		  {"....5..1.".toCharArray(),
       		   			   ".4.3.....".toCharArray(),
       		   			   ".....3..1".toCharArray(),
       		   			   "8......2.".toCharArray(),
       		   			   "..2.7....".toCharArray(),
       		   			   ".15......".toCharArray(),
       		   			   "......2..".toCharArray(),
       		   			   ".2.9.....".toCharArray(),
       		   			   "..4......".toCharArray()}
						, false);
	}
	
	public void runTest(final char[][] board, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { board });
		
		printAndIncrementCount();
		try {
			for (Object answer : answers)
				assertThat((Boolean) answer).isEqualTo(expectedOutput);
			
			System.out.println("ok!");
		} catch (Exception ex) {}
	}
	
}
