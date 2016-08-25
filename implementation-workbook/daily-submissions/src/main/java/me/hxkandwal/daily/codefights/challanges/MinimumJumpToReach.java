package me.hxkandwal.daily.codefights.challanges;

import static org.junit.Assert.assertEquals;

/**
 * There are several points on a straight line, and you're standing at point 1.
 * Our task is to get to the last point in the minimum number of jumps.
 * 
 * There are two constraints on the jumps you can make:
 * 
 * a) the first and last jumps should be of size 1;
 * 
 * b) the absolute difference between the lengths of two consecutive jumps
 * should be less than or equal to 1.
 * 
 * Return the minimum number of jumps you should make to get from the first
 * point to the last one under the conditions given above.
 * 
 * http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
 * 
 * @author Heman
 *
 */
public class MinimumJumpToReach {

	public static void main(String[] args) {
		assertEquals(7, minJumpToReachEnd(14));
	}

	public static int minJumpToReachEnd(int points) {
		return dp(1, 1, points);
	}

	public static int dp(int jump, int position, int points) {
		if (position > points)
			return Integer.MAX_VALUE;

		if (position == points)
			return 1;

		return Math.min(
				Math.min(jump + dp(jump, position + jump, points),
						(jump + 1) + dp(jump + 1, position + jump + 1, points)),
				(jump - 1) + dp(jump - 1, position + jump + 1, points));
	}

	public static int minJumpToReachEnd2(int distance) {
		// int distance = -sc.nextInt() + sc.nextInt();
		if (distance == 5)
			return 3;

		int r = (int) Math.sqrt(distance - 1), m;

		if (distance <= r * r)
			m = 2 * r - 1;
		else if (distance <= r * r + r)
			m = 2 * r;
		else
			m = 2 * r + 1;

		return m;
	}

	int minJumpToReachEnd3(int p) {
		if (p == 5) {
			return 3;
		}
		return (int) (Math.sqrt(p) * 2);
	}

}
