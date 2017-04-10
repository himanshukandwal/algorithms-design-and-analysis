package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

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
        Stack<int[]> stk = new Stack<>();
        int maxArea = 0, idx = 0;
        
        for (idx = 0; idx < heights.length; idx ++) {
            int bestStart = idx;
            while (!stk.isEmpty() && stk.peek ()[0] > heights [idx]) {
                int [] top = stk.pop ();
                maxArea = Math.max (maxArea, top [0] * (idx - (bestStart = top [1])));
            }
            stk.push (new int[] { heights [idx], bestStart });
        }
        
        while (! stk.isEmpty()) {
            int [] top = stk.pop ();
            maxArea = Math.max (maxArea, top [0] * (idx - top [1]));
        }
        return maxArea;
    }

	public static int largestRectangleArea2(int[] height) {
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for(int i = 0; i <= len; i++){
            int h = (i == len ? 0 : height[i]);
            
            if (s.isEmpty() || h >= height [s.peek()])
                s.push(i);
            else
                maxArea = Math.max(maxArea, height[s.pop()] * (s.isEmpty() ? i -- : -- i - s.peek()));
        }
        return maxArea;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 2, 1, 5, 6, 2, 3 }, 10);
		_instance.runTest(new int[] { 2, 1, 2 }, 3);
		_instance.runTest(new int[] { 1, 3, 2, 1, 2 }, 5);
		_instance.runTest(new int[] { 1, 1, 1, 1, 1 }, 5);
		_instance.runTest(new int[] { 1, 1, 1, 0, 1 }, 3);
	}

	public void runTest(final int[] input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}		

}
