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
	
	public void rotate(int[][] matrix) {
		int l = 0, n = matrix.length;
		while (n/2 - l > 0) {
			for (int i = 0; i < n - 1 - 2 * l; i ++) {
				int a = matrix [l + i][n - 1 - l];
				matrix [l + i][n - 1 - l] = matrix [l][l + i];

				int b = matrix [n - 1 - l][n - 1 - l - i];
				matrix [n - 1 - l][n - 1 - l - i] = a;

				int c = matrix [n - 1 - l - i][l];
				matrix [n - 1 - l - i][l] = b;

				matrix [l][l + i] = c;
			}
			l ++;
		}
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
