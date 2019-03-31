package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 415. Add Strings
 * 
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 * 
 * Note:
 * 		The length of both num1 and num2 is < 5100.
 * 		Both num1 and num2 contains only digits 0-9.
 * 		Both num1 and num2 does not contain any leading zero.
 * 		You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * 
 * @author Hxkandwal
 */
public class AddStrings extends AbstractCustomTestRunner {
	
	private static AddStrings _instance = new AddStrings();
	
    public static String _addStrings(String num1, String num2) {
		StringBuilder ans = new StringBuilder();
		int idx1 = num1.length() - 1, idx2 = num2.length() - 1, carry = 0;
		while (idx1 >= 0 || idx2 >= 0) {
			int sum = carry;
			if (idx1 >= 0) sum += num1.charAt(idx1 --) - '0';
			if (idx2 >= 0) sum += num2.charAt(idx2 --) - '0';
			ans.append(sum % 10);
			carry = sum / 10;
		}
		if (carry > 0) ans.append(carry);
		return ans.reverse().toString();
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("10", "10", "20");
		_instance.runTest("10", "1", "11");
		_instance.runTest("9", "99", "108");
		_instance.runTest("123456789", "987654321", "1111111110");
	}

	public void runTest(final String num1, final String num2, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { num1, num2 });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
}
