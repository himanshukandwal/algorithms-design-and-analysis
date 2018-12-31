package challenges.codefights;

import challenges.AbstractCustomTestRunner;

import java.util.Stack;

/**
 * Reverse Parentheses
 *
 * You have a string s that consists of English letters, punctuation marks, whitespace characters, and brackets. It is guaranteed that the parentheses in s form a regular
 * bracket sequence. Your task is to reverse the strings contained in each pair of matching parentheses, starting from the innermost pair. The results string should not
 * contain any parentheses.
 *
 * Example:
 *      For string s = "a(bc)de", the output should be reverseParentheses(s) = "acbde".
 *
 * link: https://app.codesignal.com/arcade/intro/level-3/3o6QFqgYSontKsyk4/description
 *
 * @author Hxkandwal
 */
public class ReverseParentheses extends AbstractCustomTestRunner {

    String reverseParentheses(String s) {
        StringBuilder ans = new StringBuilder();
        Stack<StringBuilder> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(new StringBuilder());
            else if (c == ')') {
                StringBuilder top = stack.pop().reverse();
                if (!stack.isEmpty()) stack.peek().append(top);
                else ans.append(top);
            }
            else if (!stack.isEmpty()) stack.peek().append(c);
            else ans.append(c);
        }
        return ans.toString();
    }

}
