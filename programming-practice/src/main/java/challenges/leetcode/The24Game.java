package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 679. 24 Game
 *
 * You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.
 *
 * Example 1:
 *          Input: [4, 1, 8, 7]
 *          Output: True
 *          Explanation: (8-4) * (7-1) = 24
 *
 * Example 2:
 *          Input: [1, 2, 1, 2]
 *          Output: False
 *
 * Note:
 *  The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
 *  Every operation done is between two numbers. In particular, we cannot use - as a unary operator.
 *  For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
 *  You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
 *
 * @author Hxkandwal
 */
public class The24Game extends AbstractCustomTestRunner {

    private static The24Game _instance = new The24Game();

    public boolean _judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int num : nums) list.add (num * 1d);
        return dfs (list);
    }

    public boolean dfs (List<Double> nums) {
        if (nums.size() == 0) return false;
        if (nums.size() == 1) return Math.abs(nums.get(0) - 24) < 0.00000001;

        for (int i = 0; i < nums.size(); i ++) {
            for (int j = 0; j < nums.size(); j ++) {
                if (i == j) continue;

                List<Double> next = new ArrayList<>();
                for (int k = 0; k < nums.size(); k ++) if (k != i && k != j)
                    next.add (nums.get(k));

                double a = nums.get (i), b = nums.get (j);
                for (Double op : operations(a, b)) {
                    next.add (op);
                    if (dfs (next)) return true;
                    next.remove(op);
                }
            }
        }
        return false;
    }

    private List<Double> operations(double a, double b) {
        List<Double> ops = new ArrayList<>();
        ops.add (a + b);
        ops.add (a - b);
        ops.add (a * b);
        if (b != 0) ops.add (a / b);
        return ops;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int [] { 4, 1, 8, 7 }, true);
        _instance.runTest(new int [] { 1, 2, 1, 2 }, false);
    }

    public void runTest(final int [] nums, final boolean expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { nums });

        for (Object answer : answers)
            assertThat((Boolean) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
