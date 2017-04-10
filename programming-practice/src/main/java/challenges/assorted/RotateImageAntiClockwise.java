package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Rotate Image
 * 
 * You are given an n x n 2D matrix representing an image. Rotate the image by 90 degrees (anti-clockwise).
 * 
 * Follow up: Could you do this in-place?
 * 
 * @author Hxkandwal
 */
public class RotateImageAntiClockwise extends AbstractCustomTestRunner {
	
	private static RotateImageAntiClockwise _instance = new RotateImageAntiClockwise();
	
	private RotateImageAntiClockwise() {}
	
	public int[][] _rotate(int[][] matrix) {
		int n = matrix.length, level = 0;
		
		while (level <= n/2 - 1) {
			int row = 0, col = 0;
			for (int idx = 0; idx < n - 1 - 2 * level; idx ++) {
				int a = matrix [row + level + idx][col + level];
				matrix [row + level + idx][col + level] = matrix [row + level][col + n - 1 - level - idx];
				
				int b = matrix [row + n - 1 - level][col + level + idx];
				matrix [row + n - 1 - level][col + level + idx] = a;
				
				a = matrix [row + n - 1 - level - idx][col + n - 1 - level];
				matrix [row + n - 1 - level - idx][col + n - 1 - level] = b;
				
				matrix [row + level][col + n - 1 - level - idx] = a;
			}
			
			level ++;
		}
		
		return matrix;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [][] {{ 1 }}, new int [][] {{ 1 }});
		_instance.runTest(new int [][] {{ 1, 2 }, { 3, 4 }}, new int [][] {{ 2, 4 }, { 1, 3 }});
		_instance.runTest(new int [][] {{ 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }}, new int [][] {{ 3, 6, 9 }, { 2, 5, 8 }, { 1, 4, 7 }});
	}

	public void runTest(final int[][] matrix, final int[][] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { matrix });

		for (Object answer : answers) {
			int [][] ans = (int[][]) answer;
			for (int idx = 0; idx < ans.length; idx ++) assertThat(ans [idx]).isEqualTo(expectedOutput [idx]);
		}
		
		System.out.println("ok!");
	}	

}
