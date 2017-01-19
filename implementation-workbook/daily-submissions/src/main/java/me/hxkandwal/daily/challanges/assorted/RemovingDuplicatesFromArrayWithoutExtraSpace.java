package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Remove the duplicates from the array without extra space.
 * 
 * @author Hxkandwal
 *
 */
public class RemovingDuplicatesFromArrayWithoutExtraSpace extends AbstractCustomTestRunner {
	
	private static RemovingDuplicatesFromArrayWithoutExtraSpace _instance = new RemovingDuplicatesFromArrayWithoutExtraSpace();
	
	private RemovingDuplicatesFromArrayWithoutExtraSpace() {}
	
	public static int _cleanupArray(int[] array) {
		int uniqueIdx = 0;
		
		for (int idx = 1; idx < array.length; idx ++) {
			boolean isDuplicate = false;
			
			for (int innerIdx = 0; innerIdx <= uniqueIdx; innerIdx++) {
				if (array[innerIdx] == array[idx]) {
					isDuplicate = true;
					break;
				}	
			}
			
			if (!isDuplicate)
				array[++ uniqueIdx] = array [idx];
		}
		
		return uniqueIdx + 1;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 2, 3, 3, 4, 5, 6, 6 }, 6);
		_instance.runTest(new int[] { 1, 2, 2, 3, 1 }, 3);
		_instance.runTest(new int[] { 1, 2, 2, 3, 1, 3, 1, 2, 1, 1 }, 3);
	}

	public void runTest(final int[] input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}

}
