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

        public NumArrayBIT(int[] nums) {
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

        int [] arr;
        int [] nums;
        int size;

        public NumArraySqrt(int[] nums) {
            int len = nums.length;
            this.size = (int) Math.ceil(Math.sqrt(len));

            this.nums = nums;
            this.arr = new int [size];

            for (int idx = 0; idx < len; idx ++) {
                arr [idx/size] += nums [idx];
            }
        }

        public void update(int i, int val) {
            arr [i / size] += (-nums [i] + val);
            nums [i] = val;
        }

        public int sumRange(int i, int j) {
            int l = i / size, r = j / size;
            if (l == r)
                return IntStream.rangeClosed(i, j).map (idx -> nums [idx]).sum();

            // Start-points of the blocks:  0 * size, 1 * size, 2 * size, ......, i * size.
            // As l is in middle of a block so, to manually computed the sum it should be less than starting of next block. i.e. (l + 1) * size
            //          [..., l * size --- l ---- , (l + 1) * size ---- ...]
            // same for r, the beginning of block, where r is in the middle, starts from r * size. so we should manually compute sum from  r * size - to - r
            //          [..., r * size --- r ---- , (r + 1) * size ---- ...]
            int ans = 0;
            for (int idx = i; idx < (l + 1) * size; idx ++) ans += nums [idx];
            for (int idx = l + 1; idx < r; idx ++) ans += arr [idx];
            for (int idx = r * size; idx <= j; idx ++) ans += nums [idx];
            return ans;
        }
    }

    // Using Segment tree.
    public class SegmentTree {
        int [] arr;
        int size, len;

        public SegmentTree(int[] nums) {
            this.len = nums.length;
            this.size = 2 * len;
            this.arr = new int [size];

            for (int idx = len; idx < size; idx ++) arr [idx] = nums [idx - len];

            for (int idx = len - 1; idx > 0; idx --)
                arr [idx] = arr [2 * idx] +  arr [2 * idx + 1];
        }

        public void update (int pos, int val) {
            pos += len;
            arr [pos] = val;

            pos /= 2;
            while (pos > 0) {
                arr [pos] = arr [2 * pos] + arr [2 * pos + 1];
                pos /= 2;
            }
        }

        public int sum (int from, int to) {
            from += len;
            to += len;
            int ans = 0;
            while (from <= to) {
                if (from % 2 != 0) ans += arr [from ++];
                if (to % 2 == 0) ans += arr [to --];
                from /= 2;
                to /= 2;
            }
            return ans;
        }
    }
}
