package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 553. Optimal Division
 *
 * Given a list of positive integers, the adjacent integers will perform the float division. For example, [2,3,4] -> 2 / 3 / 4.
 *
 * However, you can add any number of parenthesis at any position to change the priority of operations. You should find out how to add
 * parenthesis to get the maximum result, and return the corresponding expression in string format. Your expression should NOT contain
 * redundant parenthesis.
 *
 * Example:
 *      Input: [1000,100,10,2]
 *
 *      Output: "1000/(100/10/2)"
 *
 *      Explanation:    1000/(100/10/2) = 1000/((100/10)/2) = 200
 *                   However, the bold parenthesis in "1000/((100/10)/2)" are redundant, since they don't influence the operation priority.
 *                   So you should return "1000/(100/10/2)".
 *
 *            Other cases:  1000/(100/10)/2 = 50
 *                          1000/(100/(10/2)) = 50
 *                          1000/100/10/2 = 0.5
 *                          1000/100/(10/2) = 2
 *
 * Note:
 *  -   The length of the input array is [1, 10].
 *  -   Elements in the given array will be in range [2, 1000].
 *  -   There is only one optimal division for each test case.
 *
 * @author Hxkandwal
 */
public class OptimalDivision extends AbstractCustomTestRunner {

    private static OptimalDivision _instance = new OptimalDivision();

    public String _optimalDivision(int[] nums) {
        if (nums.length == 1) return String.valueOf (nums [0]);
        if (nums.length == 2) return String.valueOf (nums [0] + "/" + nums [1]);
        StringBuilder ans = new StringBuilder (nums [0] + "/(" + nums [1]);
        for (int idx = 2; idx < nums.length; idx ++) ans.append ("/" + nums [idx]);
        return ans.append (")").toString();
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int[] { 1000, 100, 10, 2 }, "1000/(100/10/2)");
    }

    public void runTest(final int[] nums, final String expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { nums });

        for (Object answer : answers)
            assertThat((String) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
