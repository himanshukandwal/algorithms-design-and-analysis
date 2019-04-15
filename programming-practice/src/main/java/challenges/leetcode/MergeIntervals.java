package challenges.leetcode;

import java.util.*;

import challenges.AbstractCustomTestRunner;

/**
 * 56. Merge Intervals
 * 
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * For example,
 * 		Given [1,3],[2,6],[8,10],[15,18],
 * 		return [1,6],[8,10],[15,18].
 * 
 * @author Hxkandwal
 */
public class MergeIntervals extends AbstractCustomTestRunner {

	public static class Interval {
		int start, end;

		public Interval() { start = 0; end = 0; }
		public Interval(int s, int e) { start = s; end = e; }
	}
	
	public List<Interval> _merge(List<Interval> intervals) {
        LinkedList<Interval> ans = new LinkedList<>();
        Collections.sort(intervals, (a, b) -> a.end - b.end);
        
        for (Interval i : intervals) {
            if (ans.size() == 0) ans.offer (i);
            else {
                Interval c = i;
                // eat as much as you can
                while (!ans.isEmpty() && c.start <= ans.peekLast().end) { 
                    Interval m = ans.pollLast();
                    m.end = c.end;
                    m.start = (m.start < c.start) ? m.start : c.start;
                    c = m;
                } 
                // full ?? get added now.
                ans.offer (c);
            }
        }
        return ans;
    }

    public List<Interval> _mergeFaster(List<Interval> intervals) {
        List<Interval> ans = new ArrayList<>();
        for (Interval interval : intervals)
            adjustInterval (ans, interval);
        return ans;
    }

    private void adjustInterval(List<Interval> list, Interval i) {
        int l = 0, r = list.size() - 1;
        while (l <= r) {
            int mid = l + (r - l)/2;
            Interval m = list.get (mid);
            if (m.start <= i.start && m.end >= i.end) return;
            if (m.start > i.end) r = mid - 1;
            else if (m.end < i.start) l = mid + 1;
            else {
                int prev = mid, next = mid;
                while (prev - 1 >= 0 && list.get(prev - 1).end >= i.start) prev --;
                while (next + 1 < list.size() && list.get(next + 1).start <= i.end) next ++;
                Interval overlapped = new Interval (
                        Math.min (list.get(prev).start, i.start),
                        Math.max (list.get(next).end, i.end)
                );
                int size = next - prev + 1;
                while (size -- > 0) list.remove (prev);
                list.add (prev, overlapped);
                return;
            }
        }
        if (l == list.size()) list.add (i);
        else list.add(l, i);
    }
	
}