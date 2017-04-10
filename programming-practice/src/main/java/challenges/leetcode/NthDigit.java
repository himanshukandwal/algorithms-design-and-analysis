package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 400. Nth Digit
 * 
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 * 
 * Note: n is positive and will fit within the range of a 32-bit signed integer (n < 2^31).
 * 
 * Example 1:
 * 		Input: 3
 * 		Output: 3
 * 
 * Example 2:
 * 		Input: 11
 * 		Output: 0
 * 
 * 		Explanation: The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 * 
 * @author Hxkandwal
 */
public class NthDigit extends AbstractCustomTestRunner {
	
	private static NthDigit _instance = new NthDigit();
	
	private NthDigit() {}
	
    public static int _findNthDigit(int n) {
    	if (n <= 0) return 0;
    	if (n <= 9) return n;
    	else {
    		n -= 9;
    		if (n % 2 == 0) return (n % 21) / 2 - 1;
    		else { return ((n % 180) / 20) + 1; }
    	}
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(3, 3);
		_instance.runTest(10, 1);
		_instance.runTest(11, 0);
		_instance.runTest(12, 1);
		_instance.runTest(19, 4);
		_instance.runTest(21, 5);
		_instance.runTest(30, 2);
		_instance.runTest(32, 2);
		_instance.runTest(1000, 3);
	}

	public void runTest(final int n, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}

}
