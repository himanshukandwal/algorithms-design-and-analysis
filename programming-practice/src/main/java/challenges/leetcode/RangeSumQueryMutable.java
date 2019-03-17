package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

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

    public RangeSumQueryMutable(int[] nums) {
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
