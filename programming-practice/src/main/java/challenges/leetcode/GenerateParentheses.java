package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 22. Generate Parentheses
 * 
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * 
 * For example, given n = 3, a solution set is:
 * 
 * 	 "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()" 
 * 
 * @author Hxkandwal
 */
public class GenerateParentheses extends AbstractCustomTestRunner {

	private static GenerateParentheses _instance = new GenerateParentheses();
	
	public List<String> _generateParenthesis(int n) {
		List<String> ans = new ArrayList<>();
        generate (ans, "", n, n);
        return ans;
    }
    
    private void generate (List<String> ans, String build, int left, int right) {
        if (left > right) return;
        if (left == 0 && right == 0) { ans.add (build); return; }
        if (left > 0) generate (ans, build + "(", left - 1, right);
        if (right > left) generate (ans, build + ")", left, right - 1);
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(3, Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()"));
	}

	@SuppressWarnings("unchecked")
	public void runTest(final int number, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { number });

		for (Object answer : answers)
			assertThat((List<String>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
