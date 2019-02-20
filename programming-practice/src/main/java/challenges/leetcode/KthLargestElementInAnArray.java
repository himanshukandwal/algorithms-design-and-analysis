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
        return findKthLargest(nums, 0, nums.length - 1, k - 1);
    }

    private int findKthLargest(int[] nums, int i, int j, int k) {
        if (i >= j) return nums [i];
        int pi = j, p = nums [j];
        int s = i, e = j;
        while (s < e) {
            while (s < e && nums [s] > p) s ++;
            while (s < e && nums [e] <= p) e --;
            if (s < e) swap (nums, s, e);
        }
        swap (nums, pi, e);
        if (e > k) return findKthLargest(nums, i, e - 1, k);
        else if (e < k) return findKthLargest(nums, e + 1, j, k);
        else return nums [e];
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
