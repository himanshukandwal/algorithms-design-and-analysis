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
        if (nums == null || nums.length == 0) return -1;
        return findKthLargestInner(nums, k - 1, 0, nums.length - 1);
    }

    private int findKthLargestInner(int[] nums, int k, int left, int  right) {
        if (left >= right) return nums [left];
        swap (nums, left + (right - left)/2, right);
        int l = left, r = right;
        while (l < r) {
            while (l < r && nums [l] > nums [right]) l ++;
            while (r > l && nums [r] <= nums [right]) r --;
            if (l < r) {
                swap (nums, l, r);
                l ++; r --;
            }
        }
        if (nums [r] > nums [right]) r ++;
        swap (nums, r, right);
        if (r > k) return findKthLargestInner(nums, k, left, r - 1);
        else if (r < k) return findKthLargestInner(nums, k, r + 1, right);
        return nums [r];
    }

    private void swap(int[] nums, int from, int to) {
        int temp = nums [from];
        nums [from] = nums [to];
        nums [to] = temp;
    }
    
   	// driver method
   	public static void main(String[] args) {
		_instance.runTest(new int[] { 3, 2, 1, 5, 6, 4 }, 2, 5);
   	}

   	public void runTest(final int[] nums, int k, final int expectedOutput) {
   		List<Object> answers = runAll(getClass(), new Object[] { nums, k });

   		for (Object answer : answers)
   				assertThat((Integer) answer).isEqualTo(expectedOutput);

   		System.out.println("ok!");
   	}
}
