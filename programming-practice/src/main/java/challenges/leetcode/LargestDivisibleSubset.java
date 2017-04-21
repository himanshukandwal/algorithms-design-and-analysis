package challenges.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 368. Largest Divisible Subset
 * 
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this 
 * subset satisfies: Si % Sj = 0 or Sj % Si = 0.
 * 
 * If there are multiple solutions, return any subset is fine.
 * 
 * Example 1:
 * 		nums: [1,2,3]
 * 		Result: [1,2] (of course, [1,3] will also be ok)
 * 
 * Example 2:
 * 		nums: [1,2,4,8]
 * 		Result: [1,2,4,8]
 * 
 * @author Hxkandwal
 */
public class LargestDivisibleSubset extends AbstractCustomTestRunner {
	
	private static LargestDivisibleSubset _instance = new LargestDivisibleSubset();

	public List<Integer> largestDivisibleSubset(int[] nums) {
        Map <Integer, List<Integer>> map = new HashMap <>();
        for (int num : nums) {
            for (Integer key : map.keySet()) {
                if (num % key == 0 && !map.containsKey (num)) {
                    map.put (num, new ArrayList<> (map.get (key)));
                    map.get (num).add (num);
                }
            }
        }
        List<Integer> max = null;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet())
            if (max == null || max.size() < entry.getValue().size()) max = entry.getValue();
        return max;
    }
	
}
