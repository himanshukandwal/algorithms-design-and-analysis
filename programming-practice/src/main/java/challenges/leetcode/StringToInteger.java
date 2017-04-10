package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

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
		if ((str = str.trim()).length() == 0) return 0;
        boolean isNeg = (str.charAt(0) == '-');
        if (str.charAt(0) == '+' || str.charAt(0) == '-') str = str.substring(1); 
        	
        long value = 0;
        for (int idx = 0; idx < str.length(); idx ++) {
            if (Character.isDigit (str.charAt(idx))) { 
                value = 10 * value + (str.charAt(idx) - '0'); 
                if (value > Integer.MAX_VALUE)
                    return (isNeg) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            else break;
        }
        return ((int) value) * (isNeg ? -1 : 1);
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
		_instance.runTest("9223372036854775809", 2147483647);
	}
	
	public void runTest(final String str, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { str });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
