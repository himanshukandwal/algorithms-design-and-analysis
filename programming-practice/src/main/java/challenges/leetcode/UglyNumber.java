package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 263. Ugly Number
 * 
 * Write a program to check whether a given number is an ugly number.
 * 
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly 
 * since it includes another prime factor 7.
 * 
 * Note that 1 is typically treated as an ugly number.
 * 
 * @author Hxkandwal
 *
 */
public class UglyNumber extends AbstractCustomTestRunner {
	
	private static UglyNumber _instance = new UglyNumber();
	
	private UglyNumber() {}
	
	public static boolean _isUgly(int num) {
		for (int idx = 2; idx < 6 && num > 0; idx ++) 
			while (num % idx == 0) num /= idx;
		return (num == 1);
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(6, true);
		_instance.runTest(8, true);
		_instance.runTest(14, false);
		_instance.runTest(-2147483648, false);
	}

	public void runTest(final int num, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { num });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
