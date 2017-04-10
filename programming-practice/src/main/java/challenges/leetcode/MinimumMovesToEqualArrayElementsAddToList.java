package challenges.leetcode;

import java.util.stream.IntStream;

import challenges.AbstractCustomTestRunner;

/**
 * 453. Minimum Moves to Equal Array Elements Add to List
 * 
 * Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is 
 * incrementing n - 1 elements by 1.
 * 
 * Example:
 * 		Input: [1,2,3]
 * 		Output: 3
 * 
 * 		Explanation: Only three moves are needed (remember each move increments two elements):
 * 		[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 * 
 * @author Hxkandwal
 */
public class MinimumMovesToEqualArrayElementsAddToList extends AbstractCustomTestRunner {

	/**
	 * Adding 1 to n - 1 elements is the same as subtracting 1 from one element, w.r.t goal of making the elements in the array equal.
	 * So, best way to do this is make all the elements in the array equal to the min element.
	 * sum(array) - n * minimum
	 */
	public int minMoves(int[] nums) {
        if (nums.length == 0) return 0;
        int min = Integer.MAX_VALUE, res = 0;
        for (int num : nums) min = Math.min (min, num);
        for (int num : nums) res += Math.abs (num - min);
        return res;
    }

	public int minMovesOneLiner(int[] nums) {
	    return IntStream.of(nums).sum() - nums.length * IntStream.of(nums).min().getAsInt();
	}
	
}
