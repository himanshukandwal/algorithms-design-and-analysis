package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 287. Find the Duplicate Number
 * 
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate 
 * number must exist. Assume that there is only one duplicate number, find the duplicate one.
 * 
 * Note:
 * 	You must not modify the array (assume the array is read only).
 * 	You must use only constant, O(1) extra space.
 * 	Your runtime complexity should be less than O(n^2).
 * 	There is only one duplicate number in the array, but it could be repeated more than once.
 * 
 * @author Hxkandwal
 */
public class FindTheDuplicateNumber extends AbstractCustomTestRunner {
	
	private static FindTheDuplicateNumber _instance = new FindTheDuplicateNumber();
	
	private FindTheDuplicateNumber() {}
	
	/**
	 * The main idea is the same with problem Linked List Cycle II,https://leetcode.com/problems/linked-list-cycle-ii/. 
	 * Use two pointers the fast and the slow. The fast one goes forward two steps each time, while the slow one goes only 
	 * step each time. They must meet the same item when slow==fast. In fact, they meet in a circle, the duplicate number 
	 * must be the entry point of the circle when visiting the array from nums[0]. Next we just need to find the entry point. 
	 * 
	 * We use a point(we can use the fast one before) to visit form beginning with one step each time, do the same job to slow. 
	 * When fast==slow, they meet at the entry point of the circle. The easy understood code is as follows.
	 * 
	 * http://keithschwarz.com/interesting/code/?dir=find-duplicate
	 */
    public int _findDuplicate(int[] nums) {
    	int slow = nums [0], fast = nums [slow], finder = 0;
    	
    	while (slow != fast) {
    		slow = nums [slow];
    		fast = nums [nums [fast]];
    	}
    	
    	while (finder != slow) {
    		finder = nums [finder];
    		slow = nums [slow];
    	}
        
        return slow;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, 1 }, 1);
		_instance.runTest(new int [] { 1, 2, 2 }, 2);
		_instance.runTest(new int [] { 2, 2, 2, 2, 2 }, 2);
		_instance.runTest(new int [] { 13, 46, 8, 11, 20, 17, 40, 13, 13, 13, 14, 1, 13, 36, 48, 41, 13, 13, 13, 13, 45, 
									   13, 28, 42, 13, 10, 15, 22, 13, 13, 13, 13, 23, 9, 6, 13, 47, 49, 16, 13, 13, 39,
									   35, 13, 32, 29, 13, 25, 30, 13 }, 13);
		_instance.runTest(new int[] { 26, 2, 9, 20, 31, 7, 14, 32, 37, 15, 29, 6, 12, 38, 48, 22, 19, 45, 42, 40, 1, 12,
									  25, 36, 39, 30, 35, 4, 27, 12, 49, 16, 47, 3, 44, 41, 8, 17, 21, 23, 10, 43, 12, 34, 
									  24, 28, 33, 13, 46, 11 }, 12);
	}

	public void runTest(final int[] nums, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
    
}
