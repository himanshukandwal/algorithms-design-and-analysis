package challenges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

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
	
	/**
	 * Lets take an example : 123
	 * Intuition : build N using N - 1
	 * 
	 * 3 : generate answer for all domain values {3} 
	 * 		with 3		: 3
	 * 		without 3	: -
	 * 		Answer : {3 -}
	 * 
	 * 2 : for each domain value use all previous permutation.
	 * 		with 2	 	: 23 2- 
	 * 		without 2 	:  3 -
	 * 		Answer : {23 2- 3 -}
	 * 
	 * 1 : for each domain value use all previous permutation.
	 * 		with 1	 	: 23 2- 
	 * 		without 1 	:  3 -
	 * 		Answer : {123 12- 13 1- 23 2- 3 -}
	 * 
	 * Now for duplicates, it impacts the following N thats using answer of N - 1 
	 * 
	 * Lets take an example : BAA
	 * A : generate answer for all domain values {A}	
	 * 		with A		: A
	 * 		without A	: -
	 * 		Answer : {A -}
	 * 
	 * A : for each domain value use all previous permutation.
	 * 		with A	 	: AA  A- 
	 * 		without A 	:  A  -
	 * 		Answer : {AA  A- A -}
	 * 
	 * Here, A and A- are the same so these value will merge and result-set will form of values 3 instead of 4.
	 * 		So, the answer will be passed as 3 values to future rounds.
	 * 		what happened was, that we double the regular way, remove the all previous char pairings (4 - 1, 1 was the usual carry forward)
	 * 		Answer : {AA  A -}
	 * 
	 * B : for each domain value use all previous permutation.
	 * 		with B	 	: BAA  BA  B- 
	 * 		without B 	: AA  A  -
	 * 		Answer : {BAA  BA  B- AA A -}
	 * 	
	 * since B gets only 3 values to pair all its domain values so answer is 2 * 3 = 6, instead of 2 * 4 = 8 
	 * So, if we find that current character value has occurred before then its sure that there will one duplicate
	 * combination created the usual way (when with current char value and without prior char value and with prior 
	 * char value and without current char value). This result will then be propagated backwards till the first 
	 * character. (Bottom - up approach)
	 * 
	 * This approach will just give the answer, without printing actual string combinations.
	 */
	public static long _dpCountSub (String input) {
		long [] count = new long [input.length() + 1];
		count [input.length()] = 1;
		
		HashMap<Character, Integer> indexMap = new HashMap<>();
		
		for (int idx = input.length() - 1; idx >= 0; idx --) {
			char ch = input.charAt(idx);
			
			count [idx] = 2 * count [idx + 1];
			
			if (indexMap.containsKey(ch))
				count [idx] -= count [indexMap.get(ch) + 1]; // a forward char value, to get the effective pairs it made.
				
			indexMap.put(ch, idx);
		}	
	
		return count [0];
	}
	
	// method 1 : a regular recursive solution.
	public static long countSub(String input) {
		Set<String> distinctSequences = new HashSet<>();
		recurse(input, new boolean [input.length()], 0, distinctSequences);
		return distinctSequences.size();
	}
	
	private static void recurse (String input, boolean[] combination, int index, Set<String> distinctSequences) {
		// calling visit function in base case
		if (index >= input.length()) {
			distinctSequences.add(visit (input, combination));
			return;
		}
		
		combination [index] = false;
		recurse(input, combination, index + 1, distinctSequences);
		
		combination [index] = true;
		recurse(input, combination, index + 1, distinctSequences);
	}
	
	// visit function
	private static String visit(String input, boolean[] combination) {
		StringBuilder sb = new StringBuilder();
		
		for (int idx  = 0; idx  < combination.length; idx ++)
			if (combination [idx])
				sb.append(input.charAt(idx));
			
		return sb.toString();
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("gfg", 7);
		_instance.runTest("ggg", 4);
		_instance.runTest("abab", 12);
		_instance.runTest("babab", 20);
		_instance.runTest("ababab", 33);
		_instance.runTest("bababab", 54);
		_instance.runTest("abababababababababa", 17710);
		_instance.runTest("abababababababababaabababababababababa", 148099379);
	}

	public void runTest(final String input, final long expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((Long) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
