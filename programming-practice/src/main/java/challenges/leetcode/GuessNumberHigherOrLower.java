package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 374. Guess Number Higher or Lower
 *
 * We are playing the Guess Game. The game is as follows:
 *
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 *
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 *
 *  -1 : My number is lower
 *   1 : My number is higher
 *   0 : Congrats! You got it!
 *
 * Example: n = 10, I pick 6.
 *          Return 6.
 *
 * @author hxkandwal
 */
public class GuessNumberHigherOrLower extends AbstractCustomTestRunner {

    public int _guessNumber(int n) {
        int l = 1, r = n;
        while (l <= r) {
            int m = l + (r - l)/2;
            int val = guess (m);

            if (val < 0) r = m - 1;
            else if (val > 0) l = m + 1;
            else return m;
        }
        return -1;
    }

    private int guess(int num) { return 0; }
}
