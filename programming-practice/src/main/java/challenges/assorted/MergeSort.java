package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Program that implements generic merge sorting.
 * 
 * @author Hxkandwal
 *
 */
public class MergeSort extends AbstractCustomTestRunner {
	
	private static MergeSort _instance = new MergeSort();
	
	private MergeSort() {}
	
	public static int[] _mergeSort(int[] inputArray) {
		merge(inputArray, 0, inputArray.length - 1);
		return inputArray;
	}
	
	private static void merge(int[] inputArray, int l, int r) {
		if (l < r) {
			int m = ((l + r) >>> 1);
			merge(inputArray, l, m);
			merge(inputArray, m + 1, r);
			sort(inputArray, l, m, r);
		}
	}
	
	private static void sort(int[] inputArray, int l, int m, int r) {
		for (int idx = l, mIndex = m + 1; idx < r; idx ++) {
			if (idx == mIndex) mIndex ++;
			
			if (inputArray [idx] > inputArray [mIndex]) {
				int temp = inputArray [idx];
				inputArray [idx] = inputArray [mIndex];
				inputArray [mIndex] = temp;
			}
		}
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] {}, new int[] {});
		_instance.runTest(new int[] { 1, 2, 3 }, new int[] { 1, 2, 3 });
		_instance.runTest(new int[] { 3, 2, 1 }, new int[] { 1, 2, 3 });
		_instance.runTest(new int[] { 3, 2, 1, 0, 6, 4, 4, 3, 1, 5, 6, 12, 23, 12, 23, 12, 45, 45, 80 }, new int[] { 0, 1, 1, 2, 3, 3, 4, 4, 5, 6, 6, 12, 12, 12, 23, 23, 45, 45, 80 });
	}

	public void runTest(final int[] input, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((int[]) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
