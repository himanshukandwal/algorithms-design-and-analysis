package challenges.leetcode;

import java.util.LinkedList;
import java.util.Queue;

import challenges.AbstractCustomTestRunner;

/**
 * 542. 01 Matrix
 * 
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 * 
 * Example 1:
 * 		Input:
 * 				0 0 0
 * 				0 1 0
 * 				0 0 0
 * 
 * 		Output:	
 * 				0 0 0
 * 				0 1 0
 * 				0 0 0
 * 
 * Example 2:
 * 		Input:
 * 				0 0 0
 * 				0 1 0
 * 				1 1 1
 * 		
 * 		Output:
 * 				0 0 0
 * 				0 1 0
 * 				1 2 1
 * 
 * Note:
 * 	-	The number of elements of the given matrix will not exceed 10,000.
 * 	-	There are at least one 0 in the given matrix.
 * 	-	The cells are adjacent in only four directions: up, down, left and right.
 * 
 * @author Hxkandwal
 */
public class The01Matrix extends AbstractCustomTestRunner {

	private int [] rdir = { 1, 0, -1, 0 };
    private int [] cdir = { 0, 1, 0, -1 };
    
    public int[][] updateMatrix(int[][] matrix) {
        int [][] ans = new int [matrix.length][matrix [0].length];
        Queue<int[]> queue = new LinkedList<>();
        for (int r = 0; r < matrix.length; r ++)
            for (int c = 0; c < matrix [0].length; c ++) 
                if (matrix [r][c] == 0) queue.offer (new int [] { r, c });
                else ans [r][c] = Integer.MAX_VALUE;
        
        while (!queue.isEmpty()) {
            int row = queue.peek() [0], col = queue.poll() [1];
            for (int idx = 0; idx < 4; idx ++) {
                int r = row + rdir [idx], c = col + cdir [idx];
                if (r < 0 || r >= matrix.length || c < 0 || c >= matrix [0].length || matrix [r][c] == 0 
                    || ans [r][c] <= ans [row][col] + 1) continue;
                ans [r][c] = ans [row][col] + 1;
                queue.offer (new int [] { r, c });
            }
        }
        return ans;
    }
    
}
