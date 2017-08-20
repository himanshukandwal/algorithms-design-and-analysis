package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * 128. Longest Consecutive Sequence
 * 
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * 
 * For example, Given [100, 4, 200, 1, 3, 2],
 * 
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * 
 * Your algorithm should run in O(n) complexity.
 * 
 * @author Hxkandwal
 *
 */
public class LongestConsecutiveSequence extends AbstractCustomTestRunner {
	
	private static LongestConsecutiveSequence _instance = new LongestConsecutiveSequence();
	
	// expand window as much as possible, and find the max-size window, 
    public static int longestConsecutive(int[] nums) {
    	if (nums.length == 0) return 0;
        Set<Integer> set = new HashSet <>();
        int max = 1;
        
        for (int num : nums) set.add (num);
        for (int num : nums) {
            int left = num - 1, right = num + 1, count = 1;
            // eat till extreme left
            while (set.contains (left)) { count ++; set.remove (left --); }
            // eat till extreme right
            while (set.contains (right)) { count ++; set.remove (right ++); }
            max = Math.max (max, count);
        }
        return max;
    }
    
    public int _longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, 1);
        int max = 0;
        for (int num : nums) {
            if (map.containsKey (num)) {
                int l = num - 1, r = num + 1;
                while (map.containsKey (l)) { map.remove (l); map.put (num, map.get (num) + 1);  l--; }
                while (map.containsKey (r)) { map.remove (r); map.put (num, map.get (num) + 1);  r++; }
                max = Math.max (max, map.get (num));
            }
        }
        return max;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 100, 4, 200, 1, 3, 2 }, 4);
	}
	
	public void runTest(final int[] nums, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
		
}
