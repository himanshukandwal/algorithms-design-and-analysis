package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import challenges.AbstractCustomTestRunner;

/**
 * 88. Merge Sorted Array
 * 
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * 
 * Note:
 * 
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. 
 * 
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * 
 * @author Hxkandwal
 *
 */
public class MergeSortedArray extends AbstractCustomTestRunner {

	private static MergeSortedArray _instance = new MergeSortedArray();

	// method : awesome, backward sorting method.
    public void _merge(int[] nums1, int m, int[] nums2, int n) {
    	int i = m - 1;
		int j = n - 1;
		int k = m + n - 1;

		while (k >= 0) {
			if (j < 0 || (i >= 0 && nums1 [i] > nums2 [j]))
				nums1 [k--] = nums1[i--];
			else
				nums1 [k--] = nums2 [j--];
		}
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] {1}, 1, new int[] {}, 0, new int [] { 1 });
	}

	public void runTest(int[] nums1, int m, int[] nums2, int n, final int[] expectedOutput) {
		runAll(getClass(), new Object[] { nums1, m, nums2, n });
		assertThat((int[]) nums1).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
	
}
