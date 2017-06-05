package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 120. Triangle
 * 
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * For example, given the following triangle
 * 
 * 		[
 * 				[2],
 * 			   [3,4],
 * 			  [6,5,7],
 * 			 [4,1,8,3]
 * 		]
 * 
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * 
 * Note: Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 * 
 * @author Hxkandwal
 */
public class Triangle extends AbstractCustomTestRunner {
	
	private static Triangle _instance = new Triangle();

	public int _minimumTotal(List<List<Integer>> triangle) {
		int [] sum = new int [triangle.size() + 1];
        for (int idx = triangle.get (triangle.size() - 1).size() - 1; idx >= 0; idx --)
            for (int col = 0; col < triangle.get (idx).size(); col ++)
                sum [col] = triangle.get (idx).get (col) + Math.min (sum [col], sum [col + 1]);
        return sum [0];
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(Arrays.asList(Arrays.asList(-10)), -10);
		_instance.runTest(Arrays.asList(Arrays.asList(-1), Arrays.asList(-2, -3)), -4);
	}

	public void runTest(final List<List<Integer>> triangle, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { triangle });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
}
