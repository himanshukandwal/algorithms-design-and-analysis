package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Stack;

/**
 * 856. Score of Parentheses
 *
 * Given a balanced parentheses string S, compute the score of the string based on the following rule:
 *
 * 1. () has score 1
 * 2. AB has score A + B, where A and B are balanced parentheses strings.
 * 3. (A) has score 2 * A, where A is a balanced parentheses string.
 *
 * Example 1:
 *      Input: "()"
 *      Output: 1
 *
 * Example 2:
 *      Input: "(())"
 *      Output: 2
 *
 * Example 3:
 *      Input: "()()"
 *      Output: 2
 *
 * Example 4:
 *      Input: "(()(()))"
 *      Output: 6
 *
 * Note:
 *  1. S is a balanced parentheses string, containing only ( and ).
 *  2. 2 <= S.length <= 50
 *
 * @author hxkandwal
 */
public class ScoreOfParentheses extends AbstractCustomTestRunner {

    public int scoreOfParentheses(String S) {
        int score = 0;
        Stack<int[]> stack = new Stack<>();
        for (int idx = 0; idx < S.length(); idx ++) {
            if (S.charAt(idx) == '(') stack.push (new int [] { 0, idx});
            else {
                int val = stack.peek()[0], jdx = stack.pop()[1];
                if (idx - jdx == 1) val = 1;
                else val = 2 * val;
                if (stack.isEmpty()) score += val;
                else stack.peek()[0] += val;
            }
        }
        return score;
    }

}
