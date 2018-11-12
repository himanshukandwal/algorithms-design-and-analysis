package challenges.assorted;

import java.util.Arrays;

public class SegmentTree {
    public int [] arr;
    public int minIndex, size;

    public SegmentTree(int n) {
        int height = (int) Math.ceil(Math.log(n) / Math.log(2));
        arr = new int [(int) Math.pow(2, height + 1)];
        minIndex = (int) Math.pow(2, height);
        Arrays.fill(arr, Integer.MAX_VALUE);
    }

    public SegmentTree(int[] n) {
        int height = (int) Math.ceil(Math.log(n.length) / Math.log(2));
        arr = new int [(int) Math.pow(2, height + 1)];
        minIndex = (int) Math.pow(2, height);
        Arrays.fill(arr, Integer.MAX_VALUE);

        for (int idx = 0; idx < n.length; idx ++)  arr [minIndex + idx] = n [idx];
        for (int idx = minIndex - 1; idx >= 1; idx --)  arr [idx] = Math.min(arr [2 * idx], arr [2 * idx + 1]);
    }

    public void update(int value, int index) {
        arr [minIndex + index] = value;
        for (int idx = (minIndex + index)>>>1; idx >= 1; idx >>= 1)
            arr [idx] = Math.min (arr [2 * idx], arr [2 * idx + 1]);
    }

    public int min (int l, int r) {
        return l >= r ? 0 : min (l, r, 0, minIndex, 1);
    }

    public int min (int l, int r, int sl, int sr, int idx) {
        if (l <= sl && r >= sr) return arr [idx];
        int ret = Integer.MAX_VALUE;
        if (sr < l || sl > r) return ret;
        int mid = (sl + sr) >>> 1;
        if (l < mid) ret = Math.min(ret, min(l, r, sl, mid, 2 * idx));
        if (r > mid) ret = Math.min(ret, min(l, r, mid, sr, 2 * idx + 1));
        return ret;
    }

    public static void main(String[] args) {
//        SegmentTree st = new SegmentTree(new int [] { 1, 0, -1, -4, 1, 0, -1, 4 });
//        System.out.println(st.min(0, 1));
//        System.out.println(st.min(0, 2));
//        System.out.println(st.min(1, 3));
//
//        System.out.println("--------------------------- ");
//        st = new SegmentTree(new int [] { 1, 0, 2, 4, 5, 0, -1, 18, -100 });
//        System.out.println(st.min(0, 2));
//        System.out.println(st.min(4, 6));
//        System.out.println(st.min(0, 9));

        SegmentTree st = new SegmentTree(new int [] { 1, 0, -1, -4 });
        System.out.println(st.min(0, 1));       // 1
        System.out.println(st.min(0, 2));       // 0
        System.out.println(st.min(1, 4));       // -4

    }
}
