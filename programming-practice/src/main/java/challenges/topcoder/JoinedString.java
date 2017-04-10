package challenges.topcoder;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Joined String
 * 
 * You are given a String[] words. Return the shortest String that contains all the words as substrings. 
 * If there are several possible answers, return the one that comes first lexicographically.
 * 
 * Constraints:
 *	-	words will contain between 1 and 12 elements, inclusive.
 *	-	Each element of words will contain between 1 and 50 characters, inclusive.
 *	-	Each element of words will consist of only upper-case letters ('A'-'Z').

 * link: https://community.topcoder.com/stat?c=problem_statement&pm=6215
 * 
 * @author Hxkandwal
 */
public class JoinedString extends AbstractCustomTestRunner {
	
	private static JoinedString _instance = new JoinedString();
	
	private JoinedString() {}

	public static class StringIterator {
		
		private String input;
		private int idx;
		
		public StringIterator(String input) {
			this.input = input;
		}
		
		public char next() {
			return input.charAt(idx ++);
		}
		
		public char peek() {
			return input.charAt(idx);
		}
		
		public boolean hasNext() {
			return !(idx + 1 > input.length());
		}
		
		@Override
		public String toString() {
			return "(" + input + ":" + idx + ")";
		}
		
	}
	
	public static String _joinWords(String[] words) {
		List<StringIterator> iterators = new ArrayList<>();
		
		for (String word : words) 
			iterators.add(new StringIterator(word));
		
		StringBuilder answer = new StringBuilder();
		
		while (!iterators.isEmpty()) {
			Character minChar = null;
			
			for (StringIterator iterator : iterators)
				minChar = (minChar == null) ? iterator.peek() : (minChar > iterator.peek() ? iterator.peek() : minChar);
			
			answer.append(minChar);
			
			for (Iterator<StringIterator> stringIteratorIterator = iterators.iterator(); stringIteratorIterator.hasNext();) {
				StringIterator stringIterator = stringIteratorIterator.next();
				
				if (stringIterator.peek() == minChar) {
					stringIterator.next();
					
					if (!stringIterator.hasNext())
						stringIteratorIterator.remove();
				}
			}		
		}
		
		return answer.toString();
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new String[] { "BAB", "ABA" }, "ABAB");
		_instance.runTest(new String[] { "ABABA", "AKAKA", "AKABAS", "ABAKA" }, "ABABAKAKABAS");
		_instance.runTest(new String[] { "AAA","BBB", "CCC", "ABC", "BCA", "CAB" }, "AAABBBCABCCC");
	}

	public void runTest(final String[] words, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { words });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
	
}
