package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

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
        return findKthLargestInner (nums, 0, nums.length - 1, k - 1);
    }

    private int findKthLargestInner (int [] nums, int s, int e, int k) {
        if (s >= e) return nums [s];
        int l = s, r = e - 1, val = nums [e];
        while (l < r) {
            while (l < r && nums [l] > val) l ++;
            while (l < r && nums [r] <= val) r --;
            if (l < r) swap (nums, l, r);
        }
        if (nums [l] > val) l ++;
        swap (nums, l, e);

        if (l > k) return findKthLargestInner(nums, s, l - 1, k);
        if (l < k) return findKthLargestInner(nums, l + 1, e, k);
        return nums [l];
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
