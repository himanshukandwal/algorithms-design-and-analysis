package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 683. K Empty Slots
 *
 * There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In each day,
 * there will be exactly one flower blooming and it will be in the status of blooming since then.
 *
 * Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.
 *
 * For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.
 *
 * Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers between
 * them is k and these flowers are not blooming.
 *
 * If there isn't such day, output -1.
 *
 * Example 1:
 *      Input:  flowers: [1,3,2]
 *              k: 1
 *      Output: 2
 *      Explanation: In the second day, the first and the third flower have become blooming.
 *
 * Example 2:
 *      Input:  flowers: [1,2,3]
 *              k: 1
 *      Output: -1
 *
 * Note: The given array will be in the range [1, 20000].
 *
 * @author hxkandwal
 */
public class KEmptySlots extends AbstractCustomTestRunner {

    class Node {
        public int val;
        Node left, right;
        public Node (int val) { this.val = val; }
    }

    public int _kEmptySlots(int[] flowers, int k) {
        Node root = null;

        for (int idx = 0; idx < flowers.length; idx ++) {
            int bloomIdx = flowers [idx];
            if (root == null) root = new Node (bloomIdx);
            else {
                int min = Integer.MIN_VALUE, max = Integer.MAX_VALUE;
                Node n = root, p = null;
                while (n != null) {
                    p = n;
                    if (bloomIdx > n.val) {
                        min = n.val;
                        n = n.right;
                    } else {
                        max = n.val;
                        n = n.left;
                    }
                }

                if (Math.abs (max - bloomIdx) == (k + 1) || Math.abs (min - bloomIdx) == (k + 1)) return idx + 1;
                if (bloomIdx > p.val) p.right = new Node (bloomIdx);
                else p.left = new Node (bloomIdx);
            }
        }
        return -1;
    }

    // Better approach
    public int _kEmptySlotsBetter(int[] flowers, int k) {
        int[] days = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++) {
            days[flowers[i] - 1] = i + 1;
        }

        int ans = Integer.MAX_VALUE;
        int left = 0, right = k+1;

        search: while (right < days.length) {
            for (int i = left+1; i < right; ++i) {
                if (days[i] < days[left] || days[i] < days[right]) {
                    left = i;
                    right = i + k + 1;
                    continue search;
                }
            }
            ans = Math.min(ans, Math.max(days[left], days[right]));
            left = right;
            right = left + k + 1;
        }

        return ans < Integer.MAX_VALUE ? ans : -1;
    }

    // using counting (fenwick tree)
    public class FenwickTree {
        int [] arr;
        int size;

        public FenwickTree(int size) {
            this.size = size + 1;
            this.arr = new int [this.size];
        }

        public boolean isPresent (int pos) {
            return count (pos) - count (pos - 1) != 0;
        }

        public void add (int pos) {
            while (pos < size) {
                arr [pos] += 1;
                pos += (pos & (~pos + 1));
            }
        }

        public int count (int pos) {
            int sum = 0;
            while (pos > 0) {
                sum += arr [pos];
                pos -= (pos & (~pos + 1));
            }
            return sum;
        }

        public int count (int from, int to) {
            return count (to) - count (from - 1);
        }
    }

    public int _kEmptySlotsFenwickTree(int[] flowers, int k) {
        int n = flowers.length;
        FenwickTree ft = new FenwickTree(n);

        for (int idx = 0; idx < n; idx ++) {
            int pos = flowers [idx];
            if (pos - k - 1 > 0 && ft.isPresent(pos - k - 1) && ft.count(pos - k, pos) == 0) return idx + 1;
            if (pos + k + 1 <= n && ft.isPresent(pos + k + 1) && ft.count(pos, pos + k) == 0) return idx + 1;
            ft.add (pos);
        }
        return -1;
    }
}
