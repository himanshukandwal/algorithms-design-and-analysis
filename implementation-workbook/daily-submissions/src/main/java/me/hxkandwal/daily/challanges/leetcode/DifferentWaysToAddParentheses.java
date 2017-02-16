package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 241. Different Ways to Add Parentheses
 * 
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers 
 * and operators. The valid operators are +, - and *.
 * 
 * Example 1
 * 	Input: "2-1-1".
 * 
 * 	((2-1)-1) = 0
 * 	(2-(1-1)) = 2
 * 
 * 	Output: [0, 2]
 * 
 * Example 2
 * 	Input: "2*3-4*5"
 * 	
 * 	(2*(3-(4*5))) = -34
 * 	((2*3)-(4*5)) = -14
 * 	((2*(3-4))*5) = -10
 * 	(2*((3-4)*5)) = -10
 * 	(((2*3)-4)*5) = 10
 * 
 * Output: [-34, -14, -10, -10, 10]
 * 
 * @author Hxkandwal
 */
@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class DifferentWaysToAddParentheses extends AbstractCustomTestRunner {
	
	private static DifferentWaysToAddParentheses _instance = new DifferentWaysToAddParentheses();
	
	private DifferentWaysToAddParentheses() {}
	
	public List<Integer> _diffWaysToComputeFaster(String input) {
		List<Integer> answer = new ArrayList<>();
		
		if (!input.matches(".*[+*-].*")) { answer.add(Integer.valueOf(input)); return answer; }
		
		for (int idx = 0; idx < input.length(); idx ++) {
			char ch = input.charAt(idx);
			if (ch == '+' || ch == '-' || ch == '*')
				for (int a : _diffWaysToComputeFaster(input.substring(0, idx)))
					for (int b : _diffWaysToComputeFaster(input.substring(idx + 1)))
						answer.add ((ch == '+' ? a + b : (ch == '-' ? a - b : a * b)));
		}
		
		return answer;
	}
		
    public List<Integer> diffWaysToCompute(String input) {
    	int parenthesis = 0;
    	for (int idx = 0; idx < input.length(); idx ++) 
    		if (!(input.charAt(idx) >= '0' && input.charAt(idx) <= '9')) parenthesis ++;
    	
    	List<Integer> answer = new ArrayList<>();
    	
    	if (parenthesis > 0) innerRecursion(answer, parenthesis, parenthesis, input, 0);
    	
    	return answer;
    }	
    
    private static void innerRecursion(List<Integer> answer, int left, int right, String s, int idx) {
    	if (idx >= s.length() || right < left || left < 0) return;
    	
    	boolean processed = false;
    	if (left == 0 && right == 0) {
    		answer.add (evaluate(s));
    		return;
    	}
    	
    	if (left > 0 && (idx == 0 || (!Character.isDigit(s.charAt(idx - 1)) && !Character.isDigit(s.charAt(idx + 1))))) {
    		innerRecursion (answer, left - 1, right, s.substring(0, idx) + "(" + s.substring(idx), idx + 1);
    		processed = true;
    	}
    	
    	if (right > left && s.charAt(idx - 2) != '(') {
    		processed = true;
    		
    		if (idx + 1 == s.length())
    			innerRecursion (answer, left, right - 1, s.substring(0, idx + 1) + ")", idx + 1);
    		else if (Character.isDigit(s.charAt(idx - 1)) && !Character.isDigit(s.charAt(idx)) && Character.isDigit(s.charAt(idx + 1)))
    			innerRecursion (answer, left, right - 1, s.substring(0, idx) + ")" + s.substring(idx), idx + 1);
    		else 
    			processed = false;
    	}		
    		
    	if (!processed) innerRecursion (answer, left, right, s, idx + 1);
    }
	
	public static Integer evaluate(String sb) {
		Stack<Object> stack = new Stack<>();
		
		for (int idx = 0; idx < sb.length(); idx ++) {
			char ch = sb.charAt(idx);
			
			if (ch == ')') {
				int var1 = (Integer) stack.pop();
				String operation = (String) stack.pop();
				int var2 = (Integer) stack.pop();
				stack.pop();
				
				stack.push((operation.equals("+") ? var2 + var1 : (operation.equals("-") ? var2 - var1 : var2 * var1)));
			} else stack.push(ch >= '0' && ch <= '9' ? ch - '0' : Character.toString(ch));
		}
		
		return (Integer) stack.pop();
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("2*3", new ArrayList() {{ add(6); }});
		_instance.runTest("2*3-4*5", new ArrayList() {{ add(-34); add(-10); add(-14); add(-10); add(10); }});
	}

	public void runTest(final String input, final List<Integer> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((List<Integer>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
    
}
