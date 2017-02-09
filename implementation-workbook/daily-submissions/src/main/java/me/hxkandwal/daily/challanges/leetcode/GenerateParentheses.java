package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
public class GenerateParentheses extends AbstractCustomTestRunner {

	private static GenerateParentheses _instance = new GenerateParentheses();
	
	private GenerateParentheses() {}
	
	public static List<String> _generateParenthesis(int n) {
		int left = n, right = n;
        
		List<String> collector = new ArrayList<>();
		innerRecursion (collector, "", left, right);
		return collector;
    }
	
	private static void innerRecursion(List<String> collector, String answer, int left, int right) {
		if (left < 0 || right < left) return;
		
		if (left == 0 && right == 0) { 
			collector.add (answer);
		} else {
			if (left > 0) innerRecursion (collector, answer + "(", left - 1, right);
			
			if (right > left) innerRecursion (collector, answer + ")", left, right - 1);
		}
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(3, new ArrayList() {{ add("((()))");  add("(()())"); add("(())()"); add("()(())"); add("()()()"); }});
	}

	public void runTest(final int number, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { number });

		for (Object answer : answers)
			assertThat((List<String>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
