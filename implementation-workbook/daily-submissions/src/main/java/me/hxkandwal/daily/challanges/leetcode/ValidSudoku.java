package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
	
	private ValidSudoku() {}
	
    public boolean _isValidSudoku(char[][] board) {
        if (board == null || board.length == 0)
        	return true;
        
        // must be a square
        if (board.length != board [0].length)
        	return false;
        
        for (int row = 0; row < board.length; row ++) {
        	Set<Character> seenCharacters = new HashSet<>();
        	
        	for (int col = 0; col < board [row].length; col ++) {
        		char boardChar = board [row][col];
        		
        		if (boardChar >= '0' && boardChar <= '9') {
        			if (seenCharacters.add(boardChar)) {
						for (int rowcol = 0; rowcol < board.length; rowcol ++) {
							if (rowcol != row && board [rowcol][col] == board [row][col])
								return false;
						}
						
						// check for that box condition
						if (row >= 0 && row <= 2) {
							if (col >= 0 && col <= 2)  {
								for (int boxrow = 0; boxrow < 3; boxrow ++)
									for (int boxcol = 0; boxcol < 3; boxcol ++)
										if (boxrow != row && boxcol != col && board [boxrow][boxcol] == boardChar)
											return false;
							}
							else if (col >= 3 && col <= 5)  {
								for (int boxrow = 0; boxrow < 3; boxrow ++)
									for (int boxcol = 3; boxcol < 6; boxcol ++)
										if (boxrow != row && boxcol != col && board [boxrow][boxcol] == boardChar)
											return false;
							}
							else {
								for (int boxrow = 0; boxrow < 3; boxrow ++)
									for (int boxcol = 6; boxcol < 9; boxcol ++)
										if (boxrow != row && boxcol != col && board [boxrow][boxcol] == boardChar)
											return false;
							}
						}
						else if (row >= 3 && row <= 5) {
							if (col >= 0 && col <= 2)  {
								for (int boxrow = 3; boxrow < 6; boxrow ++)
									for (int boxcol = 0; boxcol < 3; boxcol ++)
										if (boxrow != row && boxcol != col && board [boxrow][boxcol] == boardChar)
											return false;
							}
							else if (col >= 3 && col <= 5)  {
								for (int boxrow = 3; boxrow < 6; boxrow ++)
									for (int boxcol = 3; boxcol < 6; boxcol ++)
										if (boxrow != row && boxcol != col && board [boxrow][boxcol] == boardChar)
											return false;
							}
							else {
								for (int boxrow = 3; boxrow < 6; boxrow ++)
									for (int boxcol = 6; boxcol < 9; boxcol ++)
										if (boxrow != row && boxcol != col && board [boxrow][boxcol] == boardChar)
											return false;
							}
						}
						else {
							if (col >= 0 && col <= 2)  {
								for (int boxrow = 6; boxrow < 9; boxrow ++)
									for (int boxcol = 0; boxcol < 3; boxcol ++)
										if (boxrow != row && boxcol != col && board [boxrow][boxcol] == boardChar)
											return false;
							}
							else if (col >= 3 && col <= 5)  {
								for (int boxrow = 6; boxrow < 9; boxrow ++)
									for (int boxcol = 3; boxcol < 6; boxcol ++)
										if (boxrow != row && boxcol != col && board [boxrow][boxcol] == boardChar)
											return false;
							}
							else {
								for (int boxrow = 6; boxrow < 9; boxrow ++)
									for (int boxcol = 6; boxcol < 9; boxcol ++)
										if (boxrow != row && boxcol != col && board [boxrow][boxcol] == boardChar)
											return false;
							}
						}
        			} 
        			else 
        				return false;
				} 
        		else if (boardChar != '.')
        			return false;
    		}
		}
        
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
