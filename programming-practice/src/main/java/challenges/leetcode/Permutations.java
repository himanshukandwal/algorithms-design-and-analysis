package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 46. Permutations
 * 
 * Given a collection of distinct numbers, return all possible permutations.
 * 
 * For example,
 *  	[1,2,3] have the following permutations:
 *  
 *  		[
 *  			[1,2,3],
 *  			[1,3,2],
 *  			[2,1,3],
 *  			[2,3,1],
 *  			[3,1,2],
 *  			[3,2,1]
 *  		]
 * 
 * @author Hxkandwal
 */
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class Permutations extends AbstractCustomTestRunner {
	
	private static Permutations _instance = new Permutations();
	
	private Permutations() {}

	/**
	 * *********************** FROM START *********************** 
	 */
	// juggle technique (from start)
    public List<List<Integer>> _permuteStart (int[] nums) {
    	List<List<Integer>> answer = new ArrayList<>();
    	if (nums != null && nums.length > 0) juggleStart(answer, nums, 0);
    	return answer;
    }
    
	private void juggleStart(List<List<Integer>> answer, int[] nums, int start) {
		if (start == nums.length) {
			List<Integer> response = new ArrayList<>();
			for (int num : nums) response.add(num);
			answer.add(response);
		} else {
			for (int idx = start; idx < nums.length; idx ++) {         // <<<<<<<<<<<<<<<<< catch here, we have to go to till start and initiate from 1.
				swap (nums, idx, start);
				juggleStart (answer, nums, start + 1);
				swap (nums, start, idx);
			}
		}
	}
	
	/**
	 * *********************** FROM END *********************** 
	 */
	
	// juggle technique (reverse)
    public List<List<Integer>> permute (int[] nums) {
    	List<List<Integer>> answer = new ArrayList<>();
    	if (nums != null && nums.length > 0) juggle(answer, nums, nums.length - 1);
    	return answer;
    }
    
	private void juggle(List<List<Integer>> answer, int[] nums, int start) {
		if (start == 0) {
			List<Integer> response = new ArrayList<>();
			for (int num : nums) response.add(num);
			answer.add(response);
		} else {
			for (int idx = 0; idx <= start; idx ++) {         // <<<<<<<<<<<<<<<<< catch here, we have to go to till start and initiate from 1.
				swap (nums, idx, start);
				juggle (answer, nums, start - 1);
				swap (nums, start, idx);
			}
		}
	}	
    
    private void swap (int [] nums, int from, int to) {
    	int t = nums [from];
    	nums [from] = nums [to];
    	nums [to] = t;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 2, 3 }, new ArrayList() {{ add (new ArrayList() {{ add (1); add (2); add (3); }});
												   					add (new ArrayList() {{ add (1); add (3); add (2); }});
												   					add (new ArrayList() {{ add (2); add (1); add (3); }});
												   					add (new ArrayList() {{ add (2); add (3); add (1); }});
												   					add (new ArrayList() {{ add (3); add (1); add (2); }});
												   					add (new ArrayList() {{ add (3); add (2); add (1); }}); }});
	}

	public void runTest(final int[] nums, final List<List<String>> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((List<List<String>>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}    
    
}
