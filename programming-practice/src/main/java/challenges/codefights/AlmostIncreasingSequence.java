package challenges.codefights;

import challenges.AbstractCustomTestRunner;

/**
 * Almost Increasing Sequence
 *
 * Given a sequence of integers as an array, determine whether it is possible to obtain a strictly increasing sequence by removing no more than one element from the array.
 *
 * Example:
 *  For sequence = [1, 3, 2, 1], the output should be almostIncreasingSequence(sequence) = false.
 *  There is no one element in this array that can be removed in order to get a strictly increasing sequence.
 *
 *  For sequence = [1, 3, 2], the output should be almostIncreasingSequence(sequence) = true.
 *  You can remove 3 from the array to get the strictly increasing sequence [1, 2]. Alternately, you can remove 2 to get the strictly increasing sequence [1, 3].
 *
 * link: https://app.codesignal.com/arcade/intro/level-2/2mxbGwLzvkTCKAJMG/description
 *
 * @author Hxkandwal
 */
public class AlmostIncreasingSequence extends AbstractCustomTestRunner {

    /* problem of pairs:
                        x
        { 1, 2, 3, [5, 3], 6 }
                    x
        { 1, 2, 3, [6, 5], 6 }
     */
    boolean almostIncreasingSequence(int[] sequence) {
        int f = -1, s = -1;
        for (int idx = 1; idx < sequence.length; idx ++) {
            if (s >= 0 && idx == s + 1) {
                if (sequence [f] >= sequence [idx] && sequence [s] >= sequence [idx]) return false;
            }
            else if (sequence [idx - 1] >= sequence [idx]) {
                if (f > 0 || (idx - 2 >= 0 && sequence [idx] <= sequence [idx - 2])) return false;
                f = idx - 1;
                s = idx;
            }
        }
        return true;
    }

    boolean almostIncreasingSequenceOther(int[] sequence) {
        int err = 0;
        for (int idx = 1; idx < sequence.length; idx ++) {
            if (sequence [idx - 1] >= sequence [idx]) {
                err ++;
                if (idx - 2 >= 0 && idx + 1 < sequence.length &&
                        sequence [idx - 2] >= sequence [idx] &&
                        sequence [idx - 1] >= sequence [idx + 1]) return false;      // no jump possible (localized quad array, case ignore idx -1 or ignore idx)
            }
        }
        return err <= 1;
    }

}
