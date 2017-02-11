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
		int sum = 0, count = 0, idx = 0;
		
		for (int ai : a) sum += ai;
		sum = sum / 3;

		int mark1 = -1, mark2 = -1;
		while (mark2 == -1) {
			Integer lsum = null;
			
			while (lsum == null || lsum != sum) lsum = (lsum == null) ? a [idx ++] : lsum + a [idx ++];
			
			mark2 = (mark1 != -1) ? idx - 1 : -1;
			mark1 = (mark1 == -1) ? idx - 1 : mark1;
		}
		count ++;
		
		final int markleft = mark1;
		final int markright = mark2;
		
		while (mark1 + 1 < a.length && a [mark1 + 1] == 0) {
			count ++; mark1 ++;
			
			while (mark2 + 1 < a.length && a [mark2 + 1] == 0) { 
				count ++; mark2 ++;
			} 
			mark2 = markright;
			
			while (mark2 - 1 >= 0 && a [mark2] == 0 && mark2 < mark1) {
				count ++; mark2 --;
			} 
			mark2 = markright;
		} 
		mark1 = markleft;
		
		while (mark1 - 1 >= 0 && a [mark1] == 0) {
			count ++; mark1 --;
			
			while (mark2 + 1 < a.length && a [mark2 + 1] == 0 && mark2 < mark1) { 
				count ++; mark2 ++;
			} 
			mark2 = markright;
			
			while (mark2 - 1 >= 0 && a [mark2] == 0) {
				count ++; mark2 --;
			} 
			mark2 = markright;
		} 
		mark1 = markleft;
		
		while (mark2 + 1 < a.length && a [mark2 + 1] == 0) {
			count ++; mark2 ++;
			
			while (mark1 + 1 < a.length && a [mark1 + 1] == 0) { 
				count ++; mark1 ++;
			} 
			mark1 = markleft;
			
			while (mark1 - 1 >= 0 && a [mark1] == 0 && mark2 < mark1) {
				count ++; mark1 --;
			} 
			mark1 = markleft;
		} 
		mark2 = markright;
		
		while (mark2 - 1 >= 0 && a [mark2] == 0) {
			count ++; mark2 --;
			
			while (mark1 + 1 < a.length && a [mark1 + 1] == 0 && mark2 < mark1) { 
				count ++; mark1 ++;
			} 
			mark1 = markleft;
			
			while (mark1 - 1 >= 0 && a [mark1] == 0) {
				count ++; mark1 --;
			} 
			mark1 = markleft;
		}
		mark2 = markright;
		
		return count;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 0, -1, 0, -1, 0, -1 }, 4);
	}

	public void runTest(final int[] a, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}		

}
