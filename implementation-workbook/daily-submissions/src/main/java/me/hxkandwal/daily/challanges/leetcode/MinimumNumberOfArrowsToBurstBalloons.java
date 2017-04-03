package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 452. Minimum Number of Arrows to Burst Balloons
 * 
 * There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and 
 * end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of 
 * start and end of the diameter suffice. Start is always smaller than end. There will be at most 104 balloons.
 * 
 * An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an 
 * arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow once shot keeps 
 * traveling up infinitely. The problem is to find the minimum number of arrows that must be shot to burst all balloons.
 * 
 * Example:
 * 		Input: [[10,16], [2,8], [1,6], [7,12]]
 * 		Output: 2
 * 
 * 		Explanation: One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow 
 * 					 at x = 11 (bursting the other two balloons).
 * 
 * @author Hxkandwal
 */
public class MinimumNumberOfArrowsToBurstBalloons extends AbstractCustomTestRunner {
	
	private static MinimumNumberOfArrowsToBurstBalloons _instance = new MinimumNumberOfArrowsToBurstBalloons();

	public int _findMinArrowShots(int[][] points) {
        Arrays.sort(points, (o1, o2) -> o1 [0] - o2 [0] == 0 ? o1 [1] - o2 [1] : o1 [0] - o2 [0]);
        int idx = 0, res = 0;
        while (idx < points.length) {
            int end = points [idx][1];
            while (idx + 1 < points.length) { 
            	if (end >= points [idx + 1][0]) { end = Math.min (end, points [idx + 1][1]); idx ++; }
            	else break;
            }
            res ++; idx ++;
        }
        return res;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [][] { new int [] { 10, 16 }, new int [] { 2, 8 }, new int [] { 1, 6 }, new int [] { 7, 12 }}, 2);
		_instance.runTest(new int [][] { new int [] { 9, 12 }, new int [] { 1, 10 }, new int [] { 4, 11 }, 
										 new int [] { 8, 12 }, new int [] { 3, 9 }, new int [] { 6, 9 }, 
										 new int [] { 6, 7 }}, 2);
	}

	public void runTest(final int[][] points, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { points });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
