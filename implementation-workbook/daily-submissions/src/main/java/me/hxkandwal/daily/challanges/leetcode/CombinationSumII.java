package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class CombinationSumII extends AbstractCustomTestRunner {
	
	private static CombinationSumII _instance = new CombinationSumII();
	
	private CombinationSumII() {}
	
    public List<List<Integer>> _combinationSum2(int [] candidates, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        
        if (candidates != null && candidates.length != 0) {
        	Arrays.sort(candidates);                                      // <<<<<<<<<< sort it first, so that we can handle duplicates
        	answer = combinationSum2Indexed (candidates, target, 0);
        }
        
        return answer;
    }		
    
    public List<List<Integer>> combinationSum2Indexed (int [] candidates, int target, int index) {
    	List<List<Integer>> answer = new ArrayList<>();
    	
    	for (int idx = index; idx < candidates.length; idx ++) {
    		if (idx > index && (candidates [idx] == candidates [idx - 1])) continue; // <<<<<<<<<< stop processing duplicates
    		
    		if (candidates [idx] < target) {
    			for (List<Integer> response : combinationSum2Indexed (candidates, target - candidates [idx], idx + 1)) {
    				response.add(candidates [idx]);
    				answer.add (response);
	    		}
	    	} else if (candidates [idx] == target) {
	    		List<Integer> ansItem = new ArrayList<>();
	    		ansItem.add(candidates [idx]);
	    		answer.add(ansItem);
	    	}
    	}
    	
    	return answer;
    }
    
   	// driver method
   	public static void main(String[] args) {
		_instance.runTest(new int[] { 10, 1, 2, 7, 6, 1, 5 }, 8, new ArrayList() {{  add (new ArrayList() {{ add(1); add(1); add(6); }});
   																		   			 add (new ArrayList() {{ add(1); add(2); add(5); }});
   																		   			 add (new ArrayList() {{ add(1); add(7); }});
   																		   			 add (new ArrayList() {{ add(2); add(6); }});
																				 }});
   	}

   	public void runTest(final int[] candidates, final int target, final List<List<Integer>> expectedOutput) {
   		List<Object> answers = runAll(getClass(), new Object[] { candidates, target });

   		for (Object answer : answers)
   				assertThat((List<Integer>) answer).isEqualTo(expectedOutput);

   		System.out.println("ok!");
   	}	
   	
}
