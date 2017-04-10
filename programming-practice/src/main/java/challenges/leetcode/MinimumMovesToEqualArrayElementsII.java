package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 462. Minimum Moves to Equal Array Elements II
 * 
 * Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing 
 * a selected element by 1 or decrementing a selected element by 1.
 * 
 * You may assume the array's length is at most 10,000.
 * 
 * @author Hxkandwal
 */
public class MinimumMovesToEqualArrayElementsII extends AbstractCustomTestRunner {
	
	private static MinimumMovesToEqualArrayElementsII _instance = new MinimumMovesToEqualArrayElementsII();
	
    public int minMoves2(int[] nums) {
        if (nums.length == 1) return 0;
        int median = quickFind (nums, 0, nums.length - 1, nums.length/2), sum = 0;
        for (int num : nums) 
            sum += Math.abs (median - num);
        return sum;
    }
    
    public int quickFind (int[] nums, int start, int end, int k) {
        int left = start, right = end, pivot = nums [end];
        while (true) {
            while (nums [left] < pivot && left < right) left ++;
            while (nums [right] >= pivot && left < right) right --;
            if (left == right) break;
            swap (nums, left, right);
        }
        swap (nums, left, end);
        if (k == left) return nums [left];
        else if (k < left) return quickFind (nums, start, left - 1, k);
        else return quickFind (nums, left + 1, end, k);
    }
    
    private void swap (int [] nums, int from, int to) {
        int t = nums [from];
        nums [from] = nums [to];
        nums [to] = t;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, 1, 2 }, 1);
		_instance.runTest(new int [] { 1, 2, 3 }, 2);
		_instance.runTest(new int [] { 3, 2, 1 }, 2);
		_instance.runTest(new int [] { 1, 0, 0, 8, 6 }, 14);
	}

	public void runTest(final int[] nums, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}

}
