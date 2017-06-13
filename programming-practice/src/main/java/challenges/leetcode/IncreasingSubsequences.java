package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * 491. Increasing Subsequences
 * 
 * Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the 
 * length of an increasing subsequence should be at least 2.
 * 
 * Example:
 * 		Input: [4, 6, 7, 7]
 * 		Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * 
 * Note:
 * 	-	The length of the given array will not exceed 15.
 * 	-	The range of integer in the given array is [-100,100].
 * 	-	The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing 
 * 		sequence.
 * 
 * @author Hxkandwal
 */
public class IncreasingSubsequences extends AbstractCustomTestRunner {
	
	private static IncreasingSubsequences _instance = new IncreasingSubsequences();

	// iterative solution
	public List<List<Integer>> _findSubsequences(int[] nums) {
		List<List<List<Integer>>> dp = new ArrayList<>();
        for (int idx = 0; idx < nums.length; idx ++) {
        	List<List<Integer>> list = new ArrayList<> ();
        	list.add(Arrays.asList(nums [idx]));
        	dp.add (list);
        }
        
        for (int idx = 1; idx < nums.length; idx ++)
            for (int jdx = 0; jdx < idx; jdx ++)
                if (nums [idx] >= nums [jdx]) {
                	if (nums [idx] == nums [jdx]) dp.get (idx).clear();
                    for (List<Integer> list : dp.get (jdx)) {
                        List<Integer> clone = new ArrayList<> (list);
                        clone.add (nums [idx]);
                        dp.get (idx).add (clone);
                    }
                }
        
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int idx = 0; idx < nums.length; idx ++) for (List<Integer> li : dp.get(idx)) if (li.size() > 1) ans.add (li);
        return ans;
    }
	
	// dfs solution
	public List<List<Integer>> findSubsequencesDFS(int[] nums) {
        Set<List<Integer>> ans = new HashSet<List<Integer>> ();
        dfs (ans, new ArrayList<>(), nums, 0);
        return new ArrayList<> (ans);
    }
    
    public void dfs (Set<List<Integer>> ans, List<Integer> build, int [] nums, int index) {
        if (build.size() > 1) ans.add (new ArrayList<> (build));
        for (int idx = index; idx < nums.length; idx ++) {
            if (build.size() == 0 || build.get (build.size() - 1) <= nums [idx]) {
                build.add (nums [idx]);
                dfs (ans, build, nums, idx + 1);
                build.remove (build.size() - 1);
            }
        }
    }

   	// driver method
   	public static void main(String[] args) {
   		_instance.runTest(new int[] { 4, 6, 7, 7 }, Arrays.asList(Arrays.asList(4, 6), Arrays.asList(4, 7),  Arrays.asList(6, 7),Arrays.asList(4, 6, 7), 
   																  Arrays.asList(7, 7), Arrays.asList(4, 7, 7), Arrays.asList(6, 7, 7), Arrays.asList(4, 6, 7, 7)));
   	}

   	@SuppressWarnings("unchecked")
	public void runTest(final int[] nums, final List<List<Integer>> expectedOutput) {
   		List<Object> answers = runAll(getClass(), new Object[] { nums });

   		for (Object answer : answers)
   				assertThat((List<List<Integer>>) answer).isEqualTo(expectedOutput);

   		System.out.println("ok!");
   	}
   	
}
