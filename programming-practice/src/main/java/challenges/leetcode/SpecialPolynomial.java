package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Special Polynomial
 * 
 * Given integers x and n, find the largest integer k such that x0+x1+x2+...+xk ≤ n.
 * 
 * Example:
 * 		For x = 2 and n = 5, the output should be specialPolynomial(x, n) = 1.
 * 		We have 2^0 + 2^1 < 5 and 2^0 + 2^1 + 2^2 > 5.
 * 
 * @author Hxkandwal
 */
public class SpecialPolynomial extends AbstractCustomTestRunner {
	
	private static SpecialPolynomial _instance = new SpecialPolynomial();

	public int _specialPolynomial(int x, int n) {
	    int total = 1, pow = 0;
	    while (total <= n) total += (int) Math.pow (x, ++ pow);
	    return -- pow;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(2, 5, 1);
	}

	public void runTest(final int x, final int n, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { x, n });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
