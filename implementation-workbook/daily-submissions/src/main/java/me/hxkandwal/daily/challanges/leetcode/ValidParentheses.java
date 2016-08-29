package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 20. Valid Parentheses
 * 
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * 
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * 
 * @author Hxkandwal
 *
 */
public class ValidParentheses extends AbstractCustomTestRunner {

	private static ValidParentheses _instance = new ValidParentheses();
	
	private ValidParentheses() {}
	
	public boolean _isValid(String s) {
		if (s == null || s.length() == 0)
			return true;
		
		if (s.length() % 2 != 0)
			return false;
		
		Map<Character, Character> dictionary = new HashMap<>();
		dictionary.put(')', '(');
		dictionary.put('}', '{');
		dictionary.put(']', '[');
		
		Stack<Character> parathesisStack = new Stack<>();
		for (int idx = 0; idx < s.length(); idx++) {
			if (!dictionary.keySet().contains(s.charAt(idx))) 
				parathesisStack.push(s.charAt(idx));
			else
				if (parathesisStack.isEmpty() || (parathesisStack.pop() != dictionary.get(s.charAt(idx)))) 
					return false;
		}
		
		return parathesisStack.isEmpty();
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("", true);
		_instance.runTest(null, true);
		_instance.runTest("()", true);
		_instance.runTest("[]", true);
		_instance.runTest("{}", true);
		_instance.runTest("()[]{}", true);
		_instance.runTest("[)", false);
		_instance.runTest("([)]", false);
		_instance.runTest("{)", false);
		_instance.runTest(")}{({))[{{[}", false);
		
		System.out.println("ok!");
	}

	public void runTest(final String s, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
	}
	
}
