package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 39. Combination Sum
 * 
 * Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * 
 * The same repeated number may be chosen from C unlimited number of times.
 * 
 * Note:
 * 		All numbers (including target) will be positive integers.
 * 		The solution set must not contain duplicate combinations.
 * 
 * For example, given candidate set [2, 3, 6, 7] and target 7,
 * A solution set is:
 * 		[
 *			[7],
 *			[2, 2, 3]
 *		]
 *
 * @author Hxkandwal
 */
public class CombinationSum extends AbstractCustomTestRunner {
	
	private static CombinationSum _instance = new CombinationSum();
	
    public List<List<Integer>> _combinationSum(int[] candidates, int target) {
    	Arrays.sort (candidates);
    	return combine (candidates, target, 0);
    }
    
    private List<List<Integer>> combine (int [] candidates, int target, int start) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int idx = start; idx < candidates.length; idx ++) {
            if (target < candidates [idx]) break;
            if (target == candidates [idx]) { 
                List<Integer> build = new ArrayList<> (); 
                build.add (candidates [idx]);
                ans.add (build);
                break;
            }  
            for (List<Integer> res : combine (candidates, target - candidates [idx], idx)) {
                res.add (candidates [idx]);
                ans.add (res);
            } 
        }
        return ans;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 2, 3, 6, 7 }, 7, Arrays.asList(Arrays.asList(3, 2, 2), Arrays.asList(7)));
	}

	@SuppressWarnings("unchecked")
	public void runTest(final int[] candidates, final int target, final List<List<Integer>> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { candidates, target });

		for (Object answer : answers)
				assertThat((List<Integer>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
	
}
