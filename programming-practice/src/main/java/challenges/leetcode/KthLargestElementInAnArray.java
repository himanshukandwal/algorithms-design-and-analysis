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
	
	public int _findKthLargest(int[] nums, int k) {
        return find (nums, k - 1, 0, nums.length - 1);
    }
    
    private int find (int [] nums, int k, int start, int end) {
        if (start >= end) return nums [start];
        
        int r = end, low = start, high = end - 1;
        while (low < high) {
            if (nums [low] > nums [r]) low ++;
            else swap (nums, low, high --);
        }
        r = low + (nums [low] > nums [r] ? 1 : 0);
        swap (nums, r, end);
        
        if (r > k)  return find (nums, k, start, r - 1);
        else if (r < k) return find (nums, k, r + 1, end);
        else return nums [r];
    }
    
    private void swap (int [] nums, int from, int to) {
        int num = nums [from];
        nums [from] = nums [to];
        nums [to] = num;
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
