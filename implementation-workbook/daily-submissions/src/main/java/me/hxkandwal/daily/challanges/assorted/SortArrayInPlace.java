package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Sort array contents
 * 
 * @author Hxkandwal
 */
public class SortArrayInPlace extends AbstractCustomTestRunner {
	
	private static SortArrayInPlace _instance = new SortArrayInPlace();
	
	private SortArrayInPlace() {}

	// three pointer strategy
	public static int[] _sort (int[] array) {
		int idx = 0, low = 0, high = array.length - 1;
		
		while (idx <= high) {
			if (array [idx] == 1)
				idx ++;
			else if (array [idx] == 0) {
				array [idx] = array [low];
				array [low] = 0;
				idx ++; low ++;
			} else {
				int t = array [high];
				array [high] = array [idx];
				array [idx] = t;
				idx ++; high --;
			}
		}
		
		return array;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 0, 2 }, new int[] { 0, 1, 2 });
	}

	public void runTest(final int[] array, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array });

		for (Object answer : answers)
			assertThat((int[]) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}		
	
}
