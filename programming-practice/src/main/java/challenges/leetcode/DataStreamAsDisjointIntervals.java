package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 352. Data Stream as Disjoint Intervals
 *
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.
 *
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
 *
 *      [1, 1]
 *      [1, 1], [3, 3]
 *      [1, 1], [3, 3], [7, 7]
 *      [1, 3], [7, 7]
 *      [1, 3], [6, 7]
 *
 * Follow up:
 *  What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
 *
 * @author Hxkandwak
 */
public class DataStreamAsDisjointIntervals extends AbstractCustomTestRunner {

    public class Interval {
        int start, end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    List<Interval> list;

    /** Initialize your data structure here. */
    public DataStreamAsDisjointIntervals() {
        this.list = new ArrayList<>();
    }

    public void addNum(int val) {
        int idx = findPosition(val);
        if (idx < 0) return;

        Interval curr = null;
        if (idx > 0) {
            Interval left = list.get (idx - 1);
            if (left.end + 1 == val) {
                left.end = val;
                curr = left;
            }
        }

        if (idx < list.size()) {
            Interval right = list.get (idx);
            if (right.start - 1 == val) {
                right.start = val;
                curr = right;
            }
        }

        if (curr == null) list.add (idx, new Interval(val, val));
        else {
            Interval left = (idx > 0) ? list.get (idx - 1) : null;
            Interval right = (idx < list.size()) ? list.get (idx) : null;
            if (left != null && right != null && left.end == right.start) {
                left.end = right.end;
                list.remove (right);
            }
        }
    }

    public List<Interval> getIntervals() {
        return list;
    }

    private int findPosition(int val) {
        int l = 0, r = list.size() - 1;
        while (l <= r) {
            int m = l + (r - l)/2;
            Interval mInterval = list.get (m);

            if (mInterval.start <= val && mInterval.end >= val) return -1;
            if (mInterval.end < val) l = m + 1;
            else r = m - 1;
        }
        return l;
    }
}
