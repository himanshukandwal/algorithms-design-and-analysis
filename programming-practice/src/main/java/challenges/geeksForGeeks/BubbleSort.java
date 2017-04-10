package challenges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Bubble sorting algorithm : 
 * Bubble Sort is the simplest sorting algorithm that works by repeatedly swapping 
 * the adjacent elements if they are in wrong order.
 * 
 * link : http://quiz.geeksforgeeks.org/bubble-sort/ 
 * 
 * @author Hxkandwal
 *
 */
public class BubbleSort extends AbstractCustomTestRunner {

	private static BubbleSort _instance = new BubbleSort();
	
	private BubbleSort() {}
	
	public static void _sort(int[] array) {
		if (array == null || array.length <= 1)
			return;
		
		for (int idx = 0; idx < array.length - 1; idx ++) {
			for (int innerIdx = 0; innerIdx < array.length - idx -1; innerIdx ++) {
				if (array [innerIdx] > array [innerIdx + 1]) {
					int tempElement = array [innerIdx];
					array [innerIdx] = array [innerIdx + 1];
					array [innerIdx + 1] = tempElement;
				}
			}
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
