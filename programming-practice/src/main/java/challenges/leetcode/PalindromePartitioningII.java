package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 132. Palindrome Partitioning II
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 * Example:
 *          Input: "aab"
 *          Output: 1
 *          Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 *
 * @author Hxkandwal
 */
public class PalindromePartitioningII extends AbstractCustomTestRunner {

    private static PalindromePartitioningII _instance = new PalindromePartitioningII();

    public int _minCut(String s) {
        int n = s.length();
        int [][] dp = new int [n][n];
        boolean[][] p = new boolean [n][n];

        for (int idx = 0; idx < n; idx ++) {
            Arrays.fill(dp [idx], Integer.MAX_VALUE);
            p [idx][idx] = true;
            dp [idx][idx] = 0;
        }

        for (int len = 1; len < n; len ++) {

            for (int idx = 0; idx + len < n; idx ++) {
                int j = idx + len;
                for (int k = idx + 1; k <= j; k ++) {
                    System.out.println(" -- idx : " + idx + " , k :" + k + " j: " + j);
                    if (s.charAt(idx) == s.charAt(k) &&
                            (k == idx + 1 || p [idx + 1][k - 1]) &&
                            (k + 1 == j || p [k + 1][j])
                    ) {
                        dp[idx][j] = Math.min(dp[idx][k], dp[k + 1][j]) + 1;
                    }
                }
            }
        }

        Arrays.stream(dp).map(Arrays::toString).forEach(System.out::println);
        return dp [0][n - 1];
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest("bb", 0);
    }

    public void runTest(final String s, final Integer expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { s });

        for (Object answer : answers)
            assertThat((String) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
