package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Merge two unsorted arrays into a single sorted array. No extra space are allowed.
 * 
 * @author Hxkandwal
 *
 */
public class MergeUnsortedIntoSortedArrayNoExtraSpace extends AbstractCustomTestRunner {
	
	private static MergeUnsortedIntoSortedArrayNoExtraSpace _instance = new MergeUnsortedIntoSortedArrayNoExtraSpace();
	
	private MergeUnsortedIntoSortedArrayNoExtraSpace() {}
	
	public static int[] _mergeUnsortedArrays(int[] array1, int[] array2) {

		insertionSort(array1);
		
		insertionSort(array2);
		
		int idx1 = 0, idx2 = 0, idx = 0;
		
		// resulting sorted array.
		int[] result = new int[array1.length + array2.length];
		
		// free-form arrays controlling.
		while (idx1 < array1.length || idx2 < array2.length) {
			
			if (idx1 < array1.length && idx2 < array2.length) {
				if (array1 [idx1] < array2 [idx2])
					result [idx ++] = array1 [idx1 ++];
				else if (array1 [idx1] > array2 [idx2])
					result [idx ++] = array2 [idx2 ++];
				else {
					result [idx ++] = array1 [idx1 ++];
					result [idx ++] = array2 [idx2 ++];
				}
			} else if (idx1 < array1.length)
				result [idx ++] = array1 [idx1 ++];
			else
				result [idx ++] = array2 [idx2 ++];
		}
		
		return result;
	}
	
	private static void insertionSort (int[] array) {
		for (int idx = 1; idx < array.length; idx ++) {
			int value = array [idx], innerIdx = idx - 1;
			
			while (innerIdx >= 0 && value < array [innerIdx]) {
				array [innerIdx + 1] = array [innerIdx];
				innerIdx --;
			}
			
			if (innerIdx != idx - 1)
				array [innerIdx + 1] = value;
		}
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 2, 2, 3, 1, 4, 1, 6, 7 }, new int[] { 1, 2, 2, 3 }, new int[] { 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 6, 7 });
	}

	public void runTest(final int[] input1, final int[] input2, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input1, input2 });

		for (Object answer : answers)
			assertThat((int[]) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	

}
