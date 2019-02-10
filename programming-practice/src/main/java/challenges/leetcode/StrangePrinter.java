package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 664. Strange Printer
 *
 * There is a strange printer with the following two special requirements:
 *  The printer can only print a sequence of the same character each time.
 *  At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.
 *
 * Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.
 *
 * Example 1:
 *      Input: "aaabbb"
 *      Output: 2
 *      Explanation: Print "aaa" first and then print "bbb".
 *
 * Example 2:
 *      Input: "aba"
 *      Output: 2
 *      Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
 *
 * Hint: Length of the given string will not exceed 100.
 *
 * @author Hxkandwal
 */
public class StrangePrinter extends AbstractCustomTestRunner {

    // different bottom up technique. Its len driven rather than range drive, (i, j) -> (i, k) + (k, j).
    // its like: solve len = 1 solutions, len = 2 solutions .... to len = n solutions.
    public int _strangePrinter(String str) {
        if (str == null || str.length () == 0) return 0;
        int n = str.length();
        int [][] dp = new int [n][n];
        for (int i = 0; i < n; i ++) dp [i][i] = 1;

        for (int l = 1; l < n; l ++) {
            for (int s = 0; s < n - l; s ++) {
                dp [s][s + l] = l + 1;
                for (int k = 0; k < l; k ++) {
                    dp [s][s + l] = Math.min (dp [s][s + l],
                            dp [s][s + k] + dp [s + k + 1][s + l]
                                    + (str.charAt(s + k) == str.charAt(s + l) ? -1 : 0)
                    );
                }
            }
        }
        return dp [0][n - 1];
    }
}
