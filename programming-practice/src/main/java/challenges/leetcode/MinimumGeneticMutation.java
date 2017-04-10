package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * 433. Minimum Genetic Mutation
 * 
 * A gene string can be represented by an 8-character long string, with choices from "A","C","G","T". 
 * Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation 
 * is defined as ONE single character changed in the gene string.
 * 
 * For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
 * 
 * Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make 
 * it a valid gene string. 
 * 
 * Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to 
 * mutate from "start" to "end". If there is no such a mutation, return -1. 
 * 
 * For example,
 * 
 * 		bank	: "AACCGGTA" 
 * 		start	: "AACCGGTT" 
 * 		end		: "AACCGGTA" 
 * 		return	: 1
 * 
 * 		bank	: "AACCGGTA", "AACCGCTA", "AAACGGTA"
 * 		start	: "AACCGGTT"
 * 		end		: "AAACGGTA"
 * 		return	: 2
 * 
 * 		bank	: "AAAACCCC", "AAACCCCC", "AACCCCCC"
 * 		start	: "AAAAACCC"
 * 		end		: "AACCCCCC"
 * 		return	: 3
 * 
 * @author Hxkandwal
 *
 */
public class MinimumGeneticMutation extends AbstractCustomTestRunner {
	
	private static MinimumGeneticMutation _instance = new MinimumGeneticMutation();
	
	private MinimumGeneticMutation() {}
	
	public int _minMutation(String start, String end, String[] bank) {
        if (bank.length == 0) return -1;
        Set<String> dict = new HashSet<>();
        for (String bi : bank) dict.add (bi);
        
        int mutations = 0;
        Queue<String> queue = new LinkedList<>();
        queue.add (start);
        Set<String> seen = new HashSet<>();
        
        while (!queue.isEmpty()) {
            int size = queue.size ();
            while (size -- > 0) {
                String word = queue.poll ();
                if (word.equals (end)) return mutations;
                char [] chArr = word.toCharArray ();
                
                seen.add (word);
                for (int idx = 0; idx < chArr.length; idx ++)
                    for (char c : "ACGT".toCharArray()) {
                        char old = chArr [idx];
                        chArr [idx] = c;
                        if (dict.contains (String.valueOf(chArr)) && !seen.contains(String.valueOf(chArr))) queue.offer (String.valueOf(chArr));
                        chArr [idx] = old;
                    }    
            }
            mutations ++;
        }
        return -1;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("AAAAACCC", "AACCCCCC", new String[] { "AAAACCCC", "AAACCCCC", "AACCCCCC" }, 3);
		_instance.runTest("AACCTTGG", "AATTCCGG", new String[] { "AATTCCGG", "AACCTGGG", "AACCCCGG", "AACCTACC" }, -1);
	}
	
	public void runTest(final String start, final String end, final String[] bank, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { start, end, bank });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
