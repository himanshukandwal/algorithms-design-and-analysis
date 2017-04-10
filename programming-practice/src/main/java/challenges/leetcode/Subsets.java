package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 78. Subsets
 * 
 * Given a set of distinct integers, nums, return all possible subsets. Note: The solution set must not contain duplicate subsets.
 * 
 * For example,
 * 		If nums = [1,2,3], a solution is:
 * 		[
 * 		  [3],
 * 		  [1],
 * 		  [2],
 * 		  [1,2,3],
 * 		  [1,3],
 * 		  [2,3],
 *		  [1,2],
 *		  []
 *		]
 * 
 * @author Hxkandwal
 */
@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class Subsets extends AbstractCustomTestRunner {

	private static Subsets _instance = new Subsets();
	
	private Subsets() {}
	
	// most efficient.
	public List<List<Integer>> _subsets(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        combineIndex (answer, new ArrayList<>(), nums, 0);
        return answer;
    }
    
    private void combineIndex (List<List<Integer>> answer, List<Integer> build, int[] nums, int start) {
        answer.add (new ArrayList<>(build));
        for (int idx = start; idx < nums.length; idx ++) {
               build.add (nums [idx]);
               combineIndex (answer, build, nums, idx + 1);
               build.remove (build.size() - 1);
        }
    }
    
    public List<List<Integer>> subsets(int[] nums) {
    	List<List<Integer>> answer = new ArrayList<>();
    	for (int k = 0; k <= nums.length; k ++) {
    		boolean [] seen = new boolean [nums.length];
    		
			// seek combinations.
        	combine (nums, seen, nums.length - 1, k, answer);
		}
    	
    	return answer;
    }

	private void combine(int[] nums, boolean[] seen, int n, int k, List<List<Integer>> answer) {
		if (k == 0) {
		    List<Integer> output = new ArrayList<>();
			for (int idx = 0; idx < seen.length; idx ++) 
			    if (seen [idx]) output.add (nums [idx]);
			answer.add (output);
		} else if (n + 1 >= k) {
		       combine(nums, seen, n - 1, k, answer);
		       seen [n] = true;
		       combine(nums, seen, n - 1, k - 1, answer);
		       seen [n] = false;
		}
	}

	/**
	 * Bit manipulation
	 * 
	 * Number of subsets for {1 , 2 , 3 } = 2^3 . why ?
	 * case    		possible outcomes for the set of subsets
	 * 	1   ->          Take or dont take = 2
	 * 	2   ->          Take or dont take = 2
	 * 	3   ->          Take or dont take = 2
	 * 
	 * therefore , total = 2*2*2 = 2^3 = { { } , {1} , {2} , {3} , {1,2} , {1,3} , {2,3} , {1,2,3} }
	 * 
	 * Lets assign bits to each outcome  -> First bit to 1 , Second bit to 2 and third bit to 3
	 * Take = 1
	 * Dont take = 0
	 * 
	 * 0) 0 0 0  -> Dont take 3 , Dont take 2 , Dont take 1 = { }
	 * 1) 0 0 1  -> Dont take 3 , Dont take 2 ,   take 1       =  {1 }
	 * 2) 0 1 0  -> Dont take 3 ,    take 2       , Dont take 1 = { 2 }
	 * 3) 0 1 1  -> Dont take 3 ,    take 2       ,      take 1    = { 1 , 2 }
	 * 4) 1 0 0  ->    take 3      , Dont take 2  , Dont take 1 = { 3 }
	 * 5) 1 0 1  ->    take 3      , Dont take 2  ,     take 1     = { 1 , 3 }
	 * 6) 1 1 0  ->    take 3      ,    take 2       , Dont take 1 = { 2 , 3 }
	 * 7) 1 1 1  ->    take 3     ,      take 2     ,      take 1     = { 1 , 2 , 3 }
	 * 
	 * In the above logic ,Insert S[i] only if ( j >> i) & 1 == true  { j E { 0,1,2,3,4,5,6,7 }   i = ith element in the input array }
	 * element 1 is inserted only into those places where 1st bit of j is 1
	 * 
	 * if( j >> 0 &1 )  ==> for above above eg. this is true for sl.no.( j )= 1 , 3 , 5 , 7
	 * element 2 is inserted only into those places where 2nd bit of j is 1
	 * 
	 * if( j >> 1 &1 )  == for above above eg. this is true for sl.no.( j ) = 2 , 3 , 6 , 7
	 * element 3 is inserted only into those places where 3rd bit of j is 1
	 * 
	 * if( j >> 2 & 1 )  == for above above eg. this is true for sl.no.( j ) = 4 , 5 , 6 , 7
	 * 
	 * Time complexity : O(n*2^n) , for every input element loop traverses the whole solution set length i.e. 2^n
	 * 
	 */
	public List<List<Integer>> _subsetsBitManipulation(int[] S) {
		Arrays.sort(S);
		int totalNumber = 1 << S.length;
		
		List<List<Integer>> collection = new ArrayList<List<Integer>>(totalNumber);
		
		for (int i = 0; i < totalNumber; i ++) {
			List<Integer> set = new LinkedList<Integer>();
			
			for (int j = 0; j < S.length; j++)
				if ((i & (1 << j)) != 0) set.add(S[j]);
			
			collection.add(set);
		}
		
		return collection;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1 }, new ArrayList() {{ add(new ArrayList<Integer>()); add(new ArrayList() {{ add(1); }}); }});
		_instance.runTest(new int [] { 1, 2 }, new ArrayList() {{ add(new ArrayList<Integer>()); 
																  add(new ArrayList() {{ add(1); }});
																  add(new ArrayList() {{ add(2); }});
																  add(new ArrayList() {{ add(1); add(2); }});
															   }});
	}

	public void runTest(final int[] nums, final List<List<Integer>> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((List<List<Integer>>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
    
}
