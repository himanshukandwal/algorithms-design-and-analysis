package challenges.leetcode;

import java.util.ArrayList;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 119. Pascal's Triangle II
 * 
 * Given an index k, return the kth row of the Pascal's triangle.
 * 
 * For example, given k = 3, Return [1,3,3,1].
 * 
 * Note: Could you optimize your algorithm to use only O(k) extra space?
 * 
 * @author Hxkandwal
 */
public class PascalsTriangleII extends AbstractCustomTestRunner {

	public List<Integer> getRow (int rowIndex) {
		List<Integer> ans = new ArrayList<Integer>();
		ans.add (1);
		while (rowIndex -- > 0) {
			for (int idx = 1, val = ans.get (0); idx < ans.size (); idx ++) {
				int n = ans.get (idx);
				ans.set (idx, val + n);
				val = n;
			}
			ans.add (1);
		}
		return ans;
    }
	
}
