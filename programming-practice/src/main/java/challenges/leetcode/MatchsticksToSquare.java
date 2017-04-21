package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 473. Matchsticks to Square
 * 
 * Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a 
 * way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each 
 * matchstick must be used exactly one time.
 * 
 * Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, 
 * to represent whether you could make one square using all the matchsticks the little match girl has.
 * 
 * Example 1:
 * 		Input: [1,1,2,2,2]
 * 		Output: true
 * 		Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 * 
 * Example 2:
 * 		Input: [3,3,3,3,4]
 * 		Output: false
 * 		Explanation: You cannot find a way to form a square with all the matchsticks.
 * 
 * Note:
 * 	1. The length sum of the given matchsticks is in the range of 0 to 10^9.
 * 	2. The length of the given matchstick array will not exceed 15.
 * 
 * @author Hxkandwal
 */
public class MatchsticksToSquare extends AbstractCustomTestRunner {

	private static MatchsticksToSquare _instance = new MatchsticksToSquare();
	
	/**
	 * Whenever think about DFS solutioning, recurse from WITHIN the for loop and try to revert if things are not right. 
	 * This will allow us to cover all the combinations from that point rather than, finding max/ min using PQ and trying things. 
	 * This can lead to incorrect result ans these are one step hacks. Try to see all possibilities with recursing within the for 
	 * loop and after we have tried all possibilities we must say there is no solution. 
	 * This is generic and will cover the case of PQ (min/max) easily.
	 * Similar to generate permuatations, we backtrack for correction or with correct answer.
	 */
	public boolean _makesquare(int[] nums) {
		Arrays.sort (nums);
        int sum = 0;  
        for (int num : nums) sum += num;
        if (sum % 4 != 0 || nums.length < 4 || nums [nums.length - 1] > sum / 4 || nums [0] < 0) return false;
        int [] buckets = new int [4];
        return dfs (nums, buckets, nums.length - 1, sum/4);
    }
    
    private boolean dfs (int [] nums, int [] buckets, int index, int target) {
        if (index < 0) {
            for (int bucket : buckets) if (bucket != target) return false;
            return true;
        }
        
        for (int idx = 0; idx < buckets.length; idx ++) {
        	if (buckets [idx] + nums [index] <= target) {
        		buckets [idx] += nums [index];
        		if (dfs (nums, buckets, index - 1, target)) return true;
        		buckets [idx] -= nums [index];
        	}
        }
        return false;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 1, 2, 2, 2 }, true);
		_instance.runTest(new int[] { 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3 }, true);
		_instance.runTest(new int[] { 10, 6, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2 }, true);
	}

	public void runTest(final int[] nums, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	} 
}
