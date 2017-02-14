package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
	
	private FindRightInterval() {}
	
	public static class Interval {
		int start;
		int end;

		Interval() { start = 0; end = 0; }
		Interval(int s, int e) { start = s; end = e; }
	}
	
	// logic : sort by start time, search by end time.
	public static int[] _findRightInterval(Interval[] intervals) {
        int [] answer = new int [intervals.length];
        List<int[]> sortedIntervals = new ArrayList<>();
        
        for (int idx = 0; idx < intervals.length; idx ++)
			sortedIntervals.add(new int[] { intervals [idx].start, idx });
        
        Collections.sort (sortedIntervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1 [0] - o2 [0];
			}
		});
        
        for (int idx = 0; idx < intervals.length; idx ++) {
        	int search = intervals [idx].end;
        	int low = 0, high = sortedIntervals.size() - 1;
        	boolean found = false;
        	
        	int mid = -1;
        	while (low <= high) {
				mid = (low + high) >>> 1;
				if (sortedIntervals.get(mid)[0] > search)
					high = mid - 1;
				else if (sortedIntervals.get(mid)[0] < search)
					low = mid + 1;
				else {
					answer[idx] = sortedIntervals.get(mid)[1];
					found = true; break;
				}
			}

        	answer[idx] = (!found) ? (low < sortedIntervals.size() && high < sortedIntervals.size() ? sortedIntervals.get(low)[1] : -1) : answer[idx];
        }
        
		return answer;
    }
	
	// faster tree-map implementation
	public static int[] _findRightIntervalTreeMap(Interval[] intervals) {
		int[] result = new int[intervals.length];
		NavigableMap<Integer, Integer> intervalMap = new TreeMap<>();

		for (int i = 0; i < intervals.length; i ++)
			intervalMap.put(intervals[i].start, i);

		for (int i = 0; i < intervals.length; i ++) {
			Map.Entry<Integer, Integer> entry = intervalMap.ceilingEntry(intervals[i].end);
			result[i] = (entry != null) ? entry.getValue() : -1;
		}

		return result;
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
