package me.hxkandwal.daily.challanges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Three Split
 * 
 * You have a long strip of paper with integers written on it in a single line from left to right. You wish to cut the paper into exactly three pieces 
 * such that each piece contains at least one integer and the sum of the integers in each piece is the same. You cannot cut through a number, i.e. each 
 * initial number will unambiguously belong to one of the pieces after cutting. How many ways can you do it?
 * 
 * It is guaranteed that the sum of all elements in the array is divisible by 3.
 * 
 * Example :
 * 		For a = [0, -1, 0, -1, 0, -1], the output should be threeSplit(a) = 4.
 * 		
 * Here are all possible ways:	[0, -1] [0, -1] [0, -1]
 * 								[0, -1] [0, -1, 0] [-1]
 * 								[0, -1, 0] [-1, 0] [-1]
 * 								[0, -1, 0] [-1] [0, -1]
 * 
 * link: https://codefights.com/tournaments/hR45x5vXxN2c2qnQY/C
 * 
 * @author Hxkandwal
 *
 */
public class ThreeSplit extends AbstractCustomTestRunner {

	private static ThreeSplit _instance = new ThreeSplit();
	
	private ThreeSplit() {}
	
	public static int _threeSplit(int[] a) {
		int sum = 0, idx1 = 0, count = 0;
		
		for (int ai : a) sum += ai;
		sum = sum / 3;

		Integer sum1 = null;
		while (idx1 < a.length) {
			sum1 = (sum1 == null) ? a [idx1 ++] : sum1 + a [idx1 ++];
			
			if (sum1 != null && sum1 == sum) {
				Integer sum2 = null, idx2 = idx1;
				
				while (idx2 < a.length) {
					sum2 = (sum2 == null) ? a [idx2 ++] : sum2 + a [idx2 ++];
					
					if (sum2 != null && sum2 == sum) {
						Integer sum3 = null, idx3 = idx2;
						
			            while (idx3 < a.length) sum3 = (sum3 == null) ? a [idx3 ++] : sum3 + a [idx3 ++];
			            
			            if (sum3 != null && sum3 == sum) count ++;
					}
			    }
			}
		}

		return count;
	}
	
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
