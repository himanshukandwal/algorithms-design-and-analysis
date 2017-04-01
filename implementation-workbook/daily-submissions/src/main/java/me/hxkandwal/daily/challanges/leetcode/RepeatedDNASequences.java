package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
		Set<String> seen = new HashSet<>(), repeated = new HashSet<>();
        for (int idx = 0; idx <= s.length() - 10; idx ++) {
            String ten = s.substring(idx, idx + 10);
            if (seen.contains(ten)) repeated.add (ten);
            else seen.add (ten);
        }
        return new ArrayList<String>(repeated);
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
