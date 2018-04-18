package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * 633. Sum of Square Numbers
 *
 * Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.
 *
 * Example 1:
 *      Input: 5
 *      Output: True
 *      Explanation: 1 * 1 + 2 * 2 = 5
 *
 * Example 2:
 *      Input: 3
 *      Output: False
 *
 * @author hxkandwal
 */
public class SumOfSquareNumbers extends AbstractCustomTestRunner {

    public boolean judgeSquareSum(int c) {
        if (c == 0) return true;
        for (long y = 0; y * y < c; y ++) {
            int x = c - (int) (y * y), l = 1, r = x;
            while (l <= r) {
                long m = (l + r) >>> 1;
                if (m * m > x) r = (int) m - 1;
                else if (m * m < x) l = (int) m + 1;
                else return true;
            }
        }
        return false;
    }

    public boolean judgeSquareSumOther(int c) {
        Set<Integer> set = new HashSet<Integer>();

        for (int i = 0; i <= Math.sqrt(c); i++) {
            set.add(i * i);
            if (set.contains(c - i * i))  return true;
        }
        return false;
    }

    public boolean judgeSquareSumTwoPointer(int c) {
        int n1 = 0, n2 = (int)Math.sqrt(c);
        while (n1 <= n2) {
            int cur = n1 * n1 + n2 * n2;
            if (cur == c) return true;
            if (cur < c) n1++;
            else n2--;
        }
        return false;
    }
}
