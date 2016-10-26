package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Count Distinct Subsequences
 * 
 * Given a string, find count of distinct subsequences of it.
 * 
 * Examples:
 * a)		Input  : str = "gfg"
 * 			Output : 7
 * 			
 * 			The seven distinct subsequences are "", "g", "f", "gf", "fg", "gg" and "gfg" 
 * 
 * b)		Input  : str = "ggg"
 * 			Output : 4
 * 			
 * 			The four distinct subsequences are "", "g", "gg" and "ggg"  
 * 
 * link: http://www.geeksforgeeks.org/count-distinct-subsequences/
 * 
 * @author Hxkandwal
 *
 */
public class CountDistinctSubsequences extends AbstractCustomTestRunner {
	
	private static CountDistinctSubsequences _instance = new CountDistinctSubsequences();
	
	private CountDistinctSubsequences() {}
	
	// method 1 : a regular recursive solution.
	public static int _countSub(String input) {
		Set<String> distinctSequences = new HashSet<>();
		recurse(input, new boolean [input.length()], 0, distinctSequences);
		System.out.println(" >>> distinctSequences : " + distinctSequences);
		
		return distinctSequences.size();
	}
	
	private static void recurse (String input, boolean[] permutation, int index, Set<String> distinctSequences) {
		// calling visit function in base case
		if (index >= input.length()) {
			distinctSequences.add(visit (input, permutation));
			return;
		}
		
		permutation[index] = false;
		recurse(input, permutation, index + 1, distinctSequences);
		permutation[index] = true;
		recurse(input, permutation, index + 1, distinctSequences);
	}
	
	// visit function
	private static String visit(String input, boolean[] permutation) {
		StringBuilder sb = new StringBuilder();
		
		for (int idx  = 0; idx  < permutation.length; idx ++)
			if (permutation[idx])
				sb.append(input.charAt(idx));
			
		return sb.toString();
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("gfg", 7);
		_instance.runTest("ggg", 4);
	}

	public void runTest(final String input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
