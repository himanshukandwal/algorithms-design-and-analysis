package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.*;

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

	// DP like approach
    public List<List<Integer>> _combinationSum(int[] candidates, int target) {
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        for (int c : candidates) {
            for (int s = 0; s <= target; s ++) {
                if (c == s) map.computeIfAbsent(s, k -> new ArrayList<List<Integer>>()).add(Arrays.asList(c));
                else if (c < s) {
                    if (map.containsKey(s - c)) {
                        for (List<Integer> l : map.get (s - c)) {
                            List<Integer> v = new ArrayList<Integer>(l);
                            v.add (c);
                            map.computeIfAbsent(s, k -> new ArrayList<List<Integer>>()).add(v);
                        }
                    }
                }
            }
        }
        return map.getOrDefault(target, Arrays.asList());
    }

    // DFS approach
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        dfs (candidates, ans, new ArrayList<>(), target, 0);
        return ans;
    }

    private void dfs (int[] candidates, List<List<Integer>> ans, List<Integer> build, int target, int start) {
        if (target < 0) return;
        if (target == 0) ans.add (new ArrayList(build));
        else {
            for (int idx = start; idx < candidates.length; idx ++) {
                if (idx == 0 || candidates [idx] != candidates [idx - 1]) {
                    build.add (candidates [idx]);
                    dfs (candidates, ans, build, target - candidates [idx], idx);
                    build.remove(build.size() - 1);
                }
            }
        }
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
