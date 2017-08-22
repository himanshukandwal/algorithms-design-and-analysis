package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 442. Find All Duplicates in an Array
 * 
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements that appear twice in this array.
 * 
 * Could you do it without extra space and in O(n) runtime?
 * 
 * Example:
 *  	Input: 	[4,3,2,7,8,2,3,1]
 *  	Output: [2,3]
 *  
 * @author Hxkandwal
 */
public class FindAllDuplicatesInAnArray extends AbstractCustomTestRunner {
	
	private static FindAllDuplicatesInAnArray _instance = new FindAllDuplicatesInAnArray();

	public List<Integer> _findDuplicates(int[] nums) {
		List<Integer> ans = new ArrayList<>();
		
    	for (int idx = 0; idx < nums.length; idx ++) {
    		int val = nums [idx];
    		if (nums [Math.abs (val) - 1] > 0) nums [Math.abs (val) - 1] = -nums [Math.abs (val) - 1];
    		else ans.add (Math.abs (val));
		}
        
    	return ans;
    }

    // find duplicates : swap, else like find missing elements : directly put. (Similar : set mismatch)
	public List<Integer> findDuplicates(int[] nums) {
		List<Integer> ans = new ArrayList<>();
		for (int idx = 0; idx < nums.length; idx ++) {
			int num = nums [idx];
			while (nums [num - 1] != num) {
				int val = nums [num - 1];
				nums [num - 1] = num;
				nums [idx] = val;
				num = val;
			}
		}
		for (int idx = 0; idx < nums.length; idx ++) if (nums [idx] != idx + 1) ans.add (nums [idx]);
		return ans;
	}
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 4, 3, 2, 7, 8, 2, 3, 1 }, Arrays.asList (2, 3));
	}

	public void runTest(final int[] nums, final List<Integer> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((List<Integer>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}    

}
