package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 9. Palindrome Number
 * 
 * Determine whether an integer is a palindrome. Do this without extra space.
 * 
 * @author Hxkandwal
 *
 */
public class PalindromeNumber extends AbstractCustomTestRunner {

	private static PalindromeNumber _instance = new PalindromeNumber();
	
	public boolean _isPalindrome(int x) {
		int ans = 0, o = x;
		while (x > 0) {
            ans = ans * 10 + (x % 10);
            x /= 10;
        }
        return ans == o;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(1, true);
		for (int idx = 0; idx < 10; idx ++) 
			_instance.runTest(idx, true);
		
		for (int idx = -9; idx < 0; idx ++) 
			_instance.runTest(idx, false);
		
		_instance.runTest(11, true);
		_instance.runTest(111, true);
		_instance.runTest(121, true);
		_instance.runTest(1221, true);
		_instance.runTest(12321, true);
		_instance.runTest(123210, false);
	}

	public void runTest(final int x, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { x });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
