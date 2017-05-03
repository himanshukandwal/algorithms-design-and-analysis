package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 151. Reverse Words in a String
 * 
 * Given an input string, reverse the string word by word.
 * 
 * For example,	Given s = "the sky is blue", return "blue is sky the".
 * 
 * @author Hxkandwal
 */
public class ReverseWordsInAString extends AbstractCustomTestRunner {
	
	private static ReverseWordsInAString _instance = new ReverseWordsInAString();

	public String _reverseWords(String s) {
		String [] words = s.split (" ");
		StringBuilder sb = new StringBuilder ();
		int end = words.length - 1;
		for (int i = 0; i <= end; i ++) {
			if (!words[i].isEmpty()) {
				sb.insert (0, words [i]);
				if (i < end) sb.insert (0, " ");
			}
		}
		return sb.toString();
	}
	
	// longer but much better solution.
	public String _reverseWordsCorrectly(String s) {
		if (s == null) return null;

		char[] a = s.toCharArray();
		int n = a.length;

		// step 1. reverse the whole string
		reverse(a, 0, n - 1);
		// step 2. reverse each word
		reverseWords(a, n);
		// step 3. clean up spaces
		return cleanSpaces(a, n);
	}
	  
	void reverseWords(char[] a, int n) {
		int i = 0, j = 0;

		while (i < n) {
			while (i < j || i < n && a[i] == ' ') i++; // skip spaces
			while (j < i || j < n && a[j] != ' ') j++; // skip non spaces
			reverse(a, i, j - 1); // reverse the word
		}
	}
	  
	  // trim leading, trailing and multiple spaces
	String cleanSpaces(char[] a, int n) {
		int i = 0, j = 0;
		while (j < n) {
			while (j < n && a[j] == ' ') j++; // skip spaces
			while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
			while (j < n && a[j] == ' ') j++; // skip spaces
			if (j < n) a[i++] = ' '; // keep only one space
		}
		return new String(a).substring(0, i);
	}
	  
	  // reverse a[] from a[i] to a[j]
	private void reverse(char[] a, int i, int j) {
		while (i < j) {
			char t = a[i];
			a[i++] = a[j];
			a[j--] = t;
		}
	}
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("    ", "");
		_instance.runTest("the sky is blue", "blue is sky the");
	}

	public void runTest(final String s, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}    
    
}
