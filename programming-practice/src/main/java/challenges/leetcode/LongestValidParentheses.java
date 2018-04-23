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

	// other implementation of above
	public int longestValidParenthesesOtherStack(String s) {
		if (s.length() <= 1) return 0;
		Stack<int[]> stk = new Stack<>();
		int max = 0;
		for (int idx = 0; idx < s.length(); idx ++) {
			if (s.charAt (idx) == ')') {
				while (!stk.isEmpty() && stk.peek()[1] != 0) stk.pop();
				if (!stk.isEmpty()) {
					stk.peek()[1] += idx - stk.peek()[0] + 1;

					// try to stitch level back. ()() case
					if (stk.size() >=2 && stk.peek()[0] == (stk.get(stk.size() - 2)[0] + stk.get(stk.size() - 2)[1])) {
						stk.get(stk.size() - 2)[1] += stk.peek()[1];
						stk.pop();
					}
					max = Math.max(max, stk.peek()[1]);
				}
			} else stk.push(new int [] { idx, 0});
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

	// TLE (DP)
	public int longestValidParentheses(String s) {
		if (s.length() == 0) return 0;
		int [][] dp = new int [s.length() + 1][s.length() + 1];
		int max = 0;
		for (int r = 0; r < s.length(); r ++) {
			for (int c = r - 1; c >= 0; c --) {
				char rch = s.charAt(r), cch = s.charAt(c);
				if (rch == ')' && cch == '(') {
					// expand
					if (r - c == 1) dp [r + 1][c + 1] = 2;
						// converge
					else if (dp [r][c + 2] != 0) dp [r + 1][c + 1] = dp [r][c + 2] + 2;
					else {
						// stitch
						for (int idx = c + 1; idx < r; idx ++)
							if (dp [idx + 1][c + 1] != 0 && dp [r + 1][idx + 2] != 0)
								dp [r + 1][c + 1] = Math.max (dp [r + 1][c + 1], dp [idx + 1][c + 1] + dp [r + 1][idx + 2]);
					}
					max = Math.max (max, dp [r + 1][c + 1]);
				}
			}
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
