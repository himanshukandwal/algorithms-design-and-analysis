package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class CombinationSum extends AbstractCustomTestRunner {
	
	private static CombinationSum _instance = new CombinationSum();
	
	public CombinationSum() {}
	
    public List<List<Integer>> _combinationSum(int[] candidates, int target) {
    	List<List<Integer>> answer = new ArrayList<>();
    	
    	if (candidates != null && candidates.length > 0)
    		answer = combinationSumIndexed(candidates, target, 0);
    	
    	return answer;
    }
    
	private List<List<Integer>> combinationSumIndexed(int[] candidates, int target, int index) {
		List<List<Integer>> answer = new ArrayList<>();

		if (candidates != null && candidates.length > 0) {
			for (int idx = index; idx < candidates.length; idx ++) {
				if (candidates[idx] < target) {
					for (List<Integer> otherAns : combinationSumIndexed(candidates, target - candidates[idx], idx)) {
						otherAns.add(candidates[idx]);
						answer.add(otherAns);
					}
				} else if (candidates[idx] == target) {
					List<Integer> ans = new ArrayList<>();
					ans.add(candidates[idx]);
					answer.add(ans);
				}
			}
		}

		return answer;
	}
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 2, 3, 6, 7 }, 7, new ArrayList() {{  add (new ArrayList() {{ add(7); }});
																		   add (new ArrayList() {{ add(2); add(2); add(3); }});
																	   }});
	}

	public void runTest(final int[] candidates, final int target, final List<List<Integer>> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { candidates, target });

		for (Object answer : answers)
				assertThat((List<Integer>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
	
}
