package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 47. Permutations II
 * 
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * 
 * For example,
 * 		[1,1,2] have the following unique permutations:
 * 		
 * 			[
 * 				[1,1,2],
 * 				[1,2,1],
 * 				[2,1,1]
 * 			]
 * 
 * @author Hxkandwal
 */
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class PermutationsII extends AbstractCustomTestRunner {
	
	private static PermutationsII _instance = new PermutationsII();
	
	private PermutationsII() {}
	
	public List<List<Integer>> _permuteUnique(int[] nums) {
		List<List<Integer>> answer = new ArrayList<>();
		if (nums != null && nums.length > 0) {
			Arrays.sort (nums);
			permuteUniqueJuggle(answer, nums, 0);
		}
	    return answer;
    }
	
	private void permuteUniqueJuggle(List<List<Integer>> answer, int[] nums, int start) {
		if (start == nums.length) {
			List<Integer> response = new ArrayList<>();
			for (int num : nums) response.add(num);
			answer.add(response);
			System.out.println(response);
		} else {
			for (int idx = start; idx < nums.length; idx ++) {
				if (idx > start && nums [idx] != nums [idx - 1]) {
					swap (nums, idx, start);
					permuteUniqueJuggle (answer, nums, start + 1);
					swap (nums, start, idx);
				}
			}
		}
	}
	
	private void swap (int[] nums, int from, int to) {
		int t = nums [from];
		nums [from] = nums [to];
		nums [to] = t;
	}
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 1, 2 }, new ArrayList() {{ add (new ArrayList() {{ add (1); add (1); add (2); }});
												   					add (new ArrayList() {{ add (1); add (2); add (1); }});
												   					add (new ArrayList() {{ add (2); add (1); add (1); }}); }});
	}

	public void runTest(final int[] nums, final List<List<String>> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((List<List<String>>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}    
    
}
