package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 767. Reorganize String
 *
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 * If possible, output any possible result.  If not possible, return the empty string.
 *
 * Example 1:
 *          Input: S = "aab"
 *          Output: "aba"
 *
 * Example 2:
 *          Input: S = "aaab"
 *          Output: ""
 *
 * Note:
 *  S will consist of lowercase letters and have length in range [1, 500].
 *
 * @author Hxkandwal
 */
public class ReorganizeString extends AbstractCustomTestRunner {

    private static ReorganizeString _instance = new ReorganizeString();

    public String _reorganizeString(String S) {
        StringBuilder ans = new StringBuilder();
        int[] map = new int [26];
        for (char c : S.toCharArray()) map [c - 'a'] ++;

        int [] pos = new int [26];
        Arrays.fill (pos, -1);

        for (int idx = 0; idx < S.length(); idx ++) {
            int maxIdx = -1;
            for (int j = 0; j < 26; j ++) {
                if (map [j] > 0 &&
                        (pos [j] < 0 || idx - pos [j] >= 2) &&
                        (maxIdx < 0 || map [j] > map [maxIdx]))
                    maxIdx = j;
            }

            if (maxIdx < 0) return "";
            map [maxIdx] --;
            pos [maxIdx] = idx;
            ans.append ((char)('a' + maxIdx));
        }
        return ans.toString();
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest("aab", "aba");
        _instance.runTest("aaab", "");
    }

    public void runTest(final String S, final String expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { S });

        for (Object answer : answers)
            assertThat((String) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
