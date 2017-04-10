package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 371. Sum of Two Integers
 * 
 * Calculate the sum of two integers a and b, but you are not allowed to use the
 * operator + and -.
 * 
 * Example: Given a = 1 and b = 2, return 3.
 * 
 * @author Hxkandwal
 *
 */
public class SumOfTwoIntegers extends AbstractCustomTestRunner {

	private static SumOfTwoIntegers _instance = new SumOfTwoIntegers();

	// method 1 : approach to tackle (last) position by position (>> 1).  
	// 			  works only on positive numbers
	public int getSum(int a, int b) {
		int mask = 1;
		int sum, idx, carry;
		for (sum = idx = carry = 0; (a > 0 || b > 0); idx++, a >>= 1, b >>= 1) {
			sum = ((carry ^ (a & mask) ^ (b & mask)) << idx) | sum;

			if (carry == 1) {
				if (((a & mask) == 0) && ((b & mask) == 0))
					carry = 0;
			} else {
				if (((a & mask) == 1) && ((b & mask) == 1))
					carry = 1;
			}
		}

		if (carry > 0)
			sum = (carry << idx) | sum;

		return sum;
	}

	// method 2 : more powerful and of-course SIMPLE !!
	public int _add(int a, int b) {
		while (b != 0) {
			int carry = (a & b);

			a = a ^ b;

			b = carry << 1;
		}
		return a;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(5, 2, 7);
		_instance.runTest(5, 5, 10);
		_instance.runTest(13, 14, 27);
		_instance.runTest(13, 114, 127);
		_instance.runTest(-1, 1, 0);
		_instance.runTest(-5, 1, -4);
	}

	public void runTest(final int a, final int b, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a, b });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
