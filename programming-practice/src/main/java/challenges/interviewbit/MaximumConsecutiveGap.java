package challenges.interviewbit;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Maximum Consecutive Gap
 *
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * Try to solve it in linear time/space.
 *
 * Example :
 *      Input : [1, 10, 5]
 *      Output : 5
 *      Return 0 if the array contains less than 2 elements.
 *
 * You may assume that all the elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 * You may also assume that the difference will not overflow.
 *
 * @author Hxkandwal
 */
public class MaximumConsecutiveGap extends AbstractCustomTestRunner {

    public int _maximumGap(final List<Integer> A) {
        if (A.size() < 2) return 0;
        int n = A.size(), min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (Integer a : A) { min = Math.min (min, a); max = Math.max (max, a); }

        // max gap should be at-least this big (consecutive numbers)
        // large concentrations which are < minMaxGap, should be ignored and we
        // should start from max of that concentration
        int minMaxGap = (int) Math.ceil((double) (max - min)/(n - 1));
        // min and max buckets to avoid concentration
        int[] minBucket = new int[n - 1];
        int[] maxBucket = new int[n - 1];

        Arrays.fill(minBucket, Integer.MAX_VALUE);
        Arrays.fill(maxBucket, Integer.MIN_VALUE);
        // for any ith index, min and max bucket will store the min and max value it has seen
        // whose diff is not greater than (idx)* minMaxGap, hence stuck in [(idx - 1) * minMaxGap, idx * minMaxGap)
        for (Integer a : A) {
            if (a == min || a == max) continue;
            int index = (a - min) / minMaxGap;
            minBucket [index] = Math.min(minBucket [index], a);
            maxBucket [index] = Math.max(maxBucket [index], a);
        }

        int prevMin = min, ans = 0;
        for (int idx = 0; idx < n - 1; idx ++) {
            if (minBucket[idx] == Integer.MAX_VALUE && maxBucket[idx] == Integer.MIN_VALUE) continue;
            ans = Math.max (ans, minBucket [idx] - prevMin);
            prevMin = maxBucket [idx];
        }
        return Math.max(ans, max - prevMin);
    }

}
