package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * You are given a matrix that contains booleans. If an element is true, it means that it is colored black, 
 * 	otherwise it is colored white.
 * 
 * Your task is to find perimeter of the objects colored black. Example, 
 * For
 * 
 * matrix = [[false, false, false,  true, false],
 *           [false,  true,  true,  true, false],
 *           [false, false, false,  true, false],
 *           [false,  true,  true,  true, false],
 *           [ true,  true, false, false, false]]
 * 
 * The output should be MatrixPerimeter(matrix) = 22.
 * 
 * @author Hxkandwal
 *
 */
public class MatrixPerimeter extends AbstractCustomTestRunner {

	private static MatrixPerimeter _instance = new MatrixPerimeter();
	
	private MatrixPerimeter() {}
	
	// moving square (sliding window) method. check only where there is a boolean true.
	public int _getMatrixPerimeter(boolean[][] matrix) {
	    int perimeter = 0;
	    for (int row = 0; row < matrix.length; row ++) {
	        for (int col = 0; col < matrix[0].length; col ++) {
	            if (matrix [row][col]) {
	                if (row - 1 < 0 || !matrix[row - 1][col])
	                    perimeter ++;
	                if (row + 1 >= matrix.length || !matrix[row + 1][col])
	                    perimeter ++;
	                if (col - 1 < 0 || !matrix[row][col - 1])
	                    perimeter ++;
	                if (col + 1 >= matrix[0].length || !matrix[row][col + 1])
	                    perimeter ++;
	            }  
	        }
	    }
	    
	    return perimeter;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new boolean[][] {{false,true}, 
										   {false,false}}, 4);
		
		_instance.runTest(new boolean[][] {{true, true}, 
		                                   {true, false}}, 8);
		
		_instance.runTest(new boolean[][] {{true, true}, 
            							   {true, true}},  8);
		
		_instance.runTest(new boolean[][] {{true,true,false,true,true}},  12);
	}

	public void runTest(final boolean[][] matrix, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { matrix });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
