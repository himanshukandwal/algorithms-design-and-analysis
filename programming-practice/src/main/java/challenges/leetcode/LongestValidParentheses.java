package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 32. Longest Valid Parentheses
 * 
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) 
 * parentheses substring.
 * 
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * 
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 * 
 * @author Hxkandwal
 *
 */
public class LongestValidParentheses extends AbstractCustomTestRunner {
	
	private static LongestValidParentheses _instance = new LongestValidParentheses();

	public int _longestValidParentheses(String s) {
		Stack<Integer> stk = new Stack<>();
        Stack<int[]> ans = new Stack<>();
        int max = 0;
        for (int idx = 0; idx < s.length(); idx ++) {
            char ch = s.charAt (idx);
            if (ch == '(') stk.push (idx);
            else if (!stk.isEmpty()) {
                int pidx = stk.pop();
                int len = idx - pidx + 1;
                while (!ans.isEmpty() && ans.peek() [0] > pidx) ans.pop();
                if (!ans.isEmpty() && ans.peek() [0] == pidx - 1) len += ans.pop () [1];
                max = Math.max (max, len);
                ans.push (new int [] { idx , len });
            }
        }
        return max;
	}
	
	public int _longestValidParenthesesDP(String s) {
		int [] dp = new int [s.length()];
        int max = 0;
        for (int idx = 1; idx < s.length(); idx ++) {
            if (s.charAt (idx) == ')' && idx - 1 - dp [idx - 1] >= 0 && s.charAt (idx - 1 - dp [idx - 1]) == '(')
                max = Math.max (max, dp [idx] = (dp [idx - 1] + 2 + (idx - dp [idx - 1] - 2 >= 0 ? dp [idx - dp [idx - 1] - 2]: 0)));
        }
        return max;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("()", 2);
		_instance.runTest("(()", 2);
		_instance.runTest("(()))(()()", 4);
		_instance.runTest("(()((()()", 4);
		_instance.runTest("(()((()())))", 12);
		_instance.runTest("()(()((()())))", 14);
		_instance.runTest("(()((()()))", 10);
		_instance.runTest("(", 0);
		_instance.runTest("(((", 0);
		_instance.runTest(")))", 0);
		_instance.runTest(")()())", 4);
	}
	
	public void runTest(final String input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
}
