package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashSet;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 548. Split Array with Equal Sum
 * 
 * Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following 
 * conditions:
 * 
 * 	1.	0 < i, i + 1 < j, j + 1 < k < n - 1
 * 	2.	Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
 * 
 * where we define that subarray (L, R) represents a slice of the original array starting from the element indexed 
 * L to the element indexed R.
 * 
 * Example:
 * 		Input: [1,2,1,2,1,2,1]
 * 		Output: True
 * 		Explanation:
 * 			i = 1, j = 3, k = 5.
 * 			sum(0, i - 1) = sum(0, 0) = 1
 * 			sum(i + 1, j - 1) = sum(2, 2) = 1
 * 			sum(j + 1, k - 1) = sum(4, 4) = 1
 * 			sum(k + 1, n - 1) = sum(6, 6) = 1
 * 
 * Note:
 * 	-	1 <= n <= 2000.
 * 	-	Elements in the given array will be in range [-1,000,000, 1,000,000].
 * 
 * @author Hxkandwal
 */
public class SplitArrayWithEqualSum extends AbstractCustomTestRunner {
	
	private static SplitArrayWithEqualSum _instance = new SplitArrayWithEqualSum();

	public boolean _splitArray(int[] nums) {
		if (nums.length < 7) return false;
        int [] sums = new int [nums.length];
        sums [0] = nums [0];
        for (int idx = 1; idx < nums.length; idx ++) sums [idx] += sums [idx - 1] + nums [idx];
        
        for (int idx1 = 1; idx1 < nums.length - 5; idx1 ++) {
            if (idx1 == 1 || sums [idx1 - 2] != sums [idx1 - 1]) {
                int s1 = sums [idx1 - 1];
                for (int idx2 = idx1 + 2; idx2 < nums.length - 3; idx2 ++) {
                    int s2 = sums [idx2 - 1] - sums [idx1];    
                    if (s1 == s2) {
                        for (int idx3 = idx2 + 2; idx3 < nums.length - 1; idx3 ++) {
                            int s3 = sums [idx3 - 1] - sums [idx2];        
                            int s4 = sums [sums.length - 1] - sums [idx3];    
                            if (s2 == s3 && s3 == s4) return true;
                        }
                    }
                }
            }
        }
        return false;
    }
	
	public boolean _splitArrayUsingHashSet(int[] nums) {
        if (nums.length < 7) return false;
        int [] sum = new int [nums.length];
        sum [0] = nums [0];
        for (int i = 1; i < nums.length; i++) sum[i] = sum[i - 1] + nums[i];
        
		for (int j = 3; j < nums.length - 3; j ++) {
            HashSet < Integer > set = new HashSet<> ();
            for (int i = 1; i < j - 1; i++) if (sum[i - 1] == sum[j - 1] - sum[i]) set.add(sum[i - 1]);
            
            for (int k = j + 2; k < nums.length - 1; k++)
                if (sum[nums.length - 1] - sum[k] == sum[k - 1] - sum[j] && set.contains(sum[k - 1] - sum[j]))
                    return true;
        }
        return false;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 2, 1, 3, 0, 0, 2, 2, 1, 3, 3 }, true);
	}

	public void runTest(final int[] nums, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
}
