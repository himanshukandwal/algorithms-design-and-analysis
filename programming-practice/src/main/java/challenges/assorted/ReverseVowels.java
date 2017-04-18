package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * Reverse Vowels
 * 
 * Swap vowels by comparing leftmost and right most vowel in the string, then compare next leftmost and right most vowel 
 * and swap, continue until reach the middle of the string.
 * 
 * link: https://www.careercup.com/question?id=1602
 * 
 * @author Hxkandwal
 */
public class ReverseVowels extends AbstractCustomTestRunner {
	
	private static ReverseVowels _instance = new ReverseVowels();

	public String _reverse(String str) {
		char [] charr = str.toCharArray();
		Set<Character> vowels = new HashSet<>();
		for (char ch : "aeiou".toCharArray()) vowels.add(ch);
		
		int left = 0, right = str.length() - 1;
		while (left < right) {
			while (left < right && !vowels.contains(charr [left])) left ++;
			while (left < right && !vowels.contains(charr [right])) right --;
			if (left == right) break;
			char ch = charr [left];
			charr [left] = charr [right];
			charr [right] = ch;
			left ++; right --;
		}
		return new String (charr);
	} 
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("abbce", "ebbca");
	}

	public void runTest(final String str, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { str });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
}
