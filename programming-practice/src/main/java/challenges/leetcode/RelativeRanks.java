package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 506. Relative Ranks
 * 
 * Given scores of N athletes, find their relative ranks and the people with the top three highest scores, who will be awarded medals: 
 * "Gold Medal", "Silver Medal" and "Bronze Medal".
 * 
 * Example 1:
 * 		Input: [5, 4, 3, 2, 1]
 * 		Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
 * 
 * 		Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and 
 * 					 "Bronze Medal".
 * 					 For the left two athletes, you just need to output their relative ranks according to their scores.
 * Note:
 * 		N is a positive integer and won't exceed 10,000.
 * 		All the scores of athletes are guaranteed to be unique.
 * 
 * @author Hxkandwal
 */
public class RelativeRanks extends AbstractCustomTestRunner {
	
	private static RelativeRanks _instance = new RelativeRanks();
	
	// Idea : Suffix array.
	public String[] _findRelativeRanks(int[] nums) {
        Integer [] index  = new Integer [nums.length];
        for (int idx = 0; idx < nums.length; idx ++) index [idx] = idx;
        Arrays.sort (index, (a, b) -> Integer.compare (nums [b], nums [a]));
        
        String[] ans = new String [nums.length];
        for (int idx = 0; idx < index.length; idx ++) {
            if (idx == 0) ans [index[idx]] = "Gold Medal";
            else if (idx == 1) ans [index[idx]] = "Silver Medal";
            else if (idx == 2) ans [index[idx]] = "Bronze Medal";
            else ans [index[idx]] = String.valueOf(idx + 1);
        }   
        return ans;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 5, 4, 3, 2, 1 }, new String[] { "Gold Medal", "Silver Medal", "Bronze Medal", "4", "5" });
		_instance.runTest(new int[] { 10, 3, 8, 9, 4 }, new String[] { "Gold Medal", "5", "Bronze Medal", "Silver Medal", "4" });
	}

	public void runTest(final int[] nums, final String[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((String[]) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
