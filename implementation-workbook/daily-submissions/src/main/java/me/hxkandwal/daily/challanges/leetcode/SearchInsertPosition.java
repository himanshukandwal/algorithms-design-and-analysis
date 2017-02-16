package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 35. Search Insert Position
 * 
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * 
 * You may assume no duplicates in the array.
 * 
 * Here are few examples.
 * 
 * 		[1,3,5,6], 5 → 2
 * 		[1,3,5,6], 2 → 1
 * 		[1,3,5,6], 7 → 4
 * 		[1,3,5,6], 0 → 0
 * 
 * @author Hxkandwal
 *
 */
public class SearchInsertPosition extends AbstractCustomTestRunner {
	
	private static SearchInsertPosition _instance = new SearchInsertPosition();
	
	private SearchInsertPosition() {}

    public int _searchInsert(int[] nums, int target) {
        int l = 0, h = nums.length - 1;
        
        while (l <= h) {
        	int m = (l + h) >>> 1;
        	if (nums [m] == target) return m;
        	else if (nums [m] > target) h = m - 1;
        	else l = m + 1;
        }
       
        return l;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, 3, 5, 6 }, 5, 2);
		_instance.runTest(new int [] { 1, 3, 5, 6 }, 2, 1);
		_instance.runTest(new int [] { 1, 3, 5, 6 }, 7, 4);
		_instance.runTest(new int [] { 1, 3, 5, 6 }, 0, 0);
	}

	public void runTest(final int[] nums, final int target, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums, target });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}    
    
}
