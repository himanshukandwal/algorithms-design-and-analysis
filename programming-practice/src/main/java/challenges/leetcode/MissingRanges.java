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
        List<String> ans = new ArrayList<>();
        int min = lower;
        for (int num : nums) {
            if (num == Integer.MIN_VALUE) { min = num + 1; continue; }
            if (num != min && min <= upper)
                ans.add (min + (num - 1 > min ? "->" + (num - 1) : ""));
            if (num == Integer.MAX_VALUE) return ans;
            else min = num + 1;
        }
        if (min <= upper) ans.add (min + (upper > min ? "->" + upper : ""));
        return ans;
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
