package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 48. Rotate Image
 * 
 * You are given an n x n 2D matrix representing an image. Rotate the image by 90 degrees (clockwise).
 * 
 * Follow up: Could you do this in-place?
 * 
 * @author Hxkandwal
 */
public class RotateImage extends AbstractCustomTestRunner {
	
	private static RotateImage _instance = new RotateImage();
	
	private RotateImage() {}
	
	public int[][] _rotate(int[][] matrix) {
		int n = matrix.length, level = 0;
		
		while (level <= n/2 - 1) {
			int row = 0, col = 0;
			for (int idx = 0; idx < n - 1 - 2 * level; idx ++) {
				int a = matrix [row + level + idx][col + n - 1 - level];
				matrix [row + level + idx][col + n - 1 - level] = matrix [row + level][col + level + idx];
				
				int b = matrix [row + n - 1 - level][col + n - 1 - level - idx];
				matrix [row + n - 1 - level][col + n - 1 - level - idx] = a;
				
				a = matrix [row + n - 1 - level - idx][col + level];
				matrix [row + n - 1 - level - idx][col + level] = b;
				
				matrix [row + level][col + level + idx] = a;
			}
			
			level ++;
		}
		
		return matrix;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [][] {{ 1 }}, new int [][] {{ 1 }});
		_instance.runTest(new int [][] {{ 1, 2 }, { 3, 4 }}, new int [][] {{ 3, 1 }, { 4, 2 }});
		_instance.runTest(new int [][] {{ 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }}, new int [][] {{ 7, 4, 1 }, { 8, 5, 2 }, { 9, 6, 3 }});
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
