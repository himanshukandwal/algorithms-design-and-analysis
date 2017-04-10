package challenges.leetcode;

import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 150. Evaluate Reverse Polish Notation
 * 
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * 
 * Some examples:
 * 		["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * 		["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 *  
 * @author Hxkandwal
 */
public class EvaluateReversePolishNotation extends AbstractCustomTestRunner {
	
	public int evalRPN(String[] tokens) {
        if (tokens.length == 0) return 0;
        Stack<Integer> stk = new Stack<>();
        for (String token : tokens) {
            if (token.matches("[^0-9]")) {
                int o1 = stk.pop ();
                int o2 = stk.pop ();
                stk.push (token.equals ("+") ? o2 + o1 : (token.equals ("-") ? o2 - o1 
                       : (token.equals ("*") ? o1 * o2 : o2 / o1 )));
            } else stk.push (Integer.valueOf(token));
        }
        return stk.pop();
    }

}
