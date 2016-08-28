package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 7. Reverse Integer
 * 
 * Reverse digits of an integer.
 * 
 * Example1: x = 123, return 321
 * 
 * Example2: x = -123, return -321
 * 
 * @author Hxkandwal
 *
 */
public class ReverseInteger extends AbstractCustomTestRunner {
	
	private static ReverseInteger _instance = new ReverseInteger();
	
	public int _reverse(int x) {
		if (x < 10 && x > -10)
			return x;
		
		boolean isPos = (x > 0);
		x = (isPos) ? x : (-x);
		
		int answer = 0;
		while (x / 10 > 0 || x % 10 > 0) {
			long upValue = answer * 10l + (x % 10);
			
			if (upValue >= Integer.MAX_VALUE) {
			    answer = 0;
			    break;
			}
		    
		    answer = (int) upValue; 
		    x /= 10;
		}
				
		return (isPos) ? answer : (-answer);
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(123, 321);
		_instance.runTest(-123, -321);
		_instance.runTest(-1, -1);
		_instance.runTest(4, 4);
		_instance.runTest(10, 1);
		_instance.runTest(1534236469, 0);
		_instance.runTest(1000000009, 0);
		
		System.out.println("ok!");
	}
	
	public void runTest(final int x, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { x });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
	}
	
}
