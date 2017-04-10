package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Amazon Interview question for matrix transposition.
 * 
 * flag = 0, transpose left flag = 1, transpose right
 * 
 * @author Hxkandwal
 *
 */
public class MatrixTransposition extends AbstractCustomTestRunner {

	private static MatrixTransposition _instance = new MatrixTransposition();

	private MatrixTransposition() {}
	
	// additional space.
	public static int[][] _transpose(int[][] matrix, int flag) {
		int[][] res = null;
		
		if (flag == 0) {
			res = new int[matrix[0].length][matrix.length];
			for (int idx = 0; idx < matrix.length; idx ++)
				for (int innerIdx = matrix[0].length - 1; innerIdx >= 0 ; innerIdx --)
					res [matrix[0].length - 1 - innerIdx][idx] = matrix [idx][innerIdx];

		} else {
			res = new int[matrix[0].length][matrix.length];
			for (int idx = matrix.length - 1; idx >= 0; idx --)
				for (int innerIdx = 0; innerIdx < matrix[0].length; innerIdx ++)
					res [innerIdx][matrix.length - 1- idx] = matrix [idx][innerIdx];
		}
    	
    	return res;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[][] { new int[] { 1, 2, 3, 4 }, new int[] { 4, 5, 6, 8 }}, 0, 
						  new int[][] { new int[] { 4, 8 }, new int[] { 3, 6 },
										new int[] { 2, 5 }, new int[] { 1, 4 }});
		
		_instance.runTest(new int[][] { new int[] { 1, 2, 3, 4 }, new int[] { 4, 5, 6, 8 }}, 1, 
						  new int[][] { new int[] { 4, 1 }, new int[] { 5, 2 },
										new int[] { 6, 3 }, new int[] { 8, 4 }});
	}

	public void runTest(final int[][] array, final int flag, final int[][] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array, flag });

		for (Object answer : answers) {
			int[][] actualAnswer = (int[][]) answer;
			
			for (int idx = 0; idx < actualAnswer.length; idx ++)
				assertThat(actualAnswer[idx]).isEqualTo(expectedOutput[idx]);
		}

		System.out.println("ok!");
	}	

}
