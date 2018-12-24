package challenges.codefights;

import challenges.AbstractCustomTestRunner;

/**
 * Split By Value
 *
 * For an integer k rearrange all the elements of the given array in such way, that:
 *      all elements that are less than k are placed before elements that are not less than k;
 *      all elements that are less than k remain in the same order with respect to each other;
 *      all elements that are not less than k remain in the same order with respect to each other.
 *
 * Example:
 *      For k = 5 and elements = [1, 3, 5, 7, 6, 4, 2], the output should be splitByValue(k, elements) = [1, 3, 4, 2, 5, 7, 6].
 *
 * link: https://app.codesignal.com/tournaments/mDti2jqL5Fq9dsPWJ/D
 *
 * @author Hxkandwal
 */
public class SplitByValue extends AbstractCustomTestRunner {

    int[] _splitByValue(int k, int[] elements) {
        int idx = elements.length - 1, l = idx + 1;
        while (idx >= 0) {
            if (elements [idx] >= k) {
                int val = elements [idx];
                for (int j = idx + 1; j < l; j ++) elements [j - 1] = elements [j];
                elements [-- l] = val;
            }
            idx --;
        }
        return elements;
    }

}
