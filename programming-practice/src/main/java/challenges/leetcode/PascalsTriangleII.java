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
        rowIndex ++;
        while (rowIndex -- > 0) {
            int size = ans.size();
            if (size >= 2)
                for (int idx = 1, prev = ans.get (0); idx < size; idx ++) {
                    int val = ans.get (idx);
                    ans.set (idx, prev + val);
                    prev = val;
                }
            ans.add (1);
        }
        return ans;
    }
}
