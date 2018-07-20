package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 321. Create Maximum Number
 * 
 * Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. 
 * The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and 
 * space complexity.
 * 
 * Example 1:
 * 		nums1 = [3, 4, 6, 5]
 * 		nums2 = [9, 1, 2, 5, 8, 3]
 * 		k = 5
 * 
 * 		return [9, 8, 6, 5, 3]
 * 
 * Example 2:
 * 		nums1 = [6, 7]
 * 		nums2 = [6, 0, 4]
 * 		k = 5
 * 
 * 		return [6, 7, 6, 0, 4]
 * 
 * Example 3:
 * 		nums1 = [3, 9]
 * 		nums2 = [8, 9]
 * 		k = 3
 * 
 * 		return [9, 8, 9]
 *  
 * @author Hxkandwal
 * 
 */
public class CreateMaximumNumber extends AbstractCustomTestRunner {
	
	private static CreateMaximumNumber _instance = new CreateMaximumNumber();
	
	public int[] _maxNumber(int[] nums1, int[] nums2, int k) {
		int n = nums1.length;
	    int m = nums2.length;
	    int[] ans = new int[k];
	    for (int i = Math.max(0, k - m); i <= k && i <= n; ++i) {
	        int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, k - i));
	        if (greater(candidate, 0, ans, 0)) ans = candidate;
	    }
	    return ans;
	}
	
	private int[] merge(int[] nums1, int[] nums2) {
	    int[] ans = new int[nums1.length + nums2.length];
	    for (int i = 0, j = 0, r = 0; r < ans.length; ++r)
	        ans[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
	    return ans;
	}
	
	public boolean greater(int[] nums1, int i, int[] nums2, int j) {
		// tie breaking logic
	    while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
	        i++;
	        j++;
	    }
	    // either we have exhausted j (and its equally same as i throughout) or if its present and its smaller, so i should proceed else j.
	    return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
	}
	
	public int[] maxArray(int[] nums, int k) {
	    int n = nums.length;
	    int[] ans = new int[k];
	    for (int i = 0, j = 0; i < n; ++i) {
			// ElementsToFill = (k - j - 1), ElementsToOffer = (n - i - 1)
			// here toOffer has to be > toFill as we are re claiming/filling current (already filled a[j] position), and need more, if its equal we cannot decrement j anymore.
	        while (n - i - 1 > k - j - 1 && j > 0 && ans[j - 1] < nums[i]) j--;
	        if (j < k) ans[j++] = nums[i];
	    }
	    return ans;
	}

	// other approach, using sorting based on index positions.
	public int[] _maxNumberOther(int[] nums1, int[] nums2, int k) {
		Integer[] i1 = new Integer[nums1.length], i2 = new Integer[nums2.length];

		for (int idx = 0; idx < nums1.length; idx ++) i1 [idx] = idx;
		for (int idx = 0; idx < nums2.length; idx ++) i2 [idx] = idx;

		Arrays.sort(i1, (x, y) -> nums1 [y] - nums1 [x]);
		Arrays.sort(i2, (x, y) -> nums2 [y] - nums2 [x]);

		int [] ans = null;
		int n = nums1.length, m = nums2.length;
		for (int ki = Math.max(0, k - m); ki <= k && ki <= n; ki ++) {
			int [] v = merge (maxNum(nums1, i1, ki), maxNum(nums2, i2, k - ki));
			if (ans == null || greater(v, 0, ans, 0)) ans = v;
		}
		return ans;
	}

	private int[] maxNum(int[] a, Integer [] i, int l) {
		int [] v = new int [l];
		int vIdx = 0, seeAfter = -1;
		while (vIdx < v.length) {
			for (int idx = 0; idx < i.length; idx ++) {
				if (seeAfter >= i [idx]) continue;

				int toFill = v.length - vIdx - 1;
				int toOffer = i.length - i [idx] - 1;
				if (toFill <= toOffer) {
					seeAfter = i [idx];
					v [vIdx ++] = a [i [idx]];
					break;
				}
			}
		}
		return v;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 6, 7 }, new int [] { 6, 0, 4 }, 5, new int [] { 6, 7, 6, 0, 4 });
		_instance.runTest(new int [] { 3, 4, 6, 5 }, new int [] { 9, 1, 2, 5, 8, 3 }, 5, new int [] { 9, 8, 6, 5, 3 });
	}

	public void runTest(final int[] nums1, final int[] nums2, final int k, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums1, nums2, k });

		for (Object answer : answers)
			assertThat((int[]) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
