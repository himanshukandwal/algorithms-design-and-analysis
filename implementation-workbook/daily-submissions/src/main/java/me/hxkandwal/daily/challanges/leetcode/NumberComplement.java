package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 476. Number Complement
 * 
 * Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.
 * 
 * Note: The given integer is guaranteed to fit within the range of a 32-bit signed integer.
 *		 You could assume no leading zero bit in the integer’s binary representation.
 *
 * Example 1:
 * 	
 * Input: 5
 * Output: 2
 * Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. 
 * 				So you need to output 2.
 *
 * Example 2:
 * 	
 * Input: 1
 * Output: 0
 * Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0. 	
 * 	 
 * @author Hxkandwal
 *
 */
public class NumberComplement extends AbstractCustomTestRunner {
	
	private static NumberComplement _instance = new NumberComplement();
	
	public NumberComplement() {}
	
	public static int _findComplement(int num) {
        if (num == 0)
        	return num;
        
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
        	sb.insert(0, num % 2);
        	num /= 2;
        }
        
        for (int idx = 0; idx < sb.length(); idx ++)
        	sb.setCharAt(idx, (sb.charAt(idx) == '0' ? '1' : '0'));
        
		return Integer.valueOf(sb.toString(), 2);
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(5, 2);
		_instance.runTest(1, 0);
		_instance.runTest(7, 0);
	}

	public void runTest(final int x, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { x });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
