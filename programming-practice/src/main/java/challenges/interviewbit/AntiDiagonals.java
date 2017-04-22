package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Anti Diagonals
 * 
 * Give a N*N square matrix, return an array of its anti-diagonals. Look at the example for more details.
 * 
 * Example:
 * 		Input:
 * 			1 2 3
 * 			4 5 6
 * 			7 8 9
 * 
 * 		Return the following :
 * 			[
 * 				[1],
 * 				[2, 4],
 * 				[3, 5, 7],
 * 				[6, 8],
 * 				[9]
 * 			]
 * 
 * Example:
 * 		Input:
 * 			1 2
 * 			3 4
 * 		
 * 		Return the following  :
 * 			[
 * 				[1],
 * 				[2, 3],
 * 				[4]
 * 			]
 *  
 * @author Hxkandwal
 */
public class AntiDiagonals extends AbstractCustomTestRunner {
	
	private static AntiDiagonals _instance = new AntiDiagonals();

	public List<List<Integer>> _diagonal(List<List<Integer>> a) {
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
	    int rows = a.size(), cols = a.get (0).size();
	    for (int idx = 0; idx < (rows + cols - 1); idx ++) {
	    	List<Integer> r = new  ArrayList<Integer> ();
	        if (idx < rows) {
	        	int row = 0, col = idx;
	        	while (row <= idx) r.add (a.get (row ++).get (col --));
	        } else {
	        	int row = (idx - rows + 1), col = cols - 1;
	        	while (row < rows) r.add (a.get (row ++).get (col --));
	        }
	        ans.add (r);
	    }
	    return ans;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4)), Arrays.asList(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4)));
	}

	@SuppressWarnings("unchecked")
	public void runTest(final List<List<Integer>> a, final List<List<Integer>> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a });

		for (Object answer : answers) {
			List<List<Integer>> ans = (List<List<Integer>>) answer;
			assertThat(ans.size()).isEqualTo(expectedOutput.size());
			for (int idx = 0; idx < ans.size(); idx ++) 
				assertThat((List<Integer>) ans.get(idx)).isEqualTo(expectedOutput.get(idx));
		}	

		System.out.println("ok!");
	}
	
}
