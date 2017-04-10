package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Maximum Perimeter Triangle
 * 
 * Sample Input 0
 * 			5
 * 			1 1 1 3 3
 * 
 * Sample Output 0
 * 			1 3 3
 * 
 * Sample Input 1
 * 			3
 * 			1 2 3
 * 
 * Sample Output 1
 * 			-1
 * 
 * @author Hxkandwal
 */
public class MaximumPerimeterTriangle extends AbstractCustomTestRunner {
	
	private static MaximumPerimeterTriangle _instance = new MaximumPerimeterTriangle();
	
	public String _getMaxPerimeter(int [] sides) {
		Arrays.sort (sides);
		int idx = 0;
		for (idx = sides.length - 3; idx >= 0 && sides [idx + 2] >= sides [idx + 1] + sides [idx]; idx --);
		return (idx < 0) ? "-1" : new StringBuilder(sides [idx] + " ").append(sides [idx + 1] + " ").append(sides [idx + 2]).toString();
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, 1, 1, 3, 3 }, "1 3 3");
	}

	public void runTest(final int [] sides, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { sides });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
