package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 435. Non-overlapping Intervals
 * 
 * Given a collection of intervals, find the minimum number of intervals you need to remove to 
 * make the rest of the intervals non-overlapping.
 * 
 * Note: You may assume the interval's end point is always bigger than its start point.
 * 		 Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 * 
 * Example 1:
 * 		Input: [ [1,2], [2,3], [3,4], [1,3] ]
 * 		Output: 1
 * 		Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 * 
 * Example 2:
 * 		Input: [ [1,2], [1,2], [1,2] ]
 * 		Output: 2
 * 		Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 * 
 * Example 3:
 * 		Input: [ [1,2], [2,3] ]
 * 		Output: 0
 * 		Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 * 
 * @author Hxkandwal
 */
public class NonOverlappingIntervals extends AbstractCustomTestRunner {
	
	private static NonOverlappingIntervals _instance = new NonOverlappingIntervals();
	
	public static class Interval {
		int start;
		int end;
		
		public Interval() { start = 0; end = 0; }
		public Interval(int s, int e) { start = s; end = e; }
	}
	
	public int _eraseOverlapIntervals(Interval[] intervals) {
		Arrays.sort (intervals, (a, b) -> a.end - b.end);
        int rejects = 0, end = -1;
        for (Interval interval : intervals)
            if (end == -1 || end <= interval.start) end = interval.end;
            else rejects ++;
        return rejects;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new Interval [] { new Interval(1, 2), new Interval(2, 3), new Interval(3, 4), new Interval(1, 3) }, 1);
	}

	public void runTest(final Interval[] intervals, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { intervals });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
