package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Least common prime divisor
 * 
 * Calculate the LCPD (least common prime divisor) of two numbers.
 * 
 * Example:
 * 		For a = 12 and b = 13, the output should be leastCommonPrimeDivisor(a, b) = -1;
 * 
 * 		For a = 12 and b = 18, the output should be leastCommonPrimeDivisor(a, b) = 2.
 * 
 * @author Hxkandwal
 */
public class LeastCommonPrimeDivisor extends AbstractCustomTestRunner {
	
	private static LeastCommonPrimeDivisor _instance = new LeastCommonPrimeDivisor();

	public int _leastCommonPrimeDivisor(int a, int b) {
	    int gcd = gcd (a, b);
	    if (gcd == 1) return -1;
	    boolean [] primes = new boolean [gcd + 1];
	    Arrays.fill (primes, true);
	    for (int idx = 2; idx < primes.length; idx ++) {
	        if (primes [idx]) {
	            if (gcd % idx == 0) return idx;
	            else
	                for (int j = 2; idx * j < primes.length; j ++)
	                    primes [j] = false;
	        }
	    }
	    return -1;
	}

	public int gcd (int a, int b) {
	    if (b == 0) return a;
	    return gcd (b, a % b);
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(5, 10, 5);
		_instance.runTest(12, 13, -1);
		_instance.runTest(12, 18, 2);
	}

	public void runTest(final int a, final int b, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a, b });

		for (Object answer : answers)
				assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
		
}
