package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 40. Combination Sum II
 * 
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * 
 * Each number in C may only be used once in the combination.
 * 
 * Note:
 * 		All numbers (including target) will be positive integers.
 * 		The solution set must not contain duplicate combinations.
 * 
 * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
 * 
 * A solution set is:
 * 		[
 * 			[1, 7],
 * 			[1, 2, 5],
 * 			[2, 6],
 * 			[1, 1, 6]
 * 		]
 * 
 * @author Hxkandwal
 */
public class CombinationSumII extends AbstractCustomTestRunner {
	
	private static CombinationSumII _instance = new CombinationSumII();
	
    public List<List<Integer>> _combinationSum2(int [] candidates, int target) {
    	Arrays.sort (candidates);
    	return combine (candidates, target, 0);
    }
    
    private List<List<Integer>> combine (int [] candidates, int target, int start) {
        List<List<Integer>> ans = new ArrayList<>();
        
        for (int idx = start; idx < candidates.length; idx ++) {
            if (idx > start && candidates [idx] == candidates [idx - 1]) continue;
            if (target < candidates [idx]) break;
            if (target == candidates [idx]) { 
                List<Integer> build = new ArrayList<> (); 
                build.add (candidates [idx]);
                ans.add (build);
                break;
            }  
            for (List<Integer> res : combine (candidates, target - candidates [idx], idx + 1)) {
                res.add (candidates [idx]);
                ans.add (res);
            } 
        }
        return ans;
    }
    
   	// driver method
   	public static void main(String[] args) {
		_instance.runTest(new int[] { 10, 1, 2, 7, 6, 1, 5 }, 8, Arrays.asList(Arrays.asList(6, 1, 1), Arrays.asList(5, 2, 1), 
				Arrays.asList(7, 1), Arrays.asList(6, 2)));
   	}

   	@SuppressWarnings("unchecked")
   	public void runTest(final int[] candidates, final int target, final List<List<Integer>> expectedOutput) {
   		List<Object> answers = runAll(getClass(), new Object[] { candidates, target });

   		for (Object answer : answers)
   				assertThat((List<Integer>) answer).isEqualTo(expectedOutput);

   		System.out.println("ok!");
   	}	
   	
}
