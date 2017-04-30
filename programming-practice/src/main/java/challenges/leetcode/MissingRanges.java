package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 163. Missing Ranges
 * 
 * Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
 * 
 * For example, 
 * 		given [0, 1, 3, 50, 75], lower = 0 and upper = 99, 
 * 		return ["2", "4->49", "51->74", "76->99"].
 * 
 * @author Hxkandwal
 */
public class MissingRanges extends AbstractCustomTestRunner {
	
	private static MissingRanges _instance = new MissingRanges();

	public List<String> _findMissingRanges(int[] nums, int lower, int upper) {
		List<String> res = new ArrayList<>();
        for (int n : nums) {
            if (n == Integer.MIN_VALUE) {
                lower = n + 1; 
                continue;
            }
            if (lower == n - 1) res.add("" + lower);
            else if (lower < n - 1)   res.add(lower + "->" + (n - 1)); 
            if (n == Integer.MAX_VALUE)     return res; // added
            lower = n + 1;
        }
        if (lower == upper) res.add("" + lower);
        else if (lower < upper)   res.add(lower + "->" + upper);
        return res;
    }
	
	// solution without considering boundary level cases.
	public List<String> findMissingRanges(int[] nums, int lower, int upper) {
		List<String> list = new ArrayList<String>();
		for (int n : nums) {
			int justBelow = n - 1;
			if (lower == justBelow) list.add (lower + "");
			else if (lower < justBelow) list.add (lower + "->" + justBelow);
			lower = n + 1;
		}
		
		if (lower == upper) list.add (lower + "");
		else if (lower < upper) list.add (lower + "->" + upper);
		return list;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 0, 1, 3, 50, 75 }, 0, 99, Arrays.asList("2", "4->49", "51->74", "76->99"));
	}
	
	@SuppressWarnings("unchecked")
	public void runTest(final int[] nums, final int lower, final int upper, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums, lower, upper });
		
		for (Object answer : answers) 
			assertThat((List<String>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
