package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static com.google.common.truth.Truth.assertThat;

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

	public boolean _isValid(String s) {
		Map<Character, Character> map = new HashMap<>();
		map.put('(', ')');  map.put('{', '}');  map.put('[', ']');
		Stack<Character> stk = new Stack<>();
		for (char ch : s.toCharArray()) {
			if (map.keySet().contains(ch)) stk.push (map.get(ch));
			else if (stk.isEmpty() || ch != stk.pop()) return false;
		}
		return stk.isEmpty();
	}

	public boolean _isValidOther(String s) {
		Map<Character, Character> map = new HashMap<Character, Character>() {{ put (')', '('); put (']', '['); put ('}', '{'); }};
		Stack<Character> stk = new Stack<>();
		for (char c : s.toCharArray()) {
			if (map.containsKey(c)) {
				if (stk.isEmpty() || stk.pop() != map.get(c)) return false;
			} else stk.push(c);
		}
		return stk.isEmpty();
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("", true);
		_instance.runTest("()", true);
		_instance.runTest("[]", true);
		_instance.runTest("{}", true);
		_instance.runTest("()[]{}", true);
		_instance.runTest("[)", false);
		_instance.runTest("([)]", false);
		_instance.runTest("{)", false);
		_instance.runTest(")}{({))[{{[}", false);
	}

	public void runTest(final String s, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
