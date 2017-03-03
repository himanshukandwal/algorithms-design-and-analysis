package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Reverse Words in a String
 * 
 * http://www.ardendertat.com/2011/10/31/programming-interview-questions-12-reverse-words-in-a-string/
 * 
 * @author Hxkandwal
 */
public class ReverseWordsInAString extends AbstractCustomTestRunner {
	
	private static ReverseWordsInAString _instance = new ReverseWordsInAString();
	
	public String reverse (String input) {
		String[] parts = input.trim().split("\\s+");
		String out = "";
		for (int i = parts.length - 1; i > 0; i--) {
		    out += parts[i] + " ";
		}
		return out + parts[0];
	}

	public String _reverseWords(String s) {
		char [] ans = new char [s.length()];
		int aidx = ans.length, iidx = 0;
		
		for (int idx = 0; idx < s.length(); idx ++) {
			if (s.charAt(idx) == ' ' || idx == s.length() - 1) {
				if (idx == s.length() - 1) idx ++;
				int dist = (idx - iidx);
				aidx -= dist;
				for (; iidx < idx; iidx ++) ans [aidx ++] = s.charAt(iidx);
				aidx -= dist; iidx ++;
			} 
		}
		return String.valueOf(ans);
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("A CS degree", "degree CS A");
	}
	
	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers) 
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
