package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import challenges.AbstractCustomTestRunner;

/**
 * 187. Repeated DNA Sequences
 * 
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". 
 * When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 * 
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * 
 * For example,
 * 		Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 * 		Return:	["AAAAACCCCC", "CCCCCAAAAA"].
 * 
 * @author Hxkandwal
 */
public class RepeatedDNASequences extends AbstractCustomTestRunner {
	
	private static RepeatedDNASequences _instance = new RepeatedDNASequences();

	public List<String> _findRepeatedDnaSequences(String s) {
		Map<String, Integer> map = new HashMap<>();
	    for (int idx = 0; idx <= s.length() - 10; idx ++) {
	        String str = s.substring (idx, idx + 10);
	        map.put (str, map.getOrDefault (str, 0) + 1);
	    }
	    return map.entrySet().stream().filter (e -> e.getValue() > 1).map(e -> e.getKey()).sorted().collect(Collectors.toList());
	}
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT", Arrays.asList("AAAAACCCCC", "CCCCCAAAAA"));
	}
	
	@SuppressWarnings("unchecked")
	public void runTest(final String s, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });

		for (Object answer : answers)
			assertThat((List<String>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	    
    
}
