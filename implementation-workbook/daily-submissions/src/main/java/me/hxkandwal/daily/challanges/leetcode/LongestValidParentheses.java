package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
	
	public LongestValidParentheses() {}

	public int longestValidParentheses(String s) {

		return 0;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("()", 2);
		_instance.runTest("(()", 2);
		_instance.runTest("(()((()()", 4);
		_instance.runTest("(()((()())))", 12);
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
