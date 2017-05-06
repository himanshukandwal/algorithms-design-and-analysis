package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 231. Power of Two
 * 
 * Given an integer, write a function to determine if it is a power of two.
 * 
 * @author Hxkandwal
 */
public class PowerOfTwo extends AbstractCustomTestRunner {
	
	private static PowerOfTwo _instance = new PowerOfTwo();
	
	// bit manipulation trick
	public static boolean _isPowerOfTwoOptimal(int n) {
		 return n > 0 && (n & n-1) == 0;
    }
	
	public static boolean _isPowerOfTwo(int n) {
        double estimate = (Math.log(n) / Math.log(2));
		return n > 0 && (estimate - (int) estimate) < 0.000000001;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(0, false);
		_instance.runTest(1, true);
		_instance.runTest(4, true);
		_instance.runTest(16, true);
		_instance.runTest(17, false);
	}

	public void runTest(final int n, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
	
}
