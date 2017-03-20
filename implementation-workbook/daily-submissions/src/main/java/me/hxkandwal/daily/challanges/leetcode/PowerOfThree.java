package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 326. Power of Three
 * 
 * Given an integer, write a function to determine if it is a power of three.
 * 
 * @author Hxkandwal
 */
public class PowerOfThree extends AbstractCustomTestRunner {
	
	private static PowerOfThree _instance = new PowerOfThree();

	public boolean _isPowerOfThree(int n) {
		while (n > 0 && n % 3 == 0) n /= 3;
		return n > 0 && n == 1;
    }
	
	public boolean _isPowerOfThreeLog(int n) {
        double a = Math.log(n) / Math.log(3);
        return Math.abs(a - Math.rint(a)) <= 0.00000000000001;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(3, true);
		_instance.runTest(9, true);
		_instance.runTest(10, false);
		_instance.runTest(243, true);
	}

	public void runTest(final int n, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
