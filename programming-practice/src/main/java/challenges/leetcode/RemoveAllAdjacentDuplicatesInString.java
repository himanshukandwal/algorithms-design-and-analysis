package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Stack;

/**
 * 1047. Remove All Adjacent Duplicates In String
 *
 * Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.
 * We repeatedly make duplicate removals on S until we no longer can. Return the final string after all such duplicate removals have been made.
 * It is guaranteed the answer is unique.
 *
 * Example 1:
 *      Input: "abbaca"
 *      Output: "ca"
 *      Explanation: For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.
 *                   The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 *
 * Note:
 *  1 <= S.length <= 20000
 *  S consists only of English lowercase letters.
 *
 * @author Hxkandwal
 */
public class RemoveAllAdjacentDuplicatesInString extends AbstractCustomTestRunner {

    public String _removeDuplicatesFastest(String S) {
        char [] stack = S.toCharArray();
        int udx = 0;
        for (int idx = 1; idx < stack.length; idx ++) {
            if (udx >= 0 && stack [udx] == stack [idx]) udx --;
            else stack [++ udx] = stack [idx];
        }
        return new String(stack, 0, udx + 1);
    }

    public String _removeDuplicatesSimple(String S) {
        StringBuilder ans = new StringBuilder(S);
        for (int idx = ans.length() - 2; idx >= 0; idx --) {
            if (idx + 1 < ans.length() && ans.charAt(idx) == ans.charAt(idx + 1)) {
                ans.deleteCharAt(idx + 1);
                ans.deleteCharAt(idx);
            }
        }
        return ans.toString();
    }

    public String _removeDuplicatesStack(String S) {
        Stack<Character> stack = new Stack<>();
        for (char c : S.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) stack.pop ();
            else stack.push (c);
        }
        StringBuilder ans = new StringBuilder();
        for (char c : stack) ans.append(c);
        return ans.toString();
    }
}
