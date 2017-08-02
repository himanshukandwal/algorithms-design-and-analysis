package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Stack;

/**
 * 97. Interleaving String
 *
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 * For example,
 * Given:
 *      s1 = "aabcc",
 *      s2 = "dbbca",
 *
 *      When s3 = "aadbbcbcac", return true.
 *      When s3 = "aadbbbaccc", return false.
 *
 * Created by Hxkandwal
 */
public class InterleavingString extends AbstractCustomTestRunner {

    // approach: attempt to enforce the orderless (da, ad) answer at the placeholder.
    public boolean _isInterleave(String s1, String s2, String s3) {
        if (s1.length () + s2.length () != s3.length ()) return false;

        boolean [][] dp = new boolean [s1.length () + 1][s2.length () + 1];
        dp [0][0] = true;
        for (int r = 0; r <= s1.length (); r ++) {
            for (int c = 0; c <= s2.length (); c ++) {
                if (r == 0) dp [r][c] = c == 0 ? true : (dp [r][c - 1] && s2.charAt (c - 1) == s3.charAt (r + c - 1));
                else if (c == 0) dp [r][c] = r == 0 ? true : (dp [r - 1][c] && s1.charAt (r - 1) == s3.charAt (r + c - 1));
                else {
                    dp [r][c] = (dp [r][c - 1] && s2.charAt (c - 1) == s3.charAt (r + c - 1)) || (dp [r - 1][c] && s1.charAt (r - 1) == s3.charAt (r + c - 1));
                }
            }
        }
        return dp [s1.length ()][s2.length ()];
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        Stack<int []> conflictStack = new Stack<>();
        int idx1 = 0, idx2 = 0, idx3 = 0;
        while (idx3 < s3.length ()) {
            char c = s3.charAt (idx3 ++);

            boolean taken = false, found = false;
            if (idx1 < s1.length ()) if (taken = (s1.charAt (idx1) == c)) { found = true; idx1 ++; }
            if (idx2 < s2.length ()) if (s2.charAt (idx2) == c) {
                if (taken) conflictStack.push (new int [] { idx1 - 1, idx2 + 1, idx3 }); else idx2 ++;
                found = true;
            }

            if (!found) {
                if (conflictStack.isEmpty ()) return false;
                else {
                    int [] restoreIdx = conflictStack.pop ();
                    idx1 = restoreIdx [0];
                    idx2 = restoreIdx [1];
                    idx3 = restoreIdx [2];
                }
            }
        }

        return idx1 == s1.length () && idx2 == s2.length () && idx3 == s3.length ();
    }
}
