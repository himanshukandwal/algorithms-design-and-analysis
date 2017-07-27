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
	
	public int _myAtoi(String str) {
		str = str.trim ();
        if (str.length() == 0) return 0;
        long res = 0; 
        boolean neg = (str.charAt (0) == '-');
        for (int idx = (neg || str.charAt (0) == '+' ? 1 : 0); idx < str.length (); idx ++) {
            char ch = str.charAt (idx);
            if (ch >= '0' && ch <= '9') {
                res = 10 * res + (ch - '0');
                if (res > Integer.MAX_VALUE) { res = (neg ? Integer.MIN_VALUE : Integer.MAX_VALUE); break; }
            } else { if (ch == '+') return 0; else break; }
        }
        res = (neg ? -1 : 1) * res;
        return (int) res;
	}
	
	// another approach
	public int _myAtoiOther(String str) {
        str = str.trim ();
        int idx = 0, sign = 1;
        long ans = 0;
        while (idx < str.length () && (str.charAt (idx) < '0' || str.charAt (idx) > '9')) idx ++;
        if (idx > 1 || (idx == 1 && str.charAt (0) != '+' && str.charAt (0) != '-')) return 0;
        if (idx == 1 && str.charAt (0) == '-') sign = -1;
        while (idx < str.length ()) {
            if (str.charAt (idx) >= '0' && str.charAt (idx) <= '9') ans = 10l * ans + (str.charAt (idx) - '0');
            else break;
            if (ans > Integer.MAX_VALUE) break;
            idx ++;
        }
        ans = sign * ans;
        return ans < Integer.MIN_VALUE ? Integer.MIN_VALUE : (ans > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) ans);
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
