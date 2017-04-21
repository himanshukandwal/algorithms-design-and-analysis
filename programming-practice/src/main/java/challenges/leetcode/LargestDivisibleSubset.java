package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
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

	public List<Integer> _largestDivisibleSubset(int[] nums) {
		if (nums.length == 0) return new ArrayList<>();
        Arrays.sort(nums);
        Map <Integer, List<Integer>> map = new HashMap <>();
        for (int num : nums) {
        	Integer copyKey = null;
            for (Integer key : map.keySet())
                if (num % key == 0) 
                    if (copyKey == null || map.get (copyKey).size() < map.get (key).size()) copyKey = key;
                    
            map.put (num, copyKey != null ? new ArrayList<> (map.get (copyKey)) : new ArrayList<>());
			map.get (num).add (num);
        }
        
        List<Integer> max = null;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet())
            if (max == null || max.size() < entry.getValue().size()) max = entry.getValue();
        return max;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, 2, 3 }, Arrays.asList(1, 2));
	}

	@SuppressWarnings("unchecked")
	public void runTest(final int[] nums, final List<Integer> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
				assertThat((List<Integer>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	} 
		
}
