package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 227. Basic Calculator II
 * 
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 * 
 * You may assume that the given expression is always valid.
 * 
 * Some examples:
 * 		 "3+2*2" = 7
 *  	" 3/2 " = 1
 *  	" 3+5 / 2 " = 5
 *   
 * @author Hxkandwal
 */
public class BasicCalculatorII extends AbstractCustomTestRunner {
	
	private static BasicCalculatorII _instance = new BasicCalculatorII();
	
	public int _calculate(String s) {
		int len;
		if (s == null || (len = s.length()) == 0) return 0;
		Stack<Integer> stack = new Stack<Integer>();
		
		int num = 0;
		char sign = '+';
		
		for (int i = 0; i < len; i++) {
		
			if (Character.isDigit(s.charAt(i))) {
				num = num * 10 + s.charAt(i) - '0';
			}
			if ((!Character.isDigit(s.charAt(i)) && ' ' != s.charAt(i)) || i == len - 1) {
				if (sign == '-') {
					stack.push(-num);
				}
				if (sign == '+') {
					stack.push(num);
				}
				if (sign == '*') {
					stack.push(stack.pop() * num);
				}
				if (sign == '/') {
					stack.push(stack.pop() / num);
				}
				sign = s.charAt(i);
				num = 0;
			}
		}

		int re = 0;
		for (int i : stack) re += i;
		
		return re;
    }

	public int _calculateBetter(String s) {
		int num = 0;
		char prevCh = ' ';
		Stack<Integer> stack = new Stack <>();
		for (int idx = 0; idx <= s.length (); idx ++) {
			char c = idx == s.length () ? '#' : s.charAt (idx);
			if (c == ' ') continue;
			if (c >= '0' && c <= '9') num = 10 * num + (c - '0');
			else {
				if (prevCh == '*' || prevCh == '/') {
					int prev = stack.pop ();
					stack.push ((prevCh == '*') ? prev * num : prev / num);
				} else stack.push (prevCh == '-' ? -num : num);
				num = 0;
				if (c == '#') {
					while (stack.size() > 1) stack.push (stack.pop () + stack.pop());
				} else prevCh = c;
			}
		}
		return stack.pop ();
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("1 + 1", 2);
		_instance.runTest("2-1 + 2", 3);
		_instance.runTest("3 + 2 * 2", 7);
		_instance.runTest("12 - 3 * 4", 0);
		_instance.runTest("2 * 3 * 4", 24);
		_instance.runTest("3/2", 1);
		_instance.runTest(" 3+5 / 2 ", 5);
	}

	public void runTest(final String s, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	} 	
	
}
