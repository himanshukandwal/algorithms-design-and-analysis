package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Convert Array
 * 
 * http://www.ardendertat.com/2011/10/18/programming-interview-questions-9-convert-array/
 * 
 * @author Hxkandwal
 */
public class ConvertArray extends AbstractCustomTestRunner {
	
	private static ConvertArray _instance = new ConvertArray();
	
	public static int[] _convert (int [] arr) {
		int [] ans = new int [arr.length];
		
		for (int idx = 0; idx < arr.length; idx ++) 
			ans [idx] = arr [(idx % 3) * 3 + (idx / 3)];
		
		return ans;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 2, 3, 1, 2, 3, 1, 2, 3 }, new int[] { 1, 1, 1, 2, 2, 2, 3, 3, 3 });
	}
	
	public void runTest(final int [] arr, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { arr });
		
		for (Object answer : answers) 
			assertThat((int[]) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
