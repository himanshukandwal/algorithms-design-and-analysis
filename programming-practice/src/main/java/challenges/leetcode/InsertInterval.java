package challenges.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 57. Insert Interval
 * 
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 * 
 * Example 1:
 * 		Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * 
 * Example 2:
 * 		Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * 
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 * 
 * @author Hxkandwal
 */
public class InsertInterval extends AbstractCustomTestRunner {

	static class Interval {
		int start, end;
		Interval() { start = 0; end = 0; }
		Interval(int s, int e) { start = s; end = e; }
	}
	
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        Collections.sort(intervals, (a, b) -> (a.start == b.start ? a.end - b.end : a.start - b.start));
		List<Interval> ans = new ArrayList<>();
		
		int idx = 0;
		while (idx < intervals.size() && intervals.get(idx).end < newInterval.start) ans.add(intervals.get(idx ++));
		if (idx < intervals.size()) {
			int start = Math.min (newInterval.start, intervals.get(idx).start);
			while (idx < intervals.size() && newInterval.end >= intervals.get(idx).start) idx ++;
			int end = idx == 0 ? newInterval.end : Math.max (newInterval.end, intervals.get(idx - 1).end);
			ans.add (new Interval(start, end));
		} else ans.add 	(newInterval);
		while (idx < intervals.size()) ans.add(intervals.get(idx ++));
		
		return ans;
    }
	
}
