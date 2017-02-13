package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
	
	private FactorialTrailingZeroes() {}
	
	public static int _trailingZeroes(int n) {
		int count = 0, pow = 1;
		
		while (n / Math.pow(5, pow) > 0)
			count += (n / Math.pow(5, pow ++));
		
		return count;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(1, 0);
		_instance.runTest(2, 0);
		_instance.runTest(5, 1);
		_instance.runTest(10, 2);
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
