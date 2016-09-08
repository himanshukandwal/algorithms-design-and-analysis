package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Insertion Sort algorithm :
 * Insertion sort is a simple sorting algorithm that works the way we sort playing cards in our hands.
 * 
 * link : http://quiz.geeksforgeeks.org/insertion-sort/
 * 
 * @author Hxkandwal
 *
 */
public class InsertionSort extends AbstractCustomTestRunner {

	private static InsertionSort _instance = new InsertionSort();
	
	private InsertionSort() {}
	
	public static void _sort(int[] array) {
		if (array == null || array.length <= 1)
			return;
		
		for (int idx = 1; idx < array.length; idx ++) {
			int element = array [idx];
			int innerIdx = idx - 1;
			
			while (innerIdx >= 0 && array [innerIdx] > element) {
				array [innerIdx + 1] = array [innerIdx];
				innerIdx --;
			}
			
			array [innerIdx + 1] = element;
		}
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] {}, new int[] {});
		_instance.runTest(null, null);
		_instance.runTest(new int[] { 3, 4, 5, 1, 2 }, new int[] { 1, 2, 3, 4, 5 });
		_instance.runTest(new int[] { 3, 1, 5, 0, 2, 7 }, new int[] { 0, 1, 2, 3, 5, 7 });
	}

	public void runTest(final int[] array, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array });

		for (Object answer : answers)
			assertThat(array).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}