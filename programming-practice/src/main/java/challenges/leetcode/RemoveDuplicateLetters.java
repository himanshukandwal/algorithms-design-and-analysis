package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Stack;

/**
 * 316. Remove Duplicate Letters
 *
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once.
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * Example 1:
 *      Input: "bcabc"
 *      Output: "abc"
 *
 * Example 2:
 *      Input: "cbacdcbc"
 *      Output: "acdb"
 *
 * @author hxkandwal
 */
public class RemoveDuplicateLetters extends AbstractCustomTestRunner {

    public String _removeDuplicateLetters(String s) {
        if (s.length() == 0) return s;

        int[] count = new int [256];
        boolean[] seen = new boolean [256];
        for (char c : s.toCharArray()) count [c] ++;
        Stack<Character> stk = new Stack<>();

        for (char c : s.toCharArray()) {
            count [c] --;
            if (seen [c]) continue;

            while (!stk.isEmpty() && stk.peek() > c && count [stk.peek()] > 0) seen [stk.pop()] = false;
            stk.push (c);
            seen [c] = true;
        }

        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < stk.size(); idx ++) sb.append(stk.get(idx));
        return sb.toString();
    }
}
