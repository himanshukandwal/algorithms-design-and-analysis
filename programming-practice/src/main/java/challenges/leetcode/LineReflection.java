package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashSet;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 356. Line Reflection
 * 
 * Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.
 * 
 * Example 1: Given points = [[1,1],[-1,1]], return true.
 * Example 2: Given points = [[1,1],[-1,-1]], return false.
 * 
 * Follow up: Could you do better than O(n^2)?
 * 
 * @author Hxkandwal
 */
public class LineReflection extends AbstractCustomTestRunner {
	
	private static LineReflection _instance = new LineReflection();

	public boolean _isReflected(int[][] points) {
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	    HashSet<String> set = new HashSet<>();
		for (int[] p : points) {
			max = Math.max (max, p [0]);
			min = Math.min (min, p [0]);
	        String str = p [0] + "a" + p [1];
	        set.add (str);
	    }
		int sum = max + min;
		for (int [] p : points) {
			String str = (sum - p [0]) + "a" + p [1];
			if (!set.contains (str)) return false;
	    }
	    return true;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [][] {{ 0, 0 }, { 1, 0 }}, true);
		_instance.runTest(new int [][] {{ 1, 1 }, { -1, 1 }, { -1, 1 }}, true);
	}

	public void runTest(final int[][] points, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { points });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
