package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Random;

/**
 * 528. Random Pick with Weight
 *
 * Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.
 *
 * Note:
 *      1 <= w.length <= 10000
 *      1 <= w[i] <= 10^5
 *      pickIndex will be called at most 10000 times.
 *
 * Example 1:
 *      Input:  ["Solution","pickIndex"]
 *              [[[1]],[]]
 *      Output: [null,0]
 *
 * Example 2:
 *      Input:  ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 *              [[[1,3]],[],[],[],[],[]]
 *      Output: [null,0,1,1,1,0]
 *
 * Explanation of Input Syntax:
 *
 * The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.
 *
 * @author Hxkandwal
 */
public class RandomPickWithWeight extends AbstractCustomTestRunner {

    private int[] pre;
    private Random rand = new Random();

    public RandomPickWithWeight(int[] w) {
        this.pre = w.clone();
        for (int idx = 1; idx < pre.length; idx ++)
            pre [idx] += pre [idx - 1];
    }

    public int pickIndex() {
        int val = rand.nextInt(pre [pre.length - 1]) + 1;
        int l = 0, r = pre.length - 1;
        while (l < r) {
            int m = l + (r - l)/2;
            if (pre [m] < val) l = m + 1;
            else r = m;
        }
        return r;
    }
}
