package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
	
	public char[][] _solve(char[][] board) {
        if (board.length == 0) return board;
        for (int r = 0; r < board.length; r ++)
            for (int c = 0; c < board [0].length; c ++)
                if (board [r][c] == 'O') bfs (board, r, c);
        return board;
    }
    
    int [] rowdir = { 0, 1, 0, -1 };
    int [] coldir = { 1, 0, -1, 0 };
    
    private void bfs (char [][] board, int row, int col) {
        List<int []> cluster = new ArrayList <>();
        boolean safe = false;
        Queue<int []> queue = new LinkedList<>();
        queue.offer (new int [] { row, col });
        while (!queue.isEmpty ()) {
            int size = queue.size ();
            while (size -- > 0) {
                int r = queue.peek () [0], c = queue.peek () [1];
                cluster.add (queue.poll ());
                board [r][c] = '-';
                
                for (int idx = 0; idx < 4; idx ++) {
                    int rr = r + rowdir [idx], cc = c + coldir [idx];
                    if (rr < 0 || rr >= board.length || cc < 0 || cc >= board [0].length) { safe = true; continue; }
                    if (board [rr][cc] == 'O') queue.offer (new int [] { rr, cc });
                }
            }
        }
        for (int [] n : cluster) board [n [0]][n [1]] = !safe ? 'X' : 'O';
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
