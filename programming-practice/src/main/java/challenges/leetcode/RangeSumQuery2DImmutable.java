package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import challenges.AbstractCustomTestRunner;

/**
 * 304. Range Sum Query 2D - Immutable
 * 
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner 
 * (row1, col1) and lower right corner (row2, col2).
 * 
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
 * 
 * Example:
 * 	Given matrix = [
 * 		[3, 0, 1, 4, 2],
 * 		[5, 6, 3, 2, 1],
 * 		[1, 2, 0, 1, 5],
 * 		[4, 1, 0, 1, 7],
 * 		[1, 0, 3, 0, 5]
 * 	]
 * 
 * 		sumRegion(2, 1, 4, 3) -> 8
 * 		sumRegion(1, 1, 2, 2) -> 11
 * 		sumRegion(1, 2, 2, 4) -> 12
 * 		
 * Note:
 * 	You may assume that the matrix does not change.
 * 	There are many calls to sumRegion function.
 * 	You may assume that row1 ≤ row2 and col1 ≤ col2.
 * 
 * @author Hxkandwal
 */
public class RangeSumQuery2DImmutable extends AbstractCustomTestRunner {
	
	int[][] matrix;
    
    public RangeSumQuery2DImmutable (int[][] matrix) {
        this.matrix = matrix;
        for (int row = 0; row < matrix.length; row ++)
            for (int col = 0; col < matrix [0].length; col ++) {
            	if (row - 1 >= 0) matrix [row][col] += matrix [row - 1][col];
            	if (col - 1 >= 0) matrix [row][col] += matrix [row][col - 1];
            	if (row - 1 >= 0 && col - 1 >= 0) matrix [row][col] -= matrix [row - 1][col - 1];
            }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = matrix [row2][col2];
        if (row1 - 1 >= 0) sum -= matrix [row1 - 1][col2];
        if (col1 - 1 >= 0) sum -= matrix [row2][col1 - 1];
        if (row1 - 1 >= 0 && col1 - 1 >= 0) sum += matrix [row1 - 1][col1 - 1];
        return sum;
    }
	
	// driver method
	public static void main(String[] args) {
		RangeSumQuery2DImmutable instance = new RangeSumQuery2DImmutable(new int [][] { new int[] { 1, 2, 3 }, 
																						new int[] { 3, 4, 5 },
																						new int[] { 1, 1, 8 }});
		assertThat(instance.sumRegion(0, 0, 0, 0)).isEqualTo(1);
		assertThat(instance.sumRegion(0, 0, 1, 1)).isEqualTo(10);
		System.out.println("ok!");
	}
    
}
