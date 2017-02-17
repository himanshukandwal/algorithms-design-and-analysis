package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 216. Combination Sum III
 * 
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and 
 * each combination should be a unique set of numbers.
 * 
 * Example 1:
 * 		Input: k = 3, n = 7
 * 		Output: [[1,2,4]]
 * 
 * Example 2:
 * 		Input: k = 3, n = 9
 * 		Output: [[1,2,6], [1,3,5], [2,3,4]]
 * 
 * @author Hxkandwal
 */
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class CombinationSumIII extends AbstractCustomTestRunner {
	
	private static CombinationSumIII _instance = new CombinationSumIII();
	
	private CombinationSumIII() {}

    public List<List<Integer>> _combinationSum3(int k, int n) {
        return combinationSum3Index(k, n, 1);
    }
    
    public List<List<Integer>> combinationSum3Index (int k, int n, int start) {
    	List<List<Integer>> answer = new ArrayList<>();
    	
    	if (k == 0) return answer;
    	for (int idx = start; idx <= 9; idx ++) {
			if (idx < n) {
				for (List<Integer> response : combinationSum3Index(k - 1, n - idx, idx + 1)) {
					response.add (idx);
					if (response.size() == k) answer.add(response);
				}
			} else if (idx == n) {
				List <Integer> response = new ArrayList<>();
				response.add(idx);
				if (response.size() == k) answer.add(response);      // <<<<<<<<<<<<<<<<< size check
			}
		}
    	
    	return answer;
    }
    
   	// driver method
   	public static void main(String[] args) {
		_instance.runTest(3, 7 , new ArrayList() {{  add (new ArrayList() {{ add(1); add(2); add(4); }}); }});
		_instance.runTest(3, 9 , new ArrayList() {{  add (new ArrayList() {{ add(1); add(2); add(6); }});
													 add (new ArrayList() {{ add(1); add(3); add(5); }});
													 add (new ArrayList() {{ add(2); add(3); add(4); }}); }});
   	}

   	public void runTest(final int k, final int n, final List<List<Integer>> expectedOutput) {
   		List<Object> answers = runAll(getClass(), new Object[] { k, n });

   		for (Object answer : answers)
   				assertThat((List<Integer>) answer).isEqualTo(expectedOutput);

   		System.out.println("ok!");
   	}
   	
}
