package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 67. Add Binary
 * 
 * Given two binary strings, return their sum (also a binary string).
 * 
 * For example,
 * 		a = "11"
 * 		b = "1"
 * 	
 * Return "100".
 * 
 * @author Hxkandwal
 *
 */
public class AddBinary extends AbstractCustomTestRunner {
	
	private static AddBinary _instance = new AddBinary();
	
    public String _addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        while (i >= 0 || j >= 0) {
            int s = carry;
            if (i >= 0) s += a.charAt(i --) - '0';
            if (j >= 0) s += b.charAt(j --) - '0';

            // similar to how we do with base 10.
            carry = s / 2;
            s = s % 2;
            ans.append (s);
        }

        if (carry > 0) ans.append (carry);
        return ans.reverse().toString();
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("0", "0", "0");
		_instance.runTest("1", "1", "10");
		_instance.runTest("11", "1", "100");
		_instance.runTest("101111", "10", "110001");
		_instance.runTest("110010", "10111", "1001001");
	}

	public void runTest(final String a, final String b, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a, b });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
