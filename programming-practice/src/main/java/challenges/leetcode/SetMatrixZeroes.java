package challenges.leetcode;

import java.util.HashSet;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * 73. Set Matrix Zeroes
 * 
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 * 
 * @author Hxkandwal
 */
public class SetMatrixZeroes extends AbstractCustomTestRunner {
	
	// O(1) space solution
    public void _setZeroesBetter(int[][] matrix) {
        int col0 = 1;
        for (int row = 0; row < matrix.length; row ++) {
            if (matrix [row][0] == 0) col0 = 0;
            for (int col = 1; col < matrix [0].length; col ++)
                if (matrix [row][col] == 0) matrix [row][0] = matrix [0][col] = 0;
        }
        
        for (int row = matrix.length - 1; row >= 0; row --) {
            for (int col = matrix [0].length - 1; col >= 1; col --) 
                if (matrix [row][0] == 0 || matrix [0][col] == 0) matrix [row][col] = 0;
            if (col0 == 0) matrix [row][0] = 0;
        }
    }
    
	public void _setZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet<>(), cols = new HashSet<>();
        for (int row = 0; row < matrix.length; row ++)
            for (int col = 0; col < matrix [0].length; col ++)
                if (matrix [row][col] == 0) { rows.add (row); cols.add (col); }
                
        for (int row : rows) for (int col = 0; col < matrix [0].length; col ++) matrix [row][col] = 0;
        for (int col : cols) for (int row = 0; row < matrix.length; row ++) matrix [row][col] = 0;
    }

}
