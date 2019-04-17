package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 678. Valid Parenthesis String
 *
 * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid.
 * We define the validity of a string by these rules:
 *
 *  Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 *  Any right parenthesis ')' must have a corresponding left parenthesis '('.
 *  Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 *  '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 *
 * An empty string is also valid.
 *
 * Example 1:
 *              Input: "()"
 *              Output: True
 *
 * Example 2:
 *              Input: "(*)"
 *              Output: True
 *
 * Example 3:
 *              Input: "(*))"
 *              Output: True
 *
 * Note:
 *  The string size will be in the range [1, 100].
 *
 * @author Hxkandwal
 */
public class ValidParenthesisString extends AbstractCustomTestRunner {

    // greedy approach
    public boolean _checkValidString(String s) {
        int low = 0, high = 0;
        for (char c : s.toCharArray()) {
            low += c == '(' ? 1 : -1;
            high += c != ')' ? 1 : -1;
            if (high < 0) break;
            low = Math.max (low, 0);
        }
        return low == 0;
    }

    // size based DP
    public boolean _checkValidStringDP(String s) {
        int n = s.length();
        if (n == 0) return true;
        boolean [][] dp = new boolean [n][n];

        // base case, fill up extra length = 1 columns.
        for (int idx = 0; idx < n; idx ++) {
            if (s.charAt(idx) == '*') dp [idx][idx] = true;
            if (idx + 1 < n &&
                    (s.charAt(idx) == '*' || s.charAt(idx) == '(') &&
                    (s.charAt(idx + 1) == '*' || s.charAt(idx + 1) == ')')
            )
                dp [idx][idx + 1] = true;
        }

        /**
         * fill up [idx, idx + len] using other small sized answers. (loop over to fill internally
         * considering all possible combinations)
         */

        // substring dp example
        // bottom up dp
        for (int len = 2; len < n; len ++) {
            // starting, ending point
            for (int idx = 0; idx + len < n; idx ++) {
                // fill internally all combinations O(n)
                if (s.charAt(idx) == '*' && dp [idx + 1][idx + len])
                    dp [idx][idx + len] = true;
                else if (s.charAt(idx) == '*' || s.charAt(idx) == '(') {
                    for (int k = idx + 1; k <= idx + len; k ++) {
                        if ((s.charAt(k) == '*' || s.charAt(k) == ')') &&
                                (k == idx + 1 || dp [idx + 1][k - 1]) &&
                                (k == idx + len || dp [k + 1][idx + len])) {
                            dp [idx][idx + len] = true;
                        }
                    }
                }
            }
        }
        return dp [0][n - 1];
    }

}
