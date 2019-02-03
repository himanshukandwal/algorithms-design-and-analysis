package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 970. Powerful Integers
 *
 * Given two non-negative integers x and y, an integer is powerful if it is equal to x^i + y^j for some integers i >= 0 and j >= 0.
 * Return a list of all powerful integers that have value less than or equal to bound.
 * You may return the answer in any order.  In your answer, each value should occur at most once.
 *
 * Example 1:
 *      Input: x = 2, y = 3, bound = 10
 *      Output: [2,3,4,5,7,9,10]
 *      Explanation: 2 = 2^0 + 3^0
 *                   3 = 2^1 + 3^0
 *                   4 = 2^0 + 3^1
 *                   5 = 2^1 + 3^1
 *                   7 = 2^2 + 3^1
 *                   9 = 2^3 + 3^0
 *                   10 = 2^0 + 3^2
 *
 * Example 2:
 *      Input: x = 3, y = 5, bound = 15
 *      Output: [2,4,6,8,10,14]
 *
 * Note:
 *  1 <= x <= 100
 *  1 <= y <= 100
 *  0 <= bound <= 10^6
 *
 * @author Hxkandwal
 */
public class PowerfulIntegers extends AbstractCustomTestRunner {

    // better solution
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < bound; i *= x) {
            for (int j = 1; j <= bound - i; j *= y) {
                set.add (i + j);
                if (y == 1) break;  // one pass of y only needed
            }
            if (x == 1) break;  // one pass of x only needed
        }
        return new ArrayList<>(set);
    }

    public List<Integer> _powerfulIntegers(int x, int y, int bound) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 18 && Math.pow (x, i) <= bound; i ++) {
            int xval = (int) Math.pow (x, i);
            for (int j = 0; j < 18 && Math.pow (y, j) <= bound - xval; j ++)
                set.add ((int) Math.pow (y, j) + xval);
        }
        return new ArrayList<>(set);
    }
}
