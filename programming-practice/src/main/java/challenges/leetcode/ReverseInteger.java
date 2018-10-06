package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 7. Reverse Integer
 * 
 * Reverse digits of an integer.
 * 
 * Example1: x = 123, return 321
 * 
 * Example2: x = -123, return -321
 * 
 * @author Hxkandwal
 *
 */
public class ReverseInteger extends AbstractCustomTestRunner {
	
	private static ReverseInteger _instance = new ReverseInteger();

	public int reverse(int x) {
		long a = 0;
		while (x != 0) {
			a = 10l * a + x % 10;
			if (a >= Integer.MAX_VALUE || a <= Integer.MIN_VALUE) return 0;
			x /= 10;
		}
		return (int) a;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(123, 321);
		_instance.runTest(-123, -321);
		_instance.runTest(-1, -1);
		_instance.runTest(4, 4);
		_instance.runTest(10, 1);
		_instance.runTest(1534236469, 0);
		_instance.runTest(1000000009, 0);
	}
	
	public void runTest(final int x, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { x });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
