package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.AbstractMap;
import java.util.List;
import java.util.Stack;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 84. Largest Rectangle in Histogram
 * 
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle 
 * in the histogram.
 * 
 * @author Hxkandwal
 */
public class LargestRectangleInHistogram extends AbstractCustomTestRunner {
	
	private static LargestRectangleInHistogram _instance = new LargestRectangleInHistogram();
	
	private LargestRectangleInHistogram() {}
	
	public static int _largestRectangleArea (int[] heights) {
		int maxArea = 0;
		Stack<AbstractMap.SimpleEntry<Integer, Integer>> stack = new Stack<>();
		
		for (int idx = 0; idx < heights.length; idx ++) {
			if (stack.isEmpty()) {
				stack.add(new AbstractMap.SimpleEntry<Integer, Integer>(heights [idx], idx));
			} else {
				int index = idx;
				while (!stack.isEmpty() && heights [idx] < stack.peek().getKey())
					maxArea = Math.max (maxArea, stack.peek().getKey() * (idx - (index = stack.pop().getValue())));
					
				stack.push(new AbstractMap.SimpleEntry<Integer, Integer>(heights [idx], index));
			}
		}
		
		while (!stack.isEmpty()) maxArea = Math.max(maxArea, stack.peek().getKey() * (heights.length - stack.pop().getValue()));
		return maxArea;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 2, 1, 5, 6, 2, 3 }, 10);
		_instance.runTest(new int[] { 2, 1, 2 }, 3);
		_instance.runTest(new int[] { 1, 3, 2, 1, 2 }, 5);
	}

	public void runTest(final int[] input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}		

}
