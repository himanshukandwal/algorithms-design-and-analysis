package challenges.codefights;

import challenges.AbstractCustomTestRunner;

/**
 * Construct Array
 *
 * Given an integer size, return an array containing each integer from 1 to size in the following order:
 *
 *      1, size, 2, size - 1, 3, size - 2, 4, ...
 *
 * Example:
 *      For size = 7, the output should be  constructArray(size) = [1, 7, 2, 6, 3, 5, 4].
 *
 * link: https://app.codesignal.com/skill-test/9h4hjB3u4F7FNvBPL
 *
 * @author Hxkandwal
 */
public class ConstructArray extends AbstractCustomTestRunner {

    public int[] constructArray(int size) {
        int [] ans = new int [size];
        int idx = 0, count = 1;
        boolean toggle = true;
        while (idx < ans.length) {
            ans [idx ++] = toggle ? count : size + 1 - count++;
            toggle = !toggle;
        }
        return ans;
    }

}
