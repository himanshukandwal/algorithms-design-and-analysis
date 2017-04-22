package challenges.interviewbit;

import java.util.ArrayList;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Kth Row of Pascal's Triangle
 * 
 * Given an index k, return the kth row of the Pascal’s triangle.
 * Pascal’s triangle : To generate A[C] in row R, sum up A’[C] and A’[C-1] from previous row R - 1.
 * 
 * Example:
 * 		Input : k = 3 Return : [1,3,3,1]
 * 
 * @author Hxkandwal
 */
public class KthRowOfPascalsTriangle extends AbstractCustomTestRunner {

	public List<Integer> getRow(int a) {
		List<Integer> ans = new ArrayList<Integer>();
	    ans.add (1);
	    for (int idx = 0; idx < a; idx ++) {
	    	List<Integer> next = new ArrayList<Integer>();
	        next.add (1);
	        for (int iidx = 0; iidx < ans.size() - 1; iidx ++)
	            next.add (ans.get (iidx) + ans.get (iidx + 1));
	        next.add (1);
	        ans = next;
	    }
	    return ans;
	}
	
}
