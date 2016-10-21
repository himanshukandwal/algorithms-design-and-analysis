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
 * 
 * 		For a = 2, b = 3, n = 3, m = 7, the output should be hyperMod(a,b,n,m) = 1.
 * 
 * It is 2 [3] 3 = 23 = 8, and 8 mod 7 = 1.
 * 
 * @author Hxkandwal
 *
 */
public class HyperMod extends AbstractCustomTestRunner {
	
	private static HyperMod _instance = new HyperMod();
	
	private HyperMod() {}
	
	public static int _hyperMod(int a, int b, int n, int m) {
		return (n == 0) ? b + 1 : ((n == 1) ? a + b : (n == 2 ? (a * b) : (b == 0 ? 1 : (_hyperMod (a, _hyperMod (a, b - 1, n, m), n-1, m)) % m )));
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(2, 3, 3, 7, 1);
//		_instance.runTest(2, 985974312, 4, 1000000007, 661944226);
	}

	public void runTest(final int a, final int b, final int n, final int m, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a, b, n, m });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
	
}
