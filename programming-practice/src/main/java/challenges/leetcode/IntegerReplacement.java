package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 397. Integer Replacement
 * 
 * Given a positive integer n and you can do operations as follow:
 * 	If n is even, replace n with n/2.
 * 	If n is odd, you can replace n with either n + 1 or n - 1.
 * 
 * What is the minimum number of replacements needed for n to become 1?
 * 
 * Example 1:	Input: 8	Output: 3
 * 				Explanation: 8 -> 4 -> 2 -> 1
 * 
 * Example 2:	Input: 7	Output: 4
 * 				Explanation: 7 -> 8 -> 4 -> 2 -> 1
 * 									or
 * 							 7 -> 6 -> 3 -> 2 -> 1
 * 
 * @author Hxkandwal
 */
public class IntegerReplacement extends AbstractCustomTestRunner {
	
	private static IntegerReplacement _instance = new IntegerReplacement();

	public int _integerReplacement(int n) {
		if (n == 1) return 0;
        if (n == Integer.MAX_VALUE) return 32;
        if (n % 2 == 0) return 1 + _integerReplacement (n/2);
        return 1 + Math.min (_integerReplacement (n + 1), _integerReplacement (n - 1));
    }
	
	// faster
	public int integerReplacement(int n) {
        if (n == 1) return 0;
        if (n == Integer.MAX_VALUE) return 32;
        if (n == 3) return 2;
        if (n % 2 == 0) return 1 + integerReplacement (n/2);
        return 1 + (Integer.bitCount (n + 1) > Integer.bitCount (n - 1) ? integerReplacement (n - 1) : integerReplacement (n + 1));
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(3, 2);
		_instance.runTest(7, 4);
		_instance.runTest(8, 3);
		_instance.runTest(2147483647, 32);
	}
	
	public void runTest(final int n, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
