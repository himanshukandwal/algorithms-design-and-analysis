package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 90. Subsets II
 * 
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * For example,
 * 		If nums = [1,2,2], a solution is:
 * 			[
 * 			  [2],
 * 			  [1],
 * 			  [1,2,2],
 * 			  [2,2],
 * 			  [1,2],
 * 			  []
 * 			]
 * 
 * @author Hxkandwal
 */
@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class SubsetsII extends AbstractCustomTestRunner {
	
	private static SubsetsII _instance = new SubsetsII();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort (nums);
        combineIndex (answer, new ArrayList<>(), nums, 0);
        return answer;
    }
    
    private void combineIndex (List<List<Integer>> answer, List<Integer> build, int[] nums, int start) {
        answer.add (new ArrayList<>(build));
        for (int idx = start; idx < nums.length; idx ++) {
            if (idx == start || nums [idx] != nums [idx - 1]) {
               build.add (nums [idx]);
               combineIndex (answer, build, nums, idx + 1);
               build.remove (build.size() - 1);
            }
        }
    }
	
	// driver method	
	public static void main(String[] args) {
//		_instance.runTest(new int [] { 1 }, new ArrayList() {{ add(new ArrayList<Integer>()); 
//															   add(new ArrayList() {{ add(1); }}); }});
//		
		_instance.runTest(new int [] { 1, 2, 2 }, new ArrayList() {{ add(new ArrayList<Integer>()); 
																  	 add(new ArrayList() {{ add(1); }});
																  	 add(new ArrayList() {{ add(2); }});
																  	 add(new ArrayList() {{ add(1); add(2); }});
																  	 add(new ArrayList() {{ add(2); add(2); }});
																  	 add(new ArrayList() {{ add(1); add(2); add(2); }});
															   	  }});
	}

	public void runTest(final int[] nums, final List<List<Integer>> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((List<List<Integer>>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
}
