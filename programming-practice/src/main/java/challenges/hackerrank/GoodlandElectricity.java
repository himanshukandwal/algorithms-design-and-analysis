package challenges.hackerrank;

import challenges.AbstractCustomTestRunner;

/**
 * Goodland Electricity
 *
 * https://www.hackerrank.com/challenges/pylons/problem
 *
 * @author hxkandwal
 */
public class GoodlandElectricity extends AbstractCustomTestRunner {

    public int pylonsBetter(int k, int[] arr) {
        int count = 0;
        for (int idx = 0; idx < arr.length;) {
            int last = -1;
            for (int j = Math.max (0, idx - k + 1); j < idx + k && j < arr.length; j ++) if (arr [j] == 1) last = j;

            if (last == -1) return -1;
            count ++;
            idx = last + k;
        }
        return count;
    }

    public int pylons(int k, int[] arr) {
        int [] pre = new int [arr.length];
        for (int idx = 0, last = -1; idx < arr.length; idx ++) {
            if (arr [idx] == 1) last = idx;
            pre [idx] = last;
        }

        int count = 0;
        for (int idx = 0; idx < arr.length;) {
            int t = pre [Math.min (idx + k - 1, arr.length - 1)];
            if (t < 0 || t <= idx - k) return -1;
            count ++;
            idx = t + k;
        }
        return count;
    }
}
