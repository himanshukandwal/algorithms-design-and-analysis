package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import challenges.AbstractCustomTestRunner;
import java.util.List;

/**
 * 859. Buddy Strings
 *
 * Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.
 *
 * Example 1:
 *      Input: A = "ab", B = "ba"
 *      Output: true
 *
 * Example 2:
 *      Input: A = "ab", B = "ab"
 *      Output: false
 *
 * Example 3:
 *      Input: A = "aa", B = "aa"
 *      Output: true
 *
 * Example 4:
 *      Input: A = "aaaaaaabc", B = "aaaaaaacb"
 *      Output: true
 *
 * Example 5:
 *      Input: A = "", B = "aa"
 *      Output: false
 *
 * Note:
 *  0 <= A.length <= 20000
 *  0 <= B.length <= 20000
 *  A and B consist only of lowercase letters.
 *
 * @author hxkandwal
 */
public class BuddyStrings extends AbstractCustomTestRunner {

    private static BuddyStrings _instance = new BuddyStrings();

    public boolean _buddyStrings(String A, String B) {
        if (A.length() != B.length()) return false;
        if (A.equals(B)) {
            int [] arr = new int [256];
            for (char c : A.toCharArray()) arr [c] ++;
            for (int c : arr) if (c > 1) return true;
            return false;
        } else {
            int first = -1, second = -1;
            for (int idx = 0; idx < A.length(); idx ++) {
                if (A.charAt(idx) != B.charAt(idx)) {
                    if (first == -1) first = idx;
                    else if (second == -1) second = idx;
                    else return false;
                }
            }
            return A.charAt(first) == B.charAt(second) && A.charAt(second) == B.charAt(first);
        }
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest("aaaaaaaaa", "aaaaaaaaa", true);
    }

    public void runTest(final String A, final String B, final boolean expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { A, B });

        for (Object answer : answers)
            assertThat((Boolean) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
