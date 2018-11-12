package challenges.assorted;

import java.util.Arrays;

public class SegmentTree {
    private int[] arr;
    private int size;
    private int minsLastIndex;

    public SegmentTree(int[] nums) {
        int height = (int) Math.ceil (Math.log (nums.length) / Math.log (2));
        this.size = (int) Math.pow (2, height + 1);      // Technically size should be Math.pow(2, height) - 1,
                                                         // if we count total nodes, geometric series sum, but (2*i, 2*i + 1) pattern works for index based 1.
                                                         // hence, size is just (int) Math.pow(2, height), one more than what it should be.
        this.minsLastIndex = (int) Math.pow (2, height); // 1 index based
        this.arr = new int [size];

        for (int idx = 0; idx < nums.length; idx ++) arr [minsLastIndex + idx] = nums [idx];
        for (int idx =  minsLastIndex - 1; idx >= 1; idx --) arr [idx] = Math.min (arr [2 * idx], arr [2 * idx + 1]);
    }

    public void update(int index, int value) {
        arr [minsLastIndex + index] = value;
        for (int idx = (minsLastIndex + index) / 2; idx >= 1; idx /= 2) arr [idx] = Math.min (arr [2 * idx], arr [2 * idx + 1]);
    }

    public int min(int left, int right) {
        return left >= right ? 0 : min(left, right, 0, minsLastIndex, 1);   // 0 is the root and center.
    }

    public int min(int left, int right, int spanl, int spanr, int index) {
        // complete overlap
        if (left <= spanl && right >= spanr) return arr [index];
        else {
            int ret = Integer.MAX_VALUE;
            int mid = (spanl + spanr) >>> 1;
            if (mid > left && spanl < right) ret = Math.min(ret, min(left, right, spanl, mid, 2 * index));      // spanl < right is for no - overlapping case.
            if (mid < right && spanr > left) ret = Math.min(ret, min(left, right, mid, spanr, 2 * index + 1));  // spanr > left is for no - overlapping case.
            return ret;
        }
    }

    public static void main(String[] args) {
        SegmentTree st = new SegmentTree(new int [] { 1, 0, -1, -4, 1, 0, -1, 4 });
        System.out.println(st.min(0, 1));
        System.out.println(st.min(0, 2));
        System.out.println(st.min(1, 3));

        System.out.println("--------------------------- ");
        st = new SegmentTree(new int [] { 1, 0, 2, 4, 5, 0, -1, 18, -100 });
        System.out.println(st.min(0, 2));
        System.out.println(st.min(4, 6));
        System.out.println(st.min(0, 9));
    }
}
