package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 625. Minimum Factorization
 * 
 * Given a positive integer a, find the smallest positive integer b whose multiplication of each 
 * digit equals to a.
 * 
 * If there is no answer or the answer is not fit in 32-bit signed integer, then return 0.
 * 
 * Example 1
 * 		Input:	48
 * 		Output:	68
 * 
 * Example 2
 * 		Input:	15
 * 		Output:	35
 * 
 * @author Hxkandwal
 */
public class MinimumFactorization extends AbstractCustomTestRunner {
	
	private static MinimumFactorization _instance = new MinimumFactorization();
	
	public int _smallestFactorization(int a) {
		if (a <= 1) return a;
		
		int n = a + (a == Integer.MAX_VALUE ? 0 : 1);
        boolean primes [] = new boolean [n];
        Arrays.fill (primes, true);
        primes [0] = primes [1] = false;
        
        for (int idx = 2; idx < n; idx ++) 
        	if (primes [idx] && (primes [idx] = (a % idx == 0)))
        		for (int jdx = 2; jdx * idx < n; jdx ++) primes [jdx * idx] = true;
        
		int ans = Integer.MAX_VALUE;
		for (int idx = 0; idx < n; idx ++) {
			if (primes [idx]) {
				int div = idx;
				while (a % div == 0) {
					if (div <= 9 && (a / div) <= 9)
						ans = Math.min (ans, (a / div) == 1 ? div : Math.min (div * 10 + (a / div), (a / div) * 10 + div));
					div *= idx;
				}
			}
		}	
		return (ans == Integer.MAX_VALUE) ? 0 : ans;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(1, 1);
		_instance.runTest(3, 3);
		_instance.runTest(4, 4);
		_instance.runTest(6, 6);
		_instance.runTest(48, 68);
		_instance.runTest(15, 35);
		_instance.runTest(128, 288);
	}

	public void runTest(final int a, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a });

		for (Object answer : answers)
			assertThat((int) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
