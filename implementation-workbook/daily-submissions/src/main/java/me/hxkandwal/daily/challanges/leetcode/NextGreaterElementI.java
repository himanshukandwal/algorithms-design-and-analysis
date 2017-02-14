package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Next Greater Element I
 * 
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's 
 * elements in the corresponding places of nums2.
 * 
 * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.
 * 
 * Example 1:
 * 		Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 		Output: [-1,3,-1]
 * 
 * 		Explanation:
 * 			For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
 * 			For number 1 in the first array, the next greater number for it in the second array is 3.
 * 			For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
 * 
 * Example 2:
 * 		Input: nums1 = [2,4], nums2 = [1,2,3,4].
 * 		Output: [3,-1]
 * 		Explanation:
 * 			For number 2 in the first array, the next greater number for it in the second array is 3.
 * 			For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
 * 
 * @author Hxkandwal
 */
public class NextGreaterElementI extends AbstractCustomTestRunner {
	
	private static NextGreaterElementI _instance = new NextGreaterElementI();
	
	public NextGreaterElementI() {}
	
	public static int[] _nextGreaterElement(int[] findNums, int[] nums) {
		int[] answer = new int [findNums.length];
		for (int idx = 0; idx < answer.length; idx ++) answer [idx] = -1;
		
		Map<Integer, Integer> map = new HashMap<>();
		for (int idx = 0; idx < nums.length; idx ++) map.put(nums [idx], idx);
		
		for (int idx = 0; idx < findNums.length; idx ++) {
			int search = findNums [idx];
			if (map.containsKey(search))
				for (int innerIdx = map.get(search) + 1; innerIdx < nums.length; innerIdx ++)
					if (nums [innerIdx] > search) {  answer [idx] = nums [innerIdx];  break;  }
		}
		
        return answer;
    }  
	
	public static int[] _nextGreaterElementStack(int[] findNums, int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(); // map from x to next greater element of x
        Stack<Integer> stack = new Stack<>();
        
        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek() < num)
                map.put(stack.pop(), num);
            stack.push(num);
        }   
        
        for (int i = 0; i < findNums.length; i++) findNums[i] = map.getOrDefault(findNums[i], -1);
        return findNums;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 4, 1, 2 }, new int[] { 1, 3, 4, 2 }, new int[] { -1, 3, -1 });
	}

	public void runTest(final int[] findNums, final int[] nums, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { findNums, nums });

		for (Object answer : answers)
			assertThat((int[]) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
