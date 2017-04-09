package me.hxkandwal.daily.challanges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * For integers a ≥ 1 and b ≥ 0, the hyperoperator [n] is defined recursively as follows:
 * 
 * a [0] b = b + 1
 * a [1] b = a + b
 * a [2] b = a * b
 * a [n] b = 1, if n ≥ 3 and b = 0
 * a [n] b = a [n-1] (a [n] (b-1)) otherwise.
 * 
 * Given integers a, b, n, and m, find (a [n] b) modulo m.
 * 
 * Example :
 * 	For a = 2, b = 3, n = 3, m = 7, the output should be hyperMod(a,b,n,m) = 1.
 * 
 * It is 2 [3] 3 = 23 = 8, and 8 mod 7 = 1.
 * 
 * @author Hxkandwal
 */
public class HyperMod extends AbstractCustomTestRunner {
	
	private static HyperMod _instance = new HyperMod();
	
	public double hyperMod(int a, int b, int n, int m) {
		return n < 3 ? (n < 2 ? b + 1 + ~-a * n : a * b) % m
				: n < 4 ? P (a, b, m)
						: n < 5 ? H (a, b, m)
								:  b < 4 ? hyperMod(a, b < 3 ? a : (int) hyperMod (a, b - 1, n, m), n - 1, m) : H (a, 9, m);
	}
	
	private long P (long a, long b, int m){    
		return b < 1 ? 1 : (b % 2 * ~-a + 1) * (a = P (a, b / 2, m)) % m * a % m;
	}

	private long H (int a, int b, int N) {
	    int m = N, n = N, i = 1;
		for (; i++ < n / i;)
			for (m -= n % i < 1 ? m / i : 0; n % i < 1;)
	            n /= i;

		m -= n > 1 ? m / n : 0;
		return b < 2 ? a : P (a, m > 1 ? H (a, b - 1, m) : 0, N);
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(2, 3, 3, 7, 1);
		_instance.runTest(2, 985974312, 4, 1000000007, 661944226);
	}

	public void runTest(final int a, final int b, final int n, final int m, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a, b, n, m });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
	
}