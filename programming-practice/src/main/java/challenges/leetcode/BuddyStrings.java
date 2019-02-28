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
        int[] chrs = new int [256];
        boolean containsMultiple = false;
        for (char c : A.toCharArray()) if (chrs [c] ++ == 1) containsMultiple = true;
        for (char c : B.toCharArray()) chrs [c] --;
        for (int i : chrs) if (i != 0) return false;
        if (A.equals(B)) return containsMultiple;

        int idx1 = -1;
        for (int idx = 0; idx < A.length(); idx ++) {
            if (A.charAt(idx) != B.charAt(idx)) {
                if (idx1 == -1) idx1 = idx;
                else {
                    String swappedA = A.substring(0, idx1) +
                        B.charAt(idx1) +
                        A.substring (idx1 + 1, idx) +
                        A.charAt(idx1) +
                        A.substring(idx + 1);

                    return swappedA.equals (B);
                }
            }
        }
        return false;
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
