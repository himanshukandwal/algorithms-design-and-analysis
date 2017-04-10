package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 55. Jump Game
 * 
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * 
 * Determine if you are able to reach the last index.
 * For example:
 * 		A = [2,3,1,1,4], return true.
 * 		A = [3,2,1,0,4], return false.
 *  
 * @author Hxkandwal
 */
public class JumpGame extends AbstractCustomTestRunner {
	
	private static JumpGame _instance = new JumpGame();

	public boolean _canJump(int[] nums) {
		int max = 0;
        for (int idx = 0; idx < nums.length; idx ++) {
        	if (max >= idx) max = Math.max (max, idx + nums [idx]);
            else return false;
        }
        return max >= nums.length - 1;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 0, 1 }, false);
	}
	
	public void runTest(final int[] nums, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });
		
		for (Object answer : answers) 
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
		
}
