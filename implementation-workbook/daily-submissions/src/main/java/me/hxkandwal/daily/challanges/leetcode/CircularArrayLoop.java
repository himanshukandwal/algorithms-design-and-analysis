package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 457. Circular Array Loop
 * 
 * You are given an array of positive and negative integers. 
 * If a number n at an index is positive, then move forward n steps. 
 * Conversely, if it's negative (-n), move backward n steps. Assume the first element of the array is 
 * forward next to the last element, and the last element is backward next to the first element. 
 * 
 * Determine if there is a loop in this array. A loop starts and ends at a particular index with more 
 * than 1 element along the loop. The loop must be "forward" or "backward'.
 * 
 * Example 1: Given the array [2, -1, 1, 2, 2], there is a loop, from index 0 -> 2 -> 3 -> 0.
 * Example 2: Given the array [-1, 2], there is no loop.
 * 
 * Note: The given array is guaranteed to contain no element "0".
 * 
 * Can you do it in O(n) time complexity and O(1) space complexity?
 * 
 * @author Hxkandwal
 */
public class CircularArrayLoop extends AbstractCustomTestRunner {
	
	private static CircularArrayLoop _instance = new CircularArrayLoop();
	
	private CircularArrayLoop() {}
	
	public boolean _circularArrayLoop(int[] nums) {
		
		return false;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 2, -1, 1, 2, 2 }, true);
		_instance.runTest(new int[] { -1, 2 }, false);
	}
	
	public void runTest(final int[] input, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers) 
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
