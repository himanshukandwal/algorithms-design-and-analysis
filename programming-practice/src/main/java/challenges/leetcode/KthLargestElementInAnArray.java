package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 215. Kth Largest Element in an Array
 * 
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth 
 * distinct element.
 * 
 * For example,
 * 		Given [3,2,1,5,6,4] and k = 2, return 5.
 * 
 * Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
 * 
 * @author Hxkandwal
 */
public class KthLargestElementInAnArray extends AbstractCustomTestRunner {
	
	private static KthLargestElementInAnArray _instance = new KthLargestElementInAnArray();
	
	private KthLargestElementInAnArray() {}
	
    public int _findKthLargest(int[] nums, int k) {
    	return quickFind (nums, k - 1, 0, nums.length - 1);
    }
    
    public int quickFind (int[] nums, int k, int start, int end) {
    	if (start >= end) return nums [start];
    	
    	// get random pivot.
    	int pivotIdx = (start + end) >>> 1;
    	swap(nums, pivotIdx, end);
    	
    	int left = start, right = end - 1;
    	while (left < right) {
    		if (nums [left] > nums [end]) left ++;
    		else { swap (nums, left, right); right --; }
    	}
    	if (nums [right] <= nums [end]) swap (nums, right, end);
    	else swap (nums, ++ right, end);
    	
    	if (right == k) return nums [right];
    	else if (right > k) return quickFind (nums, k, start, right - 1);
    	else return quickFind (nums, k, right + 1, end);
    }
    
    private void swap (int [] nums, int from, int to) {
    	int temp = nums [from];
    	nums [from] = nums [to];
    	nums [to] = temp;
    }
    
   	// driver method
   	public static void main(String[] args) {
   		_instance.runTest(new int[] { 1 }, 1, 1);
   		_instance.runTest(new int[] { 1, 1, 2 }, 2, 1);
   		_instance.runTest(new int[] { 2, 1 }, 2, 1);
   		_instance.runTest(new int[] { -1, 2, 0 }, 2, 0);
		_instance.runTest(new int[] { 3, 2, 1, 5, 6, 4 }, 2, 5);
   	}

   	public void runTest(final int[] nums, int k, final int expectedOutput) {
   		List<Object> answers = runAll(getClass(), new Object[] { nums, k });

   		for (Object answer : answers)
   				assertThat((Integer) answer).isEqualTo(expectedOutput);

   		System.out.println("ok!");
   	}
}
