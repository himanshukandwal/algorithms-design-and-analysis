package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Stack;

/**
 * 1003. Check If Word Is Valid After Substitutions
 *
 * We are given that the string "abc" is valid.
 * From any valid string V, we may split V into two pieces X and Y such that X + Y (X concatenated with Y) is equal to V.  (X or Y may be empty.)
 * Then, X + "abc" + Y is also valid.
 *
 * If for example S = "abc", then examples of valid strings are: "abc", "aabcbc", "abcabc", "abcabcababcc".
 * Examples of invalid strings are: "abccba", "ab", "cababc", "bac".
 *
 * Return true if and only if the given string S is valid.
 *
 * Example 1:
 *      Input: "aabcbc"
 *      Output: true
 *      Explanation: We start with the valid string "abc".
 *                   Then we can insert another "abc" between "a" and "bc", resulting in "a" + "abc" + "bc" which is "aabcbc".
 *
 * Example 2:
 *      Input: "abcabcababcc"
 *      Output: true
 *      Explanation: "abcabcabc" is valid after consecutive insertings of "abc".
 *                   Then we can insert "abc" before the last letter, resulting in "abcabcab" + "abc" + "c" which is "abcabcababcc".
 *
 * Example 3:
 *      Input: "abccba"
 *      Output: false
 *
 * Example 4:
 *      Input: "cababc"
 *      Output: false
 *
 * Note:
 *  1 <= S.length <= 20000
 *  S[i] is 'a', 'b', or 'c'
 *
 * @author Hxkandwal
 */
public class CheckIfWordIsValidAfterSubstitutions extends AbstractCustomTestRunner {

    public boolean _isValidBetter(String S) {
        Stack<Character> stack = new Stack<>();
        for (char c : S.toCharArray()) {
            if (c == 'c') {
                if (stack.isEmpty() || stack.pop() != 'b') return false;
                if (stack.isEmpty() || stack.pop() != 'a') return false;
            } else stack.push(c);
        }
        return stack.isEmpty();
    }

    // strategy: try to eat "abc" word -> prepare string without it and repeat the same process.
    public boolean isValidRecursive(String S) {
        int idx = S.indexOf("abc");
        if (idx >= 0) {
            if (S.length() == 3) return true;
            else return isValidRecursive(S.substring (0, idx) + S.substring (idx + 3));
        }
        return false;
    }

    public boolean isValidIterative(String S) {
        boolean changed = true;
        while (changed) {
            changed = false;
            int idx = S.indexOf("abc");
            if (idx >= 0) {
                changed = true;
                S = S.substring (0, idx) + S.substring (idx + 3);
            }
            else if (S.startsWith("a") && S.endsWith("bc")) {
                changed = true;
                S = S.substring (1, S.length() - 2);
            }
            else if (S.startsWith("ab") && S.endsWith("c")) {
                changed = true;
                S = S.substring (2, S.length() - 1);
            }
        }
        return S.length() == 0;
    }
}
