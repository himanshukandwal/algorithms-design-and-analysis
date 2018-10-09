package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 204. Count Primes
 * 
 * Count the number of prime numbers less than a non-negative number, n.
 * 
 * @author Hxkandwal
 */
public class CountPrimes extends AbstractCustomTestRunner {
	
	private static CountPrimes _instance = new CountPrimes();

	// (DP) sieve of Eratosthenes
	public int _countPrimesOptimized(int n) {
		boolean[] p = new boolean [n];
		int ans = 0;
		for (int idx = 2; idx < n; idx ++) {
			if (!p [idx]) {
				ans ++;
				for (int j = 1; idx * j < n; j ++) p [idx * j] = true;
			}
		}
		return ans;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(999983, 78497);
		_instance.runTest(1500000, 114155);
	}

	public void runTest(final int n, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
