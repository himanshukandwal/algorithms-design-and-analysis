package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * link: https://www.youtube.com/watch?v=5dJSZLmDsxk
 * 
 * @author Hxkandwal
 */
public class CountNegativeIntegersInMatrix extends AbstractCustomTestRunner {
	
	private static CountNegativeIntegersInMatrix _instance = new CountNegativeIntegersInMatrix();
	
	private CountNegativeIntegersInMatrix() {}
	
	public static int _countNegativeIntegers (int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return 0;
		
		int count = 0, width = matrix[0].length;
		int x = 0, y = width - 1;
		
		while (y >= 0 && x < matrix.length) {
			if (matrix [x][y] < 0) {
				if (y + 1 >= width || matrix [x][y + 1] > 0) {
					count += (y + 1);
					x ++; 
				} else 
					y ++;
			} else {
				if (y - 1 >= 0)
					y --;
				else
					x ++;
			}	
		}
			
		return count;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[][] { new int[] { -4, -3, -2, -1 }, 
										new int[] { -4, -3, -2, -1}}, 8);
		
		_instance.runTest(new int[][] { new int[] { -4, -3,  1,  2 }, 
										new int[] { -4, -3, -2, -1}}, 6);
		
		_instance.runTest(new int[][] { new int[] {  1,  2,  3,  4 }, 
										new int[] { -4, -3, -2, -1}}, 4);		
	}

	public void runTest(final int[][] array, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
