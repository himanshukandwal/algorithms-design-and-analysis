package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Find peak value from ordered list of monotonically increasing and then decreasing numbers.
 * 
 * @author Hxkandwal
 */
public class PeakFinding extends AbstractCustomTestRunner {
	
	private static PeakFinding _instance = new PeakFinding();
	
	private PeakFinding() {}
	
	public static int _findPeak(int[] list) {
		int low = 0, high = list.length - 1, peak = -1;
		
		while (low <= high) {
			int mid = (low + high) >>> 1;
			peak = Math.max (peak, list [mid]);
			
			if (mid - 1 < 0 || list [mid - 1] > list [mid])
				high = mid - 1;
			else if (mid + 1 >= list.length || list [mid + 1] > list [mid])
				low = mid + 1;
			else 
				break;
		}
		
		return peak;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 2, 1 }, 2);
		_instance.runTest(new int[] { 1, 2, 3, 1 }, 3);
		_instance.runTest(new int[] { 2, 3, 1 }, 3);
		_instance.runTest(new int[] { 4, 3, 1 }, 4);
	}

	public void runTest(final int [] input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
