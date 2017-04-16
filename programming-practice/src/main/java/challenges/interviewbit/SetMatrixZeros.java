package challenges.interviewbit;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Set Matrix Zeros
 * 
 * Given an m x n matrix of 0s and 1s, if an element is 0, set its entire row and column to 0.
 * Do it in place.
 * 
 * Example
 * 		Given array A as
 * 			1 0 1
 * 			1 1 1
 * 			1 1 1
 * 
 * 		On returning, the array A should be :
 * 			0 0 0
 * 			1 0 1
 * 			1 0 1
 * 
 * @author Hxkandwal
 */
public class SetMatrixZeros extends AbstractCustomTestRunner {
	
	private static SetMatrixZeros _instance = new SetMatrixZeros();

	public void setZeroes(List<List<Integer>> a) {
	    boolean col0 = false;
	    for (int row = 0; row < a.size(); row ++) {
	        col0 = (!col0) ? a.get (row).get (0) == 0 : col0;
	        for (int col = 1; col < a.get(0).size(); col ++) {
	            if (a.get (row).get (col) == 0) {
	                a.get (0).set (col, 0);
	                a.get (row).set (0, 0);
	            }
	        }
	    }
	    
	    for (int row = a.size() - 1; row >= 0; row --) {
	        for (int col = a.get(0).size() - 1; col > 0; col --)
	            if (a.get (0).get (col) == 0 || a.get (row).get (0) == 0)
	                a.get (row).set (col, 0);
	        if (col0) a.get (row).set (0, 0);
	    }
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.setZeroes(Arrays.asList(Arrays.asList(1, 0, 1), Arrays.asList(1, 1, 1), Arrays.asList(1, 1, 1)));
	}
		
}
