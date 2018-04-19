package challenges.hackerrank;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;

/**
 * Hackerland Radio Transmitters
 *
 * https://www.hackerrank.com/challenges/hackerland-radio-transmitters/problem
 *
 * @author hxkandwal
 */
public class HackerlandRadioTransmitters extends AbstractCustomTestRunner {

    static int hackerlandRadioTransmitters(int[] x, int k) {
        Arrays.sort(x);
        int ans = 0, idx = 0;
        while (idx < x.length) {
            ans ++;
            int c = x [idx];
            while (idx + 1 < x.length && x [idx + 1] - c <= k) idx ++;
            c = x [idx];
            while (idx + 1 < x.length && x [idx + 1] - c <= k) idx ++;
            idx ++;
        }
        return ans;
    }

}
