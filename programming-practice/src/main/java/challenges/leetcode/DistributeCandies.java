package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import static com.google.common.truth.Truth.assertThat;

/**
 * 575. Distribute Candies
 *
 * Given an integer array with even length, where different numbers in this array represent different kinds of candies. Each number means one candy of the
 * corresponding kind. You need to distribute these candies equally in number to brother and sister. Return the maximum number of kinds of candies the sister could gain.
 *
 * Example 1:
 *      Input: candies = [1,1,2,2,3,3]
 *      Output: 3
 *      Explanation:
 *          There are three different kinds of candies (1, 2 and 3), and two candies for each kind.
 *          Optimal distribution: The sister has candies [1,2,3] and the brother has candies [1,2,3], too.
 *          The sister has three different kinds of candies.
 *
 * Example 2:
 *      Input: candies = [1,1,2,3]
 *      Output: 2
 *      Explanation:
 *          For example, the sister has candies [2,3] and the brother has candies [1,1].
 *          The sister has two different kinds of candies, the brother has only one kind of candies.
 *
 * Note:
 *  -   The length of the given array is in range [2, 10,000], and will be even.
 *  -   The number in given array is in range [-100,000, 100,000].
 *
 * @author Hxkandwal
 */
public class DistributeCandies extends AbstractCustomTestRunner {

    private static DistributeCandies _instance = new DistributeCandies();

    public int _distributeCandies(int[] candies) {
        int b = 0, s = 0, ans = 0;
        for (int idx = 0, lsum = 1; idx < candies.length; idx ++) {
            if (idx + 1 < candies.length && candies [idx] == candies [idx + 1]) lsum ++;
            else {
                int share = lsum / 2;
                if (b >= s) {
                    s += (lsum - share);
                    b += share;
                    ans ++;
                } else {
                    s += share;
                    b += (lsum - share);
                    if (share > 0) ans ++;
                }

                lsum = 1;
            }
        }
        return ans;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int [] { 1, 1, 2, 3 }, 2);
        _instance.runTest(new int [] { 0, 0, 0, 4 }, 2);
        _instance.runTest(new int [] { 1, 1, 1, 1, 2, 2, 2, 3, 3, 3 }, 3);
    }

    public void runTest(final int[] candies, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { candies });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
