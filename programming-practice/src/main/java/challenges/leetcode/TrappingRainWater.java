package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 42. Trapping Rain Water
 * 
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is 
 * able to trap after raining.
 * 
 * For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * 
 * @author Hxkandwal
 */
public class TrappingRainWater extends AbstractCustomTestRunner {
	
	private static TrappingRainWater _instance = new TrappingRainWater();
	
	public int _trap(int[] height) {
		if (height.length == 0) return 0;
        int total = 0, prev = height [0];
        Stack <int []> stack = new Stack <>();
        
        for (int idx = 1; idx < height.length; idx ++) {
            int mIdx = idx - 1;
            while (!stack.isEmpty() && prev < height [idx]) {
                int [] saved = stack.pop ();
                total += (Math.min (saved [0], height [idx]) - prev) * (idx - saved [1] - 1);
                prev = saved [0]; mIdx = saved [1];
            }
            
            if (prev > height [idx]) stack.push (new int [] { prev, mIdx }); 
            prev = height [idx]; 
        }
        return total;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 4, 3 }, 0);
		_instance.runTest(new int[] { 0, 2, 0 }, 0);
		_instance.runTest(new int[] { 4, 2, 3 }, 1);
		_instance.runTest(new int[] { 2, 1, 3, 1, 5 }, 3);
		_instance.runTest(new int[] { 4, 2, 0, 3, 2, 5 }, 9);
		_instance.runTest(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }, 6);
	}
	
	public void runTest(final int[] height, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { height });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
