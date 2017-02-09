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
		innerRecursion (collector, new StringBuilder(), left, right);
		return collector;
    }
	
	private static void innerRecursion(List<String> collector, StringBuilder answer, int left, int right) {
		if (left == 0) 
			collector.add(answer.toString());
		else {
			while (left -- > 0) {
				answer.append("(");
				innerRecursion (collector, new StringBuilder(), left, right);
			}
			
			while (right -- > 0) {
				answer.append(")");
				innerRecursion (collector, new StringBuilder(), left, right);
			}
		}
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(1, new ArrayList() {{ add("()"); }});
	}

	public void runTest(final int number, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { number });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
		
	
}
