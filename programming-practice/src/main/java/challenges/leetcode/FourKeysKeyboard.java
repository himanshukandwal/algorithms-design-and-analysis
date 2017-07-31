package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 651. 4 Keys Keyboard
 *
 * Imagine you have a special keyboard with the following keys:
 *
 *      Key 1: (A): Prints one 'A' on screen.
 *      Key 2: (Ctrl-A): Select the whole screen.
 *      Key 3: (Ctrl-C): Copy selection to buffer.
 *      Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.
 *
 * Now, you can only press the keyboard for N times (with the above four keys), find out the maximum numbers of 'A' you can print on screen.
 *
 * Example 1:
 *      Input: N = 3
 *      Output: 3
 *      Explanation: We can at most get 3 A's on screen by pressing following key sequence:
 *                   A, A, A
 *
 * Example 2:
 *      Input: N = 7
 *      Output: 9
 *      Explanation: We can at most get 9 A's on screen by pressing following key sequence:
 *                   A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
 *
 * Note:
 *  -   1 <= N <= 50
 *  -   Answers will be in the range of 32-bit signed integer.
 *
 * Created by Hxkandwal
 */
public class FourKeysKeyboard extends AbstractCustomTestRunner {

    private static FourKeysKeyboard _instance = new FourKeysKeyboard();

    public int _maxA(int N) {
        if (N <= 1) return N;
        int [][] dp = new int [N + 1][2];           // [max characters][copy buffer size]
        for (int idx = 1; idx <= N; idx ++) {
            dp [idx][0] = idx;
            for (int j = Math.max (dp [idx - 1][1], 1); j < idx; j ++) {
                // case: 1 (extend paste from here onwards)
                if (dp[j][0] + (idx - j) * dp[j][1] >= dp[idx][0]) {
                    dp[idx][0] = dp[j][0] + (idx - j) * dp[j][1];
                    dp[idx][1] = Math.max(dp[idx][1], dp[j][1]);
                }

                // case: 2 (copy paste from here onwards)
                if (dp [j][0] * (idx - 2 - j + 1) >= dp [idx][0]) {
                    dp [idx][0] = dp [j][0] * (idx - 2 - j + 1);
                    dp [idx][1] = Math.max (dp [idx][1], j);
                }
            }
        }
        return dp [N][0];
    }

    // nice approach.
    public int _maxAOneDimentional(int N) {
        int [] DP = new int [N + 1];
        if (N < 3) return N;
        DP [1] = 1;
        DP [2] = 2;
        for (int i = 3; i <= N; i ++) {
            int addA = DP [i - 1] + 1;
            int paste = 0;
            for (int j = 1; j < i - 2; j ++)                // interestingly handled the 2 steps by limiting the search space with idx - 2.
                paste = Math.max (paste, DP[j] * (i - j - 1));

            DP [i] = Math.max(addA, paste);
        }
        return DP [N];
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(7, 9);
        _instance.runTest(11, 27);
        _instance.runTest(12, 36);
        _instance.runTest(13, 48);
    }

    public void runTest(final int n, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { n });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
