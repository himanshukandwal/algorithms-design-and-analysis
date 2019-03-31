package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 43. Multiply Strings
 * 
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2. 
 * 
 * Note:
 * 		The length of both num1 and num2 is < 110.
 * 		Both num1 and num2 contains only digits 0-9.
 * 		Both num1 and num2 does not contain any leading zero.
 * 
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * 
 * @author Hxkandwal
 */
public class MultiplyStrings extends AbstractCustomTestRunner {
	
	private static MultiplyStrings _instance = new MultiplyStrings();
	
    public static String _multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int [] ans = new int [m + n];

        for (int i = m - 1; i >= 0; i --) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j --) {
                int y = num2.charAt(j) - '0';
                int p1 = i + j, p2 = i + j + 1;
                int sum = x * y + ans [p2];

                ans [p2] = sum % 10;
                ans [p1] += sum / 10;
            }
        }

        StringBuilder res = new StringBuilder();
        for (int a : ans) if (res.length() > 0 || a > 0) res.append(a);
        return res.length() == 0 ? "0" : res.toString();
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("1", "1", "1");
		_instance.runTest("9", "99", "891");
		_instance.runTest("123", "456", "56088");
		_instance.runTest("999", "999", "998001");
		_instance.runTest("123456789", "987654321", "121932631112635269");
	}

	public void runTest(final String num1, String num2, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { num1, num2 });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}

}
