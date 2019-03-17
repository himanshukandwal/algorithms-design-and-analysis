package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.stream.IntStream;

/**
 * 307. Range Sum Query - Mutable
 *
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * The update(i, val) function modifies nums by updating the element at index i to val.
 *
 * Example:
 *          Given nums = [1, 3, 5]
 *          sumRange(0, 2) -> 9
 *          update(1, 2)
 *          sumRange(0, 2) -> 8
 *
 * Note:
 *  The array is only modifiable by the update function.
 *  You may assume the number of calls to update and sumRange function is distributed evenly.
 *
 * @author Hxkandwal
 */
public class RangeSumQueryMutable extends AbstractCustomTestRunner {

    // Using BIT
    class NumArrayBIT {

        class BIT {
            private int size;
            private int[] arr;

            public BIT (int size) {
                this.size = size + 1;
                this.arr = new int [this.size];
            }

            public void add (int idx, int val) {
                idx ++;
                while (idx < size) {
                    arr [idx] += val;
                    idx += idx & (~idx + 1);
                }
            }

            public int sum (int idx) {
                idx ++;
                int ans = 0;
                while (idx > 0) {
                    ans += arr [idx];
                    idx -= idx & (~idx + 1);
                }
                return ans;
            }

            public int range (int i, int j) {
                return sum (j) - (i == 0 ? 0 : sum (i - 1));
            }
        }

        private BIT bit;
        private int[] nums;

        public NumArray(int[] nums) {
            this.bit = new BIT (nums.length);
            this.nums = nums;
            for (int idx = 0; idx < nums.length; idx ++) bit.add (idx, nums [idx]);
        }

        public void update(int i, int val) {
            bit.add (i, -nums [i]);
            bit.add (i, nums [i] = val);
        }

        public int sumRange(int i, int j) {
            return bit.range (i, j);
        }
    }

    // Using Square-root decomposition.
    class NumArraySqrt {

        private int len;
        private int [] b;
        private int [] nums;

        public NumArraySqrt(int[] nums) {
            this.nums = nums;
            double sqrt = Math.sqrt (nums.length);
            this.len = (int) Math.ceil (nums.length / sqrt);
            this.b = new int [len];

            for (int idx = 0; idx < nums.length; idx ++) b [idx / len] += nums [idx];
        }

        public void update(int i, int val) {
            b [i / len] += (-nums [i] + val);
            nums [i] = val;
        }

        public int sumRange(int i, int j) {
            int start = i / len, end = j / len;
            if (start == end)
                return IntStream.rangeClosed(i, j).map (idx -> nums [idx]).sum();
            int sum = 0;
            for (int k = i; k <= (start + 1) * len - 1; k ++) sum += nums [k];
            for (int k = start + 1; k <= end - 1; k ++) sum += b [k];
            for (int k = end * len ; k <= j; k ++) sum += nums [k];
            return sum;
        }
    }

    // Using Segment tree.
    class NumArraySegmentTree {

        private int [] tree;
        private int len;

        public NumArraySegmentTree(int[] nums) {
            this.len = nums.length;
            tree = new int [2 * len];
            for (int idx = 0, j = len; idx < len; idx ++, j ++) tree [j]= nums [idx];

            for (int idx = len - 1; idx > 0; idx --)
                tree [idx] = tree [2 * idx] + tree [2 * idx + 1];
        }

        public void update(int i, int val) {
            i += len;
            tree [i] = val;
            i /= 2;
            while (i > 0) {
                tree [i] = tree [2 * i] + tree [2 * i + 1];
                i /= 2;
            }
        }

        public int sumRange(int i, int j) {
            i += len;
            j += len;
            int sum = 0;
            while (i <= j) {
                if (i % 2 == 1) {
                    sum += tree [i];
                    i ++;
                }
                if (j % 2 == 0) {
                    sum += tree [j];
                    j --;
                }
                i /= 2;
                j /= 2;
            }
            return sum;
        }
    }
}
