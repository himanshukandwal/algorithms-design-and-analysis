package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
        if (nums.length == 0) return ans;
        
        int start = lower, idx = 0;
        while (idx < nums.length) {
            if (start == nums [idx]) start ++; 
            else {
            	if (nums [idx] - 1 == start) ans.add(String.valueOf(start));
            	else ans.add(start + "->" + (nums [idx] - 1));
            	start = nums [idx] + 1;
            }
            idx ++;
        }
        
        if (start == upper - 1) ans.add(String.valueOf(start));
        else ans.add(start + "->" + (upper));
        return ans;
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
