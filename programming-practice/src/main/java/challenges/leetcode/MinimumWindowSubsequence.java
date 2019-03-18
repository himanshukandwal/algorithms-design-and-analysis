package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.Arrays;
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

    // Dynamic programming solution
    public String _minWindowDP(String S, String T) {
        int m = S.length(), n = T.length();
        int [][] dp = new int [n + 1][m + 1];
        for (int idx = 0; idx <= m; idx ++) dp [0][idx] = idx + 1;

        for (int r = 0; r < T.length(); r ++)
            for (int c = r; c < S.length(); c ++)
                dp [r + 1][c + 1] = (S.charAt(c) == T.charAt(r)) ? dp [r][c] : dp [r + 1][c];

        String ans = S;
        for (int c = 0; c <= m; c ++)
            if (dp [n][c] > 0 && ans.length() > S.substring (dp [n][c] - 1, c).length())
                ans = S.substring (dp [n][c] - 1, c);

        return ans.equals(S) ? "" : ans;
    }

    // simple scanning approach (using the idea from IsSubsequence)
    public String _minWindowInitial(String S, String T) {
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

    // fastest approach
    public String _minWindowFastest(String S, String T) {
        int i = -1;
        String res = "";
        while (true) {
            for (char c: T.toCharArray()) {
                i = S.indexOf(c, i + 1);
                if (i == -1) return res;
            }
            /**
             * this is exactly what I was thinking, run isSubsequence algo, the the last index for T in S would be good, but we are not sure about the first
             * index in S. such as:
             * "daabaac", abc => forward search: "aabaac", backward search: "abaac" (2nd pass)
             * "dabbabc", abc => forward search: "abbabc", backward search: "abc"   (2nd pass)
             *  (This is also mentioned in other 7ms solution) -> this is optimization part, need to check from right to left(trick);
             *
             * maybe we can use KMP after we are done with the two passes to save some time if we have suffix same as prefix. case: aaabaaa.
             * */
            int I = ++ i;
            for (int j = T.length() - 1; j > -1; j --)
                i = S.lastIndexOf(T.charAt(j), i - 1);

            if (res.equals("") || res.length() > I - i) res = S.substring(i ++, I); // move ahead as well (like we did in our initial approach, once we match the sequence.
        }
    }

    // parallel window solution. (BFS styled) - Interesting
    public String _minWindowArticleDPSolution(String S, String T) {
        int N = S.length();
        int[] last = new int[26];
        int[][] nxt = new int[N][26];
        Arrays.fill(last, -1);

        // evaluate expression for this loop: Arrays.stream(nxt).map(a -> Arrays.toString(a)).collect(Collectors.toList())
        for (int i = N - 1; i >= 0; --i) {
            last[S.charAt(i) - 'a'] = i;
            for (int k = 0; k < 26; ++k) {
                nxt[i][k] = last[k];
            }
        }

        // evaluate expression for this collection: windows.stream().map(arr -> Arrays.toString(arr)).collect(Collectors.toList())
        List<int[]> windows = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            if (S.charAt(i) == T.charAt(0))
                windows.add(new int[] { i, i }); // BFS styled way of prepration, put all the starting points in an array (start-index, end-index)
        }
        for (int j = 1; j < T.length(); ++j) {   // start with T (index: 1) as all index: 0 are already in parallel windows
            int letterIndex = T.charAt(j) - 'a';
            for (int[] window: windows) {
                if (window[1] < N-1 && nxt[window[1]+1][letterIndex] >= 0) {    // does window[1] (end index) neighbor (window[1] + 1) has letterIndex value present
                                                                                // i.e does the forward substring after this window (S [window[1] + 1:] has letterIndex present.
                    window[1] = nxt[window[1]+1][letterIndex];
                }
                else {
                    window[0] = window[1] = -1;         // abandon the window from future use and consideration.
                    break;
                }
            }
        }

        int[] ans = {-1, S.length()};   // max window
        for (int[] window: windows) {
            if (window[0] == -1) break;     // should not use abandoned windows
            if (window[1] - window[0] < ans[1] - ans[0]) {      // find smallest one.
                ans = window;
            }
        }

        return ans[0] >= 0 ? S.substring(ans[0], ans[1] + 1) : "";
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest("abcdebdde","bde", "bcde");
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
