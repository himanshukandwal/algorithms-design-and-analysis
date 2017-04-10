package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 229. Majority Element II
 * 
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
 * 
 * The algorithm should run in linear time and in O(1)
 * 
 * @author Hxkandwal
 */
@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class MajorityElementII extends AbstractCustomTestRunner {
	
	private static MajorityElementII _instance = new MajorityElementII();
	
	private MajorityElementII() {}
	
	// Boyer-Moore algorithm (advance version, two majorities is only possible) https://gregable.com/2013/10/majority-vote-algorithm-find-majority.html 
	public List<Integer> _majorityElementBM(int[] nums) {
		List<Integer> answer = new ArrayList<>();
		int count1 = 0, count2 = 0, majority1 = 0, majority2 = 0;
		for (int num : nums) {
			if (num == majority1) count1 ++;
			else if (num == majority2) count2 ++;
			else if (count1 == 0) { majority1 = num; count1 ++; }
			else if (count2 == 0) { majority2 = num; count2 ++; }
			else  { count1 --; count2 --; }
		}
		count1 = count2 = 0;
		for (int num : nums) {
			if (num == majority1) count1 ++;
			if (num == majority2) count2 ++;
		}
		if (count1 > 0 && count1 > nums.length / 3) answer.add(majority1); 
		if (count2 > 0 && majority1 != majority2 && count2 > nums.length / 3) answer.add(majority2);
    	return answer;
    }
	
	// O(n) solution with O(n) memory
    public List<Integer> _majorityElement(int[] nums) {
        List<Integer> answer = new ArrayList<>();
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	for (int num : nums)
    		if (map.containsKey(num)) map.put(num, map.get(num) + 1);
    		else map.put(num, 1);
    	
    	for (Map.Entry<Integer, Integer> entry : map.entrySet())
    		if (entry.getValue() > nums.length/3) answer.add(entry.getKey());
    	
    	return answer;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 1, 1, 1, 2, 2, 2, 2, 3 }, new ArrayList() {{ add (1); add(2); }});
	}

	public void runTest(final int[] nums, final List<Integer> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((List<Integer>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
