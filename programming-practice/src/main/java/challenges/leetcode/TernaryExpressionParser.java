package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Stack;

/**
 * 439. Ternary Expression Parser
 *
 * Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression. You can always assume that the given expression is
 * valid and only consists of digits 0-9, ?, :, T and F (T and F represent True and False respectively).
 *
 * Note:
 *  The length of the given string is ≤ 10000.
 *  Each number will contain only one digit.
 *  The conditional expressions group right-to-left (as usual in most languages).
 *  The condition will always be either T or F. That is, the condition will never be a digit.
 *  The result of the expression will always evaluate to either a digit 0-9, T or F.
 *
 * Example 1:
 *              Input: "T?2:3"
 *              Output: "2"
 *              Explanation: If true, then result is 2; otherwise result is 3.
 *
 * Example 2:
 *              Input: "F?1:T?4:5"
 *              Output: "4"
 *              Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
 *
 *                           "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
 *                           -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
 *                           -> "4"                                    -> "4"
 *
 * Example 3:
 *              Input: "T?T?F:5:3"
 *              Output: "F"
 *              Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
 *                           "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
 *                           -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
 *                           -> "F"                                    -> "F"
 *
 * @author Hxkandwal
 */
public class TernaryExpressionParser extends AbstractCustomTestRunner {

    // ? -> enter stack, : -> pop and compare
    public String _parseTernary(String expression) {
        Stack<String> stack = new Stack<>();
        int idx = 0;
        String build = "";
        for (char c : expression.toCharArray()) {
            if (c == '?') {
                stack.push (build);
                build = "";
            } else if (c == ':') {
                String val = stack.pop();
                if (val.equals("T")) {
                    // got to check if there some some "F" present back in stack. If yes, we are following a path we should avoid (case False)
                    // and let stack clean up.
                    boolean foundFalse = false;
                    for (String str : stack) if (str.equals("F")) foundFalse = true;
                    if (!foundFalse) return build;
                }
                build = "";
            } else build += c;
        }
        // return will be called here for case: "F?2:3", build will return from here with value as 3.
        return build;
    }
}
