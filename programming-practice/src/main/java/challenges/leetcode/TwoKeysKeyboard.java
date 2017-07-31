package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 650. 2 Keys Keyboard
 *
 * Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:
 *
 * 1. Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
 * 2. Paste: You can paste the characters which are copied last time.
 *
 * Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted.
 * Output the minimum number of steps to get n 'A'.
 *
 * Example 1:
 *      Input: 3
 *      Output: 3
 *
 *      Explanation:
 *              Intitally, we have one character 'A'.
 *              In step 1, we use Copy All operation.
 *              In step 2, we use Paste operation to get 'AA'.
 *              In step 3, we use Paste operation to get 'AAA'.
 *
 * Note: The n will be in the range [1, 1000].
 *
 * Created by Hxkandwal
 */
public class TwoKeysKeyboard extends AbstractCustomTestRunner {

    private static TwoKeysKeyboard _instance = new TwoKeysKeyboard();

    public int _minSteps(int n) {
        if (n <= 1) return 0;
        int [][] dp = new int [n + 1][2];
        dp [2][0] = 2; dp [2][1] = 1;
        for (int idx = 3; idx <= n; idx ++) {
            dp [idx][0] = idx; dp [idx][1] = 1;
            for (int j = idx - 1; j >= 2; j --) {
                if ((idx - j) % dp [j][1] == 0)
                    if (dp [j][0] + (idx - j)/ dp [j][1] < dp [idx][0]) {
                        dp[idx][0] = dp [j][0] + dp [j][0] + (idx - j)/ dp [j][1];
                        dp[idx][1] = dp [j][1];
                    }

                if ((idx - j) % j == 0)
                    if (dp [j][0] + 1 + (idx - j)/j < dp [idx][0]) {
                        dp[idx][0] = dp [j][0] + 1 + (idx - j)/j;
                        dp[idx][1] = j;
                    }
            }
        }
        return dp [n][0];
    }

    // another approach
    public int minSteps(int n) {
        int [] dp = new int [n + 1];
        for (int i = 2; i <= n; i ++) {
            dp [i] = i;
            for (int j = i - 1; j > 1; j --) {
                if (i % j == 0) { dp [i] = dp [j] + (i / j); break; }
            }
        }
        return dp[n];
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(9, 6);
    }

    public void runTest(final int n, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { n });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}

