package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 258. Add Digits
 * 
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 * 
 * For example:
 * 
 * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 * 
 * @author Hxkandwal
 *
 */
public class AddDigits extends AbstractCustomTestRunner {

	private static AddDigits _instance = new AddDigits();
	
	private AddDigits() {}
	
	// O(1) solution 
	public int _addDigits(int num) {
        return (num == 0 ? 0 : (num % 9 == 0 ? 9 : (num % 9)));
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(38, 2);
		_instance.runTest(0, 0);
		_instance.runTest(1, 1);
		_instance.runTest(8, 8);
		_instance.runTest(9, 9);
		_instance.runTest(18, 9);
		
		System.out.println("ok!");
	}

	public void runTest(final int num, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { num });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
	}
}
