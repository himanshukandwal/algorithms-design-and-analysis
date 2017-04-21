package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 456. 132 Pattern
 * 
 * Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. 
 * Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.
 * 
 * Note: n will be less than 15,000.
 * Example 1:
 * 		Input: [1, 2, 3, 4]
 * 		Output: False
 * 		Explanation: There is no 132 pattern in the sequence.
 * 
 * Example 2:
 * 		Input: [3, 1, 4, 2]
 * 		Output: True
 * 		Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 * 
 * Example 3:
 * 		Input: [-1, 3, 2, 0]
 * 		Output: True
 * 		Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 * 
 * @author Hxkandwal
 */
public class The132Pattern extends AbstractCustomTestRunner {
	
	private static The132Pattern _instance = new The132Pattern();
	
	/**
	 * We want to search for a subsequence (s1,s2,s3)
	 * 
	 * INTUITION: The problem would be simpler if we want to find sequence with s1 > s2 > s3, we just need to find s1, followed by s2 and s3. 
	 * 			  Now if we want to find a 132 sequence, we need to switch up the order of searching. we want to first find s2, followed by s3, 
	 * 			  then s1.
	 * 
	 * IDEA: We can start from either side but I think starting from the end allow us to finish in a single pass. The idea is to start from end 
	 * 		 and search for a candidate for s2 and s3. A number becomes a candidate for s3 if there is any number on the left of s2 that is bigger 
	 * 		 than it.
	 * 
	 * DETECTION: Keep track of the largest candidate of s3 and once we encounter any number smaller than s3, we know we found a valid sequence 
	 * 			  since s1 < s3 implies s1 < s2.
	 * 
	 * IMPLEMENTATION:
	 * Have a stack, each time we store a new number, we first pop out all numbers that are smaller than that number. The numbers that are popped out 
	 * becomes candidate for s3.
	 * We keep track of the maximum of such s3 (which is always the most recently popped number from the stack).
	 * Once we encounter any number smaller than s3, we know we found a valid sequence since s1 < s3 implies s1 < s2.
	 * 
	 * RUNTIME: Each item is pushed and popped once at most, the time complexity is therefore O(n).
	 * 
	 * EXAMPLE:
	 * 	i = 6, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 9, S3 candidate = None, Stack = Empty
	 * 	i = 5, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 7, S3 candidate = None, Stack = [9]
	 * 	i = 4, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 10, S3 candidate = None, Stack = [9,7]
	 * 	i = 3, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 9, S3 candidate = 9, Stack = [10]
	 * 	i = 2, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 8, S3 candidate = 9, Stack = [10,9] We have 8<9, sequence found!
	 * 
	 */
	public boolean _find132pattern(int[] nums) {
		if (nums.length <= 2) return false;
        Integer second = null;
        Stack<Integer> stk = new Stack <>();
        for (int idx = nums.length - 1; idx >= 0; idx --) {
            if (second != null && nums [idx] < second) return true;
            while (!stk.isEmpty() && stk.peek() < nums [idx]) second = stk.pop ();
            stk.push (nums [idx]);
        }
        return false;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { -1, 3, 2, 0 }, true);
		_instance.runTest(new int [] { 3, 1, 4, 2 }, true);
	}

	public void runTest(final int[] nums, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
