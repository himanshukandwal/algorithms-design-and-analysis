package challenges.codefights;

import challenges.AbstractCustomTestRunner;

/**
 * Are Similar
 *
 * Two arrays are called similar if one can be obtained from another by swapping at most one pair of elements in one of the arrays.
 * Given two arrays a and b, check whether they are similar.
 *
 * Example:
 *  For a = [1, 2, 3] and b = [1, 2, 3], the output should be areSimilar(a, b) = true.
 *  The arrays are equal, no need to swap any elements.
 *
 *  For a = [1, 2, 3] and b = [2, 1, 3], the output should be areSimilar(a, b) = true.
 *  We can obtain b from a by swapping 2 and 1 in b.
 *
 *  For a = [1, 2, 2] and b = [2, 1, 1], the output should be areSimilar(a, b) = false.
 *  Any swap of any two elements either in a or in b won't make a and b equal.
 *
 * link: https://app.codesignal.com/arcade/intro/level-4/xYXfzQmnhBvEKJwXP/description
 *
 * @author Hxkandwal
 */
public class AreSimilar extends AbstractCustomTestRunner {

    boolean areSimilar(int[] a, int[] b) {
        int err = 0, last = -1;
        for (int idx = 0; idx < a.length; idx ++) {
            if (a [idx] != b [idx]) {
                err ++;
                if (last >= 0 && b [idx] == a [last] && b [last] == a [idx] && err == 2) last = -1;
                else last = idx;
            }
        }
        return err <= 2 && last < 0;
    }

}
