package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 727. Minimum Window Subsequence
 *
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
 * If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows,
 * return the one with the left-most starting index.
 *
 * Example 1:
 *              Input: S = "abcdebdde", T = "bde"
 *              Output: "bcde"
 *              Explanation: "bcde" is the answer because it occurs before "bdde" which has the same length.
 *                           "deb" is not a smaller window because the elements of T in the window must occur in order.
 *
 * Note:
 *  All the strings in the input will only contain lowercase letters.
 *  The length of S will be in the range [1, 20000].
 *  The length of T will be in the range [1, 100].
 *
 * @author Hxkandwal
 */
public class MinimumWindowSubsequence extends AbstractCustomTestRunner {

    private static MinimumWindowSubsequence _instance = new MinimumWindowSubsequence();

    public String _minWindow(String S, String T) {
        int tidx = 0;
        String ans = S;
        for (int idx = 0, start = 0; start < S.length(); idx ++) {
            if (S.charAt(idx) != T.charAt(tidx)) {
                if (tidx == 0) start = idx + 1;
            } else {
                tidx ++;
                if (tidx == T.length() && ans.length() > S.substring(start, idx + 1).length())
                    ans = S.substring(start, idx + 1);
            }
            if (tidx >= T.length() || idx + 1 == S.length()) {
                tidx = 0;
                start ++;
                idx = start - 1;
            }
        }
        return ans.equals(S) ? "" : ans;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(
                "dlnizvdvourutyysrupkgnjfppstcriscwfbfxggepvrzqxyuniktgbpcujntijkrcbvtkupwwmsylrxwhmvxbxebrwqqyllyzhkspiywlgngbcvpdughbnidjovegnyqyhfnnqxwvgaxpgyokcgmxuloirvgwqluiffxtqtojqzmjejbwpxbeejzdhnyrliorpmjopuef",
                "vfvyzhpnme",
                "vourutyysrupkgnjfppstcriscwfbfxggepvrzqxyuniktgbpcujntijkrcbvtkupwwmsylrxwhmvxbxebrwqqyllyzhkspiywlgngbcvpdughbnidjovegnyqyhfnnqxwvgaxpgyokcgmxuloirvgwqluiffxtqtojqzmje"
        );
    }

    public void runTest(final String a, final String b, final String expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { a, b });

        for (Object answer : answers)
            assertThat((String) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
