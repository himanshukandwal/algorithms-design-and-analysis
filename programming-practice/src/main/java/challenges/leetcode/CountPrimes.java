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
	
	public CountPrimes() {}
	
	// (DP) sieve of Eratosthenes
	public int _countPrimesOptimized(int n) {
		if (n <= 1) return 0;
        boolean [] primes = new boolean [n];
        Arrays.fill (primes, true);
        primes [0] = primes [1] = false;
        for (int p = 2; p < n; p ++) 
            if (primes [p])
                for (int factor = 2 * p; factor < n; factor+= p) primes [factor] = false;
        
        int count = 0;
        for (boolean prime : primes) if (prime) count ++;
        return count;
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
