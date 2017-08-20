package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 172. Factorial Trailing Zeroes
 * 
 * Given an integer n, return the number of trailing zeroes in n!.
 * 
 * Note: Your solution should be in logarithmic time complexity.
 * 
 * @author Hxkandwal
 */
public class FactorialTrailingZeroes extends AbstractCustomTestRunner {
	
	private static FactorialTrailingZeroes _instance = new FactorialTrailingZeroes();
	
	public static int _trailingZeroes(int n) {
		int ans = 0, pow = 1;
		while (n / Math.pow (5, pow) > 0) ans += n / Math.pow (5, pow ++);
		return ans;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(1, 0);
		_instance.runTest(2, 0);
		_instance.runTest(15, 3);
		_instance.runTest(30, 7);
	}

	public void runTest(final int n, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
