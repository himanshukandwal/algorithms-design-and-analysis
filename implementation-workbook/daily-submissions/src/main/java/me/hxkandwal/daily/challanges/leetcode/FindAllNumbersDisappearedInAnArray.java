package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 448. Find All Numbers Disappeared in an Array
 * 
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once. Find all the elements 
 * of [1, n] inclusive that do not appear in this array.
 * 
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 * 
 * Example:
 * 		Input: 	[4,3,2,7,8,2,3,1]
 * 		Output: [5,6]
 * 
 * @author Hxkandwal
 */
@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class FindAllNumbersDisappearedInAnArray extends AbstractCustomTestRunner {
	
	private static FindAllNumbersDisappearedInAnArray _instance = new FindAllNumbersDisappearedInAnArray();
	
	private FindAllNumbersDisappearedInAnArray() {}
	
	// time complexity : O(nlogn)
    public static List<Integer> findDisappearedNumbers(int[] nums) {
    	List<Integer> ans = new ArrayList<>();
    	Arrays.sort(nums);
    	
    	int num1 = 0, num2 = -1;
    	for (int idx = 0; idx < nums.length; idx ++) { 
    		num2 = nums [idx];
			while (num2 - num1 > 1) ans.add(++ num1);
			num1 = nums [idx];
		}
    	
    	num2 = nums.length; 
    	while (num2 - num1 >= 1) ans.add(++ num1);  
    	
    	return ans;
    }
	
    // cuckoo hashing method.
    public static List<Integer> findDisappearedNumbersCuckoo(int[] nums) {
    	List<Integer> ans = new ArrayList<>();
    	
    	for (int idx = 0; idx < nums.length; idx ++) { 
    		int oIdx = nums [idx] - 1;
    		
    		while (oIdx >= 0 && nums [oIdx] >= 0) { 
    			int nIdx = nums [oIdx] - 1; 
    			nums [oIdx] = 0; 
    			oIdx = nIdx; 
    		}
		}
    	
    	for (int idx = 0; idx < nums.length; idx ++) 
    		if (nums [idx] != 0) ans.add(idx + 1);
    	
    	return ans;
    }
    
    /**
     * Negation method
     * 
     * Negate each number while traversing, Run again and find the index that is not negated.
     */
    public static List<Integer> _findDisappearedNumbersNegation(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        
        for (int i = 0; i < nums.length; i ++) {
            int index = nums [i];
            if (nums [Math.abs(index) - 1] > 0)  nums [Math.abs(index) - 1] = - nums [Math.abs(index) - 1];
        } 
        
        for (int j = 1; j <= nums.length; j ++)
            if (nums [j - 1] > 0) result.add (j);
        
        return result;
        
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 4, 3, 2, 7, 8, 2, 3, 1 }, new ArrayList() {{ add(5); add(6); }});
		_instance.runTest(new int[] { 1, 1 }, new ArrayList() {{ add(2); }});
		_instance.runTest(new int[] { 2, 2 }, new ArrayList() {{ add(1); }});
	}

	public void runTest(final int[] nums, final List<Integer> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((List<Integer>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}

}
