package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 492. Construct the Rectangle
 * 
 * For a web developer, it is very important to know how to design a web page's size. So, given a specific rectangular web page’s area, your 
 * job by now is to design a rectangular web page, whose length L and width W satisfy the following requirements:
 * 
 * 1. The area of the rectangular web page you designed must equal to the given target area.
 * 2. The width W should not be larger than the length L, which means L >= W.
 * 3. The difference between length L and width W should be as small as possible.
 * 
 * You need to output the length L and the width W of the web page you designed in sequence.
 * Example:
 * 		Input: 4
 * 		Output: [2, 2]
 * 
 * 		Explanation: The target area is 4, and all the possible ways to construct it are [1,4], [2,2], [4,1].
 * 
 * But according to requirement 2, [1,4] is illegal; according to requirement 3,  [4,1] is not optimal compared to [2,2]. So the length L is 2, and the width W is 2.
 * 
 * @author Hxkandwal
 *
 */
public class ConstructTheRectangle extends AbstractCustomTestRunner {
	
	private static ConstructTheRectangle _instance = new ConstructTheRectangle();
	
	private ConstructTheRectangle() {}
	
	public int[] _constructRectangle(int area) {
		int [] ans = new int[] { -1, -1 }; int minDiff = Integer.MAX_VALUE;
		
		for (int idx = 1; idx * idx <= area; idx ++) {
			if (area % idx == 0) { 
				int oidx = area/idx;
				
				if (minDiff > Math.abs(idx - oidx)) {
					minDiff = Math.min(minDiff, Math.abs(idx - oidx));
					ans [0] = (idx >= oidx) ? idx : oidx;
					ans [1] = (idx >= oidx) ? oidx : idx;
				}
			}	
		}
		
		return ans;
    }	
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(4, new int[] { 2, 2 });
		_instance.runTest(2, new int[] { 2, 1 });
	}

	public void runTest(final int area, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { area });

		for (Object answer : answers)
			assertThat((int[]) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	

}
