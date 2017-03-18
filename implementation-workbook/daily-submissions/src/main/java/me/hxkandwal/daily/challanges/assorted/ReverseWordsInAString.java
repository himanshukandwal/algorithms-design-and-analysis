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
	
	public String reverseWords(String s) {
        if (s.length() == 0) return s;
        StringBuilder ans = new StringBuilder();
        StringBuilder local = new StringBuilder();
        int idx = 0;
        while (idx < s.length()) {
            if (s.charAt(idx) == ' ') {
                if (local.length() > 0) { ans.append (local.reverse().toString()).append(' '); local.setLength(0); }
            } else local.append (s.charAt(idx));
            idx ++;
        }
        if (local.length() > 0) ans.append (local.reverse().toString());
        return ans.reverse().toString().trim();
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
