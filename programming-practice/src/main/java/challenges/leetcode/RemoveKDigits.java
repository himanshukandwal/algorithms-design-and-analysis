package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 402. Remove K Digits
 * 
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 * 
 * Note:
 * 	The length of num is less than 10002 and will be ≥ k.
 * 	The given num does not contain any leading zero.
 * 
 * Example 1:
 * 		Input: num = "1432219", k = 3
 * 		Output: "1219"
 * 
 * 		Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * 
 * Example 2:
 * 		Input: num = "10200", k = 1
 * 		Output: "200"
 * 
 * 		Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * 
 * Example 3:
 * 		Input: num = "10", k = 2
 * 		Output: "0"
 * 
 * 		Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 * 
 * @author Hxkandwal
 */
public class RemoveKDigits extends AbstractCustomTestRunner {
	
	private static RemoveKDigits _instance = new RemoveKDigits();
	
	private RemoveKDigits() {}

	public static String _removeKdigits(String num, int k) {
		if (num.length() <= k) return "0";
		int deletions = 0;
		
		Stack<Character> stack = new Stack<>();
		for (int idx = 0; idx < num.length(); idx ++) {
			while (!stack.isEmpty() && stack.peek() > num.charAt(idx) && deletions < k) { stack.pop(); deletions ++; }
			if (!(stack.isEmpty() && num.charAt(idx) == '0')) stack.push (num.charAt(idx));
		}
		
		// no peaks left
        while (deletions < k && !stack.isEmpty() ) { stack.pop(); deletions ++; }
        
        StringBuilder ans = new StringBuilder();
        for (int idx = 0; idx < stack.size(); idx ++) ans.append(stack.get(idx));
		return ans.length() == 0 ? "0" : ans.toString();
    }
	
	// driver method
	public static void main(String[] args) throws FileNotFoundException {
		_instance.runTest("1432219", 3, "1219");
		_instance.runTest("10200", 1, "200");
		_instance.runTest("10", 2, "0");
		_instance.runTest("9", 1, "0");
		_instance.runTest("112", 1, "11");
		
		testComplex("/src/test/resources/me/hxkandwal/daily/challanges/leetcode/Remove-K-Digits-1.txt");
    }

    private static void testComplex(final String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(System.getProperty("user.dir") + filename));
        _instance.runTest(sc.next(), sc.nextInt(), sc.next());
        sc.close();
    }

	public void runTest(final String num, int k, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { num, k });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
		
}
