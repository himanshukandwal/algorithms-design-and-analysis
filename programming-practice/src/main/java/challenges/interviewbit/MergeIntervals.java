package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Merge Intervals
 * 
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * 
 * You may assume that the intervals were initially sorted according to their start times.
 * 
 * Example 1:
 * 		Given intervals [1,3],[6,9] insert and merge [2,5] would 
 * 		result in [1,5],[6,9].
 * 
 * Example 2:
 * 		Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] would 
 * 		result in [1,2],[3,10],[12,16].
 * 
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 * Make sure the returned intervals are also sorted.
 * 
 * @author Hxkandwal
 */
public class MergeIntervals extends AbstractCustomTestRunner {
	
	private static MergeIntervals _instance = new MergeIntervals();

	static class Interval implements Comparable<Interval> {
		int start, end;
		Interval() { start = 0; end = 0; }
		Interval(int s, int e) { start = s; end = e; }
		
		@Override
		public int compareTo(Interval o) {
			return (start - o.start == 0 ? end - o.end : start - o.start);
		}
		
		@Override
		public boolean equals(Object obj) {
			Interval interval = (Interval) obj;
			return start == interval.start && end == interval.end;
		}
		
		@Override
		public String toString() {
			return "(" + start + ","  + end + ")";
		}
	}
	
	public List<Interval> _insert(List<Interval> intervals, Interval newInterval) {
		Collections.sort(intervals, (a, b) -> (a.start == b.start ? a.end - b.end : a.start - b.start));
		List<Interval> ans = new ArrayList<>();
		
		int idx = 0;
		while (idx < intervals.size() && intervals.get(idx).end < newInterval.start) ans.add(intervals.get(idx ++));
		if (idx < intervals.size()) {
			int start = Math.min (newInterval.start, intervals.get(idx).start);
			while (idx < intervals.size() && newInterval.end > intervals.get(idx).start) idx ++;
			int end = idx == 0 ? newInterval.end : Math.max (newInterval.end, intervals.get(idx - 1).end);
			ans.add (new Interval(start, end));
		} else ans.add 	(newInterval);
		while (idx < intervals.size()) ans.add(intervals.get(idx ++));
		
		return ans;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(Arrays.asList(new Interval(3, 6), new Interval(8, 10)), new Interval(1, 2), 
				  Arrays.asList(new Interval(1, 2), new Interval(3, 6), new Interval(8, 10)));
		_instance.runTest(Arrays.asList(new Interval(1, 2), new Interval(3, 6)), new Interval(8, 10), 
						  Arrays.asList(new Interval(1, 2), new Interval(3, 6), new Interval(8, 10)));
		_instance.runTest(Arrays.asList(new Interval(1, 3), new Interval(6, 9)), new Interval(2, 5), Arrays.asList(new Interval(1, 5), new Interval(6, 9)));
		_instance.runTest(Arrays.asList(new Interval(1, 2), new Interval(3, 5), new Interval(6, 7), new Interval(8, 10),
										new Interval(12, 16)), new Interval(4, 9),
				          Arrays.asList(new Interval(1, 2), new Interval(3, 10), new Interval(12, 16)));
	}

	@SuppressWarnings("unchecked")
	public void runTest(final List<Interval> intervals, Interval newInterval, final List<Interval> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { intervals, newInterval });

		for (Object answer : answers) {
			List<Interval> ans = (List<Interval>) answer;
			assertThat(ans.size()).isEqualTo(expectedOutput.size());
			for (int idx = 0; idx < ans.size(); idx ++) 
				assertThat(ans.get(idx)).isEqualTo(expectedOutput.get(idx));
		}	

		System.out.println("ok!");
	}
	
}
