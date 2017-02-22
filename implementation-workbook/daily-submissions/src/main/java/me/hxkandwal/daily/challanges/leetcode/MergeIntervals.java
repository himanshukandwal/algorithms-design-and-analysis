package me.hxkandwal.daily.challanges.leetcode;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
		int start;
		int end;

		public Interval() { start = 0; end = 0; }
		public Interval(int s, int e) { start = s; end = e; }
		public String toString() { return "(" + start + ":" + end + ")"; }
	}
	
	public List<Interval> merge(List<Interval> intervals) {
        LinkedList<Interval> ans = new LinkedList<>();
        Collections.sort(intervals, new Comparator<Interval> () {
           public int compare (Interval i1, Interval i2) {
               return i1.end - i2.end;
           } 
        });
        
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
}
