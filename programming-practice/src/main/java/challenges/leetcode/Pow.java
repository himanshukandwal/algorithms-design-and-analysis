package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 50. Pow(x, n)
 * 
 * Implement pow(x, n).
 * 
 * @author Hxkandwal
 */
public class Pow extends AbstractCustomTestRunner {
	
	private static Pow _instance = new Pow();

	public double _myPow (double x, int n) {
		double result = 1.0;
        for (int i = n; i != 0; i /= 2, x *= x)
            if (i % 2 != 0) result *= x;
        return n < 0 ? 1.0 / result : result;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(2, 5, 32);
	}

	public void runTest(final double x, int n, final double expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { x, n });

		for (Object answer : answers) {
			assertThat((Double) answer).isAtLeast(expectedOutput);
			assertThat((Double) answer).isAtMost(expectedOutput);
		}
		
		System.out.println("ok!");
	}	

}
