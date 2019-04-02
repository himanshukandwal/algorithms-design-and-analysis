package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 436. Find Right Interval
 * 
 * Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end 
 * point of the interval i, which can be called that j is on the "right" of i.
 * 
 * For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start point to build the 
 * "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of 
 * each interval as an array.
 * 
 * Note:
 * 		You may assume the interval's end point is always bigger than its start point.
 * 		You may assume none of these intervals have the same start point.
 * 
 * Example 1:
 * 		Input: [ [1,2] ]
 * 		Output: [-1]
 * 		
 * 		Explanation: There is only one interval in the collection, so it outputs -1.
 * 
 * Example 2:
 * 		Input: [ [3,4], [2,3], [1,2] ]
 * 		Output: [-1, 0, 1]
 * 
 * 		Explanation: There is no satisfied "right" interval for [3,4].
 * 		For [2,3], the interval [3,4] has minimum-"right" start point;
 * 		For [1,2], the interval [2,3] has minimum-"right" start point.
 * 
 * 
 * Example 3:
 * 		Input: [ [1,4], [2,3], [3,4] ]
 * 		Output: [-1, 2, -1]
 * 
 * 		Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
 * 		For [2,3], the interval [3,4] has minimum-"right" start point.
 * 
 * @author Hxkandwal
 */
public class FindRightInterval extends AbstractCustomTestRunner {
	
	private static FindRightInterval _instance = new FindRightInterval();

	public static class Interval {
		int start, end;
		Interval() { start = 0; end = 0; }
		Interval(int s, int e) { start = s; end = e; }
	}

	// faster bucket sort solution
	public int[] _findRightIntervalBucketSort(Interval[] intervals) {
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for (Interval i : intervals) {
			min = Math.min (min, i.start);
			max = Math.max (max, i.end);
		}

		int len = max - min + 1;
		int [] buckets = new int [len];
		Arrays.fill (buckets, -1);

		// declare where everyone is relative to min (sorting) -> keep first one only (smallest index)
		for (int idx = 0; idx < intervals.length; idx ++)
			if (buckets [intervals [idx].start - min] == -1) buckets [intervals [idx].start - min] = idx;

		// suffix sum
		for (int idx = buckets.length - 2; idx >= 0; idx --)
			if (buckets [idx] == -1) buckets [idx] = buckets [idx + 1];

		// prepare ans (looking from end time)
		int [] ans = new int [intervals.length];
		for (int idx = 0; idx < intervals.length; idx ++)
			ans [idx] = buckets [intervals [idx].end - min];

		return ans;
	}

	// logic : sort by start time, search by end time.
	public static int[] _findRightInterval(Interval[] intervals) {
		Integer [] idxs = new Integer [intervals.length];
		for (int idx = 0; idx < idxs.length; idx ++) idxs [idx] = idx;
		Arrays.sort (idxs, (a, b) -> intervals [a].start - intervals [b].start);				// time consuming

		int[] ans = new int [intervals.length];
		Arrays.fill (ans, -1);

		for (int idx = 0; idx < idxs.length; idx ++) {
			if (idx > 0 && intervals [idxs [idx]].end == intervals [idxs [idx - 1]].end)		// use previous ans, if the end time is same
				ans [idxs [idx]] = ans [idxs [idx - 1]];
			else {
				for (int j = idx + 1; j < idxs.length; j ++) {
					if (intervals [idxs [j]].start >= intervals [idxs [idx]].end) {				// else look for correct one.
						ans [idxs [idx]] = idxs [j];
						break;
					}
				}
			}
		}
		return ans;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new Interval[] { new Interval(3, 4), new Interval(2, 3), new Interval(1, 2) }, new int[] { -1, 0, 1 });
		_instance.runTest(new Interval[] { new Interval(4, 5), new Interval(2, 3), new Interval(1, 2) }, new int[] { -1, 0, 1 });
	}

	public void runTest(final Interval[] intervals, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { intervals });

		for (Object answer : answers)
			assertThat((int[]) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	

}
