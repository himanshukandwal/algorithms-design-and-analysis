package challenges.leetcode;

import java.util.ArrayList;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 77. Combinations
 * 
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * 
 * For example,
 * 		If n = 4 and k = 2, a solution is:
 * 		[
 * 			[2,4],
 * 			[3,4],
 * 			[2,3],
 * 			[1,2],
 * 			[1,3],
 * 			[1,4],
 * 		]
 * 
 * @author Hxkandwal
 */
public class Combinations extends AbstractCustomTestRunner {
	
	// more optimal answer than boolean matrix one.
	public List<List<Integer>> _combineWithoutBooleanMatrix (int n, int k) {
    	List<List<Integer>> answer = new ArrayList<>();
    	combineWithoutBoolean (answer, new ArrayList<>(), n, k);
    	return answer;
    }
    
    private void combineWithoutBoolean (List<List<Integer>> answer, List<Integer> build, int n, int k) {
    	if (k == 0)
    		answer.add(new ArrayList<>(build));
    	else if (n >= k) {
    		combineWithoutBoolean(answer, build, n - 1, k);
    		build.add(n);
    		combineWithoutBoolean(answer, build, n - 1, k - 1);
    		build.remove(build.size() - 1);
    	}	
    }
    
    // another best.
    public List<List<Integer>> _combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs (ans, new ArrayList<>(), 1, n, k);
        return ans;
    }
    
    private void dfs (List<List<Integer>> ans, List<Integer> build, int start, int n, int k) {
        if (k == 0) ans.add (new ArrayList<>(build));
        else if (k <= n) {
            for (int idx = start; idx <= n; idx ++) {
                build.add (idx);
                dfs (ans, build, idx + 1, n, k - 1);
                build.remove (build.size() - 1);
            }
        }
    }
    
    // Balaji Sirs method. 
    public List<List<Integer>> combine (int n, int k) {
    	List<List<Integer>> answer = new ArrayList<>();
    	combine (answer, new boolean [n], n, k);
    	return answer;
    }
    
    private void combine (List<List<Integer>> answer, boolean [] seen, int n, int k) {
    	if (k == 0) {
    		List<Integer> response = new ArrayList<>();
    		for (int idx = 0; idx < seen.length; idx ++)
    			if (seen [idx]) response.add(idx + 1);
    		answer.add(response);
    	} else if (n >= k) {
    		combine(answer, seen, n - 1, k);
    		seen [n - 1] = true;
    		combine(answer, seen, n - 1, k - 1);
    		seen [n - 1] = false;
    	}	
    }
	
}
