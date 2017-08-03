package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 600. Non-negative Integers without Consecutive Ones
 *
 * Given a positive integer n, find the number of non-negative integers less than or equal to n, whose binary representations
 * do NOT contain consecutive ones.
 *
 * Example 1:
 *      Input: 5
 *      Output: 5
 *      Explanation: Here are the non-negative integers <= 5 with their corresponding binary representations:
 *
 *                       0 : 0
 *                       1 : 1
 *                       2 : 10
 *                       3 : 11
 *                       4 : 100
 *                       5 : 101
 *
 *                   Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule.
 *
 * Note: 1 <= n <= 109
 *
 * Created by Hxkandwal
 */
public class NonNegativeIntegersWithoutConsecutiveOnes extends AbstractCustomTestRunner {

    private static NonNegativeIntegersWithoutConsecutiveOnes _instance = new NonNegativeIntegersWithoutConsecutiveOnes();

    public int _findIntegers(int num) {
        int count = 1, pow = 0;
        int [] dp = new int [num + 1];
        for (int idx = 1; idx <= num; idx ++) {
            if (idx == (1 << (pow + 1))) {
                pow ++; count ++;
                dp [idx] = pow;
            } else {
                if (dp [idx - (1 << pow)] >= 0 && dp [idx - (1 << pow)] + 1 != pow) {
                    dp [idx] = pow; count ++;
                } else dp [idx] = -1;
            }
        }
        return count;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(6, 5);
    }

    public void runTest(final int num, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { num });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
