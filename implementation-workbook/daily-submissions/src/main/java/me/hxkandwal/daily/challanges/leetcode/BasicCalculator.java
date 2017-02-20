package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import java.util.Stack;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 224. Basic Calculator
 * 
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 * 
 * You may assume that the given expression is always valid.
 * 
 * Some examples:
 * 		"1 + 1" = 2
 * 		" 2-1 + 2 " = 3
 * 		"(1+(4+5+2)-3)+(6+8)" = 23
 * 
 * @author Hxkandwal
 */
public class BasicCalculator extends AbstractCustomTestRunner {
	
	private static BasicCalculator _instance = new BasicCalculator();
	
	private BasicCalculator() {}
	
	/*
	 * Principle:
	 * 		(Sign before '+'/'-') = (This context sign);
	 * 		(Sign after '+'/'-') = (This context sign) * (1 or -1);
	 * 
	 * Algorithm:
	 * 		Start from +1 sign and scan s from left to right;
	 * 		if c == digit: This number = Last digit * 10 + This digit;
	 * 		if c == '+': Add num to result before this sign; This sign = Last context sign * 1; clear num;
	 * 		if c == '-': Add num to result before this sign; This sign = Last context sign * -1; clear num;
	 * 		if c == '(': Push this context sign to stack;
	 * 		if c == ')': Pop this context and we come back to last context;
	 * 
	 * Add the last num. This is because we only add number after '+' / '-'.
	 */
	public int _calculateBetter(String s) {
		if (s == null || s.isEmpty()) return 0;
        
        Stack<Integer> stk = new Stack<>();         // <<<<<<<<<<<<<<<<< sign stack
        int result = 0;
        int sign = 1;
        int num = 0;
        
        for (int idx = 0; idx < s.length(); idx ++) {
            char ch = s.charAt (idx);
            
            if (Character.isDigit(ch))
                num = 10 * num + (ch - '0');
            else if (ch == '+' || ch == '-') {
                result += sign * num;
                sign = (!stk.isEmpty() ? stk.peek() : 1) * (ch == '+' ? 1 : -1);
                num = 0;
            } 
            else if (ch == '(')
                stk.push(sign);
            else if (ch == ')')
                stk.pop();
        }
        
        return result += sign * num;
	}
	
	// my take
    public int calculate(String s) {
        if (s == null || s.isEmpty()) return 0;
        
        Stack<String> stk = new Stack<>();
        int value = 0, idx = 0;
        s = s.trim();
        String pstr = null; 
        
        while (idx < s.length()) {
            char ch = s.charAt(idx);
            if (ch == ' ') { idx ++; continue; }
            
            if (ch == ')') {
                value = Integer.valueOf (stk.pop());
                while (! (pstr = stk.pop()).equals ("("))
                    value += (pstr.equals ("+") ? 1 : -1 ) * Integer.valueOf (stk.pop());
                String prefix = "";
                if (!stk.isEmpty() && stk.peek().equals("-")) {
                	stk.pop(); stk.push("+"); prefix = "-";
                	if (value < 0) { value = -value; prefix = ""; };
                }
                stk.push (prefix + String.valueOf(value)); 
                idx ++;
            } else {
                if (ch >= '0' && ch <= '9') {
                    int start = idx;
                    for (; idx < s.length() && s.charAt(idx) >= '0' && s.charAt(idx) <= '9';) idx ++;
                    String prefix = "";
                    if (!stk.isEmpty() && stk.peek().equals("-")) {
                    	stk.pop(); stk.push("+"); prefix = "-";
                    }
                    stk.push (prefix + s.substring (start, idx));
                } else {
                	stk.push(String.valueOf(ch));
                    idx ++;
                }
            }
        }
        
        if (!stk.isEmpty()) {
            value = Integer.valueOf (stk.pop());
            while (! stk.isEmpty()) {
                pstr = stk.pop();
                value += (pstr.equals ("+") ? 1 : -1 ) * Integer.valueOf (stk.pop());
            }
        }
        
        return value;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("1 + 1", 2);
		_instance.runTest("2-1 + 2", 3);
		_instance.runTest("(1+(4+5+2)-3)+(6+8)", 23);
	}

	public void runTest(final String s, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}    

}
