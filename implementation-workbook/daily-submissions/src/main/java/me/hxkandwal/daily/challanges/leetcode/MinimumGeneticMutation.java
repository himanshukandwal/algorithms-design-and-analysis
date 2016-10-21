package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
	
	public enum GeneType {
		A, C, G, T;
		
		public static GeneType valueOf(char ch) {
			for (GeneType type : values())
				if (type.name().charAt(0) == ch)
					return type;
			return null;
		}
	}
	
	public static class GeneBank {
		GeneType type; 										// current address
		HashMap<GeneType, GeneBank> next = new HashMap<>(); // future addresses
		
		public GeneBank(GeneType type) {
			this.type = type;
		}
		
		@Override
		public String toString() {
			return "[" + (type == null ? "root" : type.name()) + "]";
		}
	}
	
	public static int _minMutation(String start, String end, String[] bank) {
		if (start.length() == 0)
			return 0;
			
		GeneBank root = new GeneBank (null);
		
		// build the bank tree
		for (String element : bank)
			insert(root, 0, element);
	
		StringBuilder sb = new StringBuilder(start);
		
		int count = 0;
		int conflictingIndex = -1;
		
		// initial alignment
		while ((conflictingIndex = find (root, sb, 0)) != -1) {
			count ++;
			sb.setCharAt(conflictingIndex, end.charAt(conflictingIndex));
		}
		
		while (!sb.equals(end)) {
			int idx = 0;
			for (; idx < sb.length(); idx ++)
				if (sb.charAt(idx) != end.charAt(idx))
					break;
			
			sb.setCharAt(idx, end.charAt(idx));
			count ++;
			
			while ((conflictingIndex = find (root, sb, 0)) != -1) {
				count ++;
				sb.setCharAt(conflictingIndex, end.charAt(conflictingIndex));
			}
		}
		
		return count;
	}
	
	public static int find (GeneBank bank, StringBuilder element, int index) {
		if (index >= element.length())
			return -1;
		
		GeneType geneType = GeneType.valueOf(element.charAt(index));
		
		if (!bank.next.containsKey(geneType))
			return index;
		else 
			return find (bank.next.get(geneType), element, index + 1);
	}
	
	// insert : handle the inner children.
	private static void insert(GeneBank bank, int index, String element) {
		if (index >= element.length())
			return;
		
		GeneType geneType = GeneType.valueOf (element.charAt(index));
		
		if (!bank.next.containsKey(geneType))
			bank.next.put(geneType, new GeneBank(geneType));
		
		insert (bank.next.get(geneType), index + 1, element);
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("AAAAACCC", "AACCCCCC", new String[] {"AAAACCCC", "AAACCCCC", "AACCCCCC"}, 3);
	}
	
	public void runTest(final String start, final String end, final String[] bank, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { start, end, bank });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
