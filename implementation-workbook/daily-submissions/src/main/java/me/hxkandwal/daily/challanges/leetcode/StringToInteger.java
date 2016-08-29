package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 8. String to Integer (atoi)
 * 
 * Implement atoi to convert a string to an integer.
 * 
 * Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and 
 * 		 ask yourself what are the possible input cases.
 * 
 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible 
 * 		  to gather all the input requirements up front.
 * 
 * @author Hxkandwal
 *
 */
public class StringToInteger extends AbstractCustomTestRunner {

	private static StringToInteger _instance = new StringToInteger();
	
	private StringToInteger() {}
	
	public int _myAtoi(String str) {
		str = str.trim();
		
		if (str.length() == 0)
			return 0;
		
		boolean isNeg = (str.charAt(0) == '-') ? true : false;
		char baseChar = '0';
		
		int ans = 0;
		for (int idx = (isNeg ? 1 : ((str.charAt(0) == '+' ? 1 : 0))); idx < str.length(); idx ++) {
			if (str.charAt(idx) >= baseChar && str.charAt(idx) <= (baseChar + 9)) {
				long upValue = (ans * 10l) + (str.charAt(idx) - baseChar);
				
				if (isNeg ? (-upValue < Integer.MIN_VALUE) : (upValue > Integer.MAX_VALUE))
					return (isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE); 
				
				ans = (int) upValue;
			} else
				break;
		}
		
		return (isNeg ? -ans : ans);
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("+", 0);
		_instance.runTest("     010", 10);
		_instance.runTest("  -0012a42", -12);
		_instance.runTest("+1", 1);
		_instance.runTest("123a", 123);
		_instance.runTest("0", 0);
		_instance.runTest("", 0);
		_instance.runTest("9646324351", 2147483647);
		_instance.runTest("-9646324351", -2147483648);
		_instance.runTest("2646324351", 2147483647);
		_instance.runTest("-2646324351", -2147483648);
		_instance.runTest("1000", 1000);
		_instance.runTest("-1000", -1000);
		_instance.runTest("123", 123);
		_instance.runTest("00001", 1);
		_instance.runTest("-2147483647", -2147483647);
		_instance.runTest("-2147483648", -2147483648);
		_instance.runTest("2147483648", 2147483647);
	}
	
	public void runTest(final String str, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { str });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
