package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
		if (n == 0) return 0;
		boolean [] dp = new boolean [n]; 		// <<<<<<<<<<<<<<<<<<<<<<< for storing 1 to n - 1
		Arrays.fill(dp, true);

        int count = 0;        
    	for (int idx = 2; idx < n; idx ++) {
    		if (dp [idx]) {
	    		count ++;
	    		for (int mIdx = 1; mIdx * idx < n; mIdx ++) 
	    			dp [mIdx * idx] = false;
    		}
    	}
    	
        return count;
    }
	
	// failed naive approach
    public int _countPrimes(int n) {
    	if (n == 0) return 0;
        int count = 0;
        for (int idx = 2; idx < n; idx ++) {
            boolean isPrime = true;
            for (int j = 2; j * j <= idx && isPrime; j ++)
                if (idx % j == 0) isPrime = false;
            if (isPrime) count ++;
        }
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
