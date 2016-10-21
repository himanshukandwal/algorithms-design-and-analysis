package me.hxkandwal.daily.challanges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * You are given a n x m matrix, which contains all the integers from 1 to n * m, 
 * inclusive, each exactly once.
 * 
 * Initially you are standing in the cell containing the number 1. On each turn you are allowed 
 * to move to an adjacent cell, i.e. to a cell that shares a common side with the one you are standing on now. 
 * It is prohibited to visit any cell more than once.
 * 
 * Check if it is possible to visit all the cells of the matrix in the order of increasing numbers in the 
 * cells, i.e. get to the cell with the number 2 on the first turn, then move to 3, etc.
 * 
 * Example:
 * 		For matrix =   [[1, 4, 5], 
 * 						[2, 3, 6]]
 * 
 * 			the output should be findPath(matrix) = true;
 * 
 * 
 * 		For matrix =   [[1, 3, 6], 
 * 						[2, 4, 5]]
 * 
 * 			the output should be findPath(matrix) = false;
 * 
 * @author Hxkandwal
 *
 */
public class FindPath extends AbstractCustomTestRunner {
	
	private static FindPath _instance = new FindPath();
	
	private FindPath() {}
	
	public static boolean _findPath(int[][] matrix) {
	    int row = -1, col = -1;
	    int length = matrix.length, width = matrix[0].length;
	    
	    boolean visit[][] = new boolean[length][width];
	    
	    boolean foundStartLocation = false;
	    for (row = 0; row < matrix.length; row ++) {
	    	for (col = 0; col < matrix[row].length; col ++) { 
	    		if (matrix[row][col] == 1) {
	    			visit [row][col] = foundStartLocation = true;
	    			break;
	    		}
	    	}
	    	
	    	if (foundStartLocation)
	    		break;
	    }
		
	    if (!foundStartLocation)
	    	return false;
	    
	    int currentVal = matrix[row][col];
	    while (true) {
	        currentVal ++;
	        if (col + 1 < width && matrix[row][col + 1] == currentVal) {
	        	col ++;
	        	visit[row][col] = true;
	        } else if (row + 1 < length && matrix[row + 1][col] == currentVal) {
	            row ++;
	            visit[row][col] = true;
	    	} else if (row - 1 >= 0 && matrix[row - 1][col] == currentVal) {
	            row --;
	            visit[row][col] = true;
	    	} else if (col - 1 >= 0 && matrix[row][col - 1] == currentVal) {
	        	col --;
	        	visit[row][col] = true;
	    	} else
	            break;
	    }
	    
	    for (row = 0; row < matrix.length; row ++) {
	    	for (col = 0; col < matrix[row].length; col ++) {
	    		if (!visit[row][col])
	    			return false;
	    	}
	    }
	    		
	    return true;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[][] { { 1, 4, 5 }, { 2, 3, 6 } }, true);
		_instance.runTest(new int[][] { { 1, 3, 6 }, { 2, 4, 5 } }, false);
		_instance.runTest(new int[][] { { 5, 4, 3, 2, 1 } }, true);
		_instance.runTest(new int[][] { { 1, 2, 3 }, 
										{ 6, 5, 4 }, 
										{ 7, 8, 9 }, 
										{ 12, 11, 10 } }, true);
		_instance.runTest(new int[][] { { 2, 3, 4, 5 }, 
										{ 1, 8, 7, 6 }, 
										{ 12, 9, 10, 11 } }, false);
	}
	
	public void runTest(final int[][] matrix, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { matrix });
		
		for (Object answer : answers) 
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
