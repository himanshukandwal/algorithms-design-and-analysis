package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

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
	
	public static int[] findRightInterval(Interval[] intervals) {
        int [] answer = new int [intervals.length];
        
		return null;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(null, null, 0);
		_instance.runTest(new int[] {}, new int[] {}, 0);
		_instance.runTest(new int[] { 1, 3 }, new int[] { 2 }, 2.0d);
		_instance.runTest(new int[] { 1, 2 }, new int[] { 3, 4 }, 2.5d);
	}

	public void runTest(final int[] nums1, final int[] nums2, final double expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums1, nums2 });

		for (Object answer : answers)
			assertThat((Double) answer).isWithin(expectedOutput);

		System.out.println("ok!");
	}	

}
