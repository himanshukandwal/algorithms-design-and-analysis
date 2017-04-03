package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 75. Sort Colors
 * 
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are 
 * adjacent, with the colors in the order red, white and blue.
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * 
 * Note: You are not suppose to use the library's sort function for this problem.
 * 
 * @author Hxkandwal
 */
public class SortColors extends AbstractCustomTestRunner {
	
	private static SortColors _instance = new SortColors();

	public int[] _sortColorsBetter(int[] nums) {
		int second = nums.length - 1, zero = 0;
		for (int i = 0; i <= second; i ++) {
			while (nums [i] == 2 && i < second) swap (nums, i, second --);
			while (nums [i] == 0 && i > zero) swap(nums, i, zero ++);
        }
        return nums;
    }
	
	public int[] sortColors(int[] nums) {
		int ze = -1, oe = -1, idx = 0;
        while (idx < nums.length) {
            if (nums [idx] == 0) {
                if (ze + 1 != idx) swap (nums, ze + 1, idx); else idx ++;
                ze ++;
            } else if (nums [idx] == 1) {
            	oe = ze < oe ? oe : ze;
                if (oe + 1 != idx) swap (nums, oe + 1, idx); else idx ++;
                oe ++;
            } else idx ++;
        }
        return nums;
    }
    
    private void swap (int[] nums, int from, int to) {
        int val = nums [from];
        nums [from] = nums [to];
        nums [to] = val;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, 1, 0 }, new int [] { 0, 1, 1 });
		_instance.runTest(new int [] { 1, 1 }, new int [] { 1, 1 });
		_instance.runTest(new int [] { 0, 1 }, new int [] { 0, 1 });
		_instance.runTest(new int [] { 1, 0 }, new int [] { 0, 1 });
		_instance.runTest(new int [] { 2, 1, 0 }, new int [] { 0, 1, 2 });
	}
	
	public void runTest(final int[] nums, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });
		
		for (Object answer : answers) 
			assertThat((int []) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
