package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 60. Permutation Sequence
 * 
 * The set [1,2,3, ... ,n] contains a total of n! unique permutations. By listing and labeling all of the permutations 
 * in order, We get the following sequence (ie, for n = 3):
 * 			
 * 			"123"
 * 			"132"
 * 			"213"
 * 			"231"
 * 			"312"
 * 			"321"
 * 
 * Given n and k, return the kth permutation sequence.
 * 
 * Note: Given n will be between 1 and 9 inclusive.
 * 
 * @author Hxkandwal
 */
public class PermutationSequence extends AbstractCustomTestRunner {
	
	private static PermutationSequence _instance = new PermutationSequence();

	public String _getPermutation(int n, int k) {
        int [] fact = new int [10];
        fact [0] = 1;
        for (int idx = 1; idx < fact.length; idx ++) fact [idx] = fact [idx - 1] * idx;
        boolean [] nums = new boolean [n + 1];
        Arrays.fill (nums, true);
        return getPermutationInner (fact, nums, n, k);
    }
    
    public String getPermutationInner (int [] fact, boolean [] nums, int n, int k) {
        if (n == 0) return "";
        int val = fact [n - 1], idx = 1;
        while (val < k) { val += fact [n - 1]; idx ++; }
        k -= (val - fact [n - 1]);
        int i = 1;
        for (int j = 0; i < nums.length; i ++) if (nums [i] && ++ j == idx) break;
        nums [i] = false;
        
        return i + getPermutationInner (fact, nums, n - 1, k);
    }
    
    public String _getPermutationBetter(int n, int k) {
        int[] fact = new int [n + 1];
        fact[0] = 1;
        for (int idx = 1; idx < n; idx ++) fact [idx] = idx * fact [idx - 1];
        
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) numbers.add(i);
        
        StringBuilder sb = new StringBuilder();
        k--;
        
        for (int idx = n - 1; idx >= 0; idx --) {
            int index = k / fact [idx];
            sb.append(numbers.remove(index));
            k %= fact[idx];
        }
        
        return sb.toString();
        
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(3, 3, "213");
	}

	public void runTest(final int n, final int k, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n, k });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
    
}
