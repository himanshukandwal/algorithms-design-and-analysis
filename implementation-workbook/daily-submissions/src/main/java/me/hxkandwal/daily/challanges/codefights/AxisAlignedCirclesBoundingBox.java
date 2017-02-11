package me.hxkandwal.daily.challanges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Axis aligned Circles Bounding Box.
 * 
 * Given a set of circles find the area of the axis-aligned minimum bounding box of the set of points which belong to 
 * at least one of the given circles.
 * 
 * Example:
 * 		For x = [1, 0, 4], y = [-1, 2, 2] and r = [3, 5, 4], 
 * 		the output should be axisAlignedCirclesBoundingBox(x, y, r) = 143.
 * 
 * 		In the image below circles are given in blue, green and yellow, and the axis-aligned minimum bounding box is given in red. Its area is 13 * 11 = 143.
 * 
 * link: https://codefights.com/tournaments/9TtH2eKkA88maNTSH/C
 * 
 * @author Hxkandwal
 *
 */
public class AxisAlignedCirclesBoundingBox extends AbstractCustomTestRunner {
	
	private static AxisAlignedCirclesBoundingBox _instance = new AxisAlignedCirclesBoundingBox();
	
	private AxisAlignedCirclesBoundingBox() {}
	
	public static int axisAlignedCirclesBoundingBox(int[] x, int[] y, int[] r) {
		int xmax = Integer.MIN_VALUE, xmin = Integer.MAX_VALUE;
		int ymax = Integer.MIN_VALUE, ymin = Integer.MAX_VALUE;
		
		for (int idx = 0; idx < x.length; idx ++) {
			xmax = Math.max (xmax, x [idx] + r [idx]);
			xmin = Math.min (xmin, x [idx] - r [idx]);
			
			ymax = Math.max (ymax, y [idx] + r [idx]);
			ymin = Math.min (ymin, y [idx] - r [idx]);
		}
		
		return (xmax - xmin) * (ymax - ymin);
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 0, 4 }, new int[] { -1, 2, 2 }, new int[] { 3, 5, 4 }, 143);
	}

	public void runTest(final int[] x, final int[] y, final int[] r, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { x, y, r });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}		

}
