package challenges.codefights;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;

/**
 * Array Mode
 *
 * Given array of integers, find its mode.
 *
 * Example:
 *      For sequence = [1, 3, 3, 3, 1], the output should be arrayMode(sequence) = 3;
 *      For sequence = [1, 3, 2, 1], the output should be arrayMode(sequence) = 1
 *
 * link: https://app.codesignal.com/challenge/J6DuW9RqENKaJjkGS
 *
 * @author Hxkandwal
 */
public class ArrayMode extends AbstractCustomTestRunner {

    public int _arrayMode(int[] sequence) {
        Arrays.sort (sequence);
        int bestCount = 1, bestVal = sequence [0];
        for (int idx = 1, count = bestCount, val = bestVal; idx < sequence.length; idx ++) {
            if (sequence [idx] != val) {
                count = 1;  val = sequence [idx];
            } else count ++;
            if (count > bestCount) { bestCount = count; bestVal = val; }
        }
        return bestVal;
    }

}
