package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 228. Summary Ranges
 * 
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * 
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 * 
 * @author Hxkandwal
 */
public class SummaryRanges extends AbstractCustomTestRunner {
	
	private static SummaryRanges _instance = new SummaryRanges();
	
	public List<String> _summaryRanges(int[] nums) {
		List<String> ans = new ArrayList<String> ();
        for (int start = 0, end = 1; end <= nums.length; end ++) {
            if (end == nums.length || nums [end] != nums [end - 1] + 1) {
            	String suffix = (nums [start] != nums [end - 1]) ? "->" + (nums [end - 1]) : "";
            	ans.add(nums [start] + suffix);
            	start = end;
            }
        }
        return ans;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 0, 1, 2, 4, 5, 7 }, Arrays.asList("0->2", "4->5", "7"));
		_instance.runTest(new int[] { 1, 3 }, Arrays.asList("1", "3"));
	}
	
	@SuppressWarnings("unchecked")
	public void runTest(final int[] nums, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });
		
		for (Object answer : answers) 
			assertThat((List<String>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
