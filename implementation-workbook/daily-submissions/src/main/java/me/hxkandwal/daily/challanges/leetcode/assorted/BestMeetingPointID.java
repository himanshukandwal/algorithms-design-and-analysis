package me.hxkandwal.daily.challanges.leetcode.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 296. Best Meeting Point
 * 
 * @author Hxkandwal
 */
public class BestMeetingPointID extends AbstractCustomTestRunner {
	
	private static BestMeetingPointID _instance = new BestMeetingPointID();

	public int _minTotalDistance(int[] grid) {
        int mid = -1;
        boolean jump = false;
        for (int idx = 0; idx < grid.length; idx ++) {
        	if (grid [idx] == 1) {
        		if (mid == -1) { mid = idx; jump = false; }
        		else { mid += (jump) ? 1 : 0; break; }
        	} else if (mid != -1) { 
        		mid += (jump) ? 1 : 0;
        		jump = !jump;
        	}
        }
        return mid;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, 0, 0, 1 }, 1);
		_instance.runTest(new int [] { 1, 0, 0, 0, 1 }, 2);
		_instance.runTest(new int [] { 0, 1, 0, 0, 1 }, 2);
		_instance.runTest(new int [] { 0, 0, 0, 0, 1, 0, 0, 0, 1 }, 6);
	}

	public void runTest(final int[] grid, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { grid });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}

}
