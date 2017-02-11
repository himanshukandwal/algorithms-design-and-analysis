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
 * link: https://codefights.com/tournaments/9TtH2eKkA88maNTSH/C
 * 
 * @author Hxkandwal
 *
 */
public class AxisAlignedCirclesBoundingBox extends AbstractCustomTestRunner {
	
	private static AxisAlignedCirclesBoundingBox _instance = new AxisAlignedCirclesBoundingBox();
	
	private AxisAlignedCirclesBoundingBox() {}
	
	
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 0, -1, 0, -1, 0, -1 }, 4);
		_instance.runTest(new int[] { -1, 0, -1, 0, -1, 0 }, 4);
		_instance.runTest(new int[] { -1, 1, -1, 1, -1, 1, -1, 1 }, 3);
	}

	public void runTest(final int[] a, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}		

}
