package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Find all distinct palindromic sub-strings of a given string
 * 
 * Given a string of lowercase ASCII characters, find all distinct continuous palindromic sub-strings of it.
 * 
 * Examples:
 * 		Input: str = "abaaa"
 * 		Output:  Below are 5 palindrome sub-strings
 * 					a
 * 					aa
 * 					aaa
 * 					aba
 * 					b
 * 
 * 		Input: str = "geek"
 * 		Output:  Below are 4 palindrome sub-strings
 * 					e
 * 					ee
 * 					g
 * 					k
 * 
 * link: http://www.geeksforgeeks.org/find-number-distinct-palindromic-sub-strings-given-string/
 * 
 * @author Hxkandwal
 *
 */
public class DistinctPalindromicSubStrings extends AbstractCustomTestRunner {
	
	private static DistinctPalindromicSubStrings _instance = new DistinctPalindromicSubStrings();
	
	private DistinctPalindromicSubStrings() {}

	public static int _findAllPalindromes(String string) {
		Set<String> uniquePalindromes = new HashSet<>();
		
		for (int index = 0; index < string.length(); index ++) {
			uniquePalindromes.add ("" + string.charAt(index));
			
			if (index > 0 && index < string.length()) {
				circulate (uniquePalindromes, string, index, 1, true);
				circulate (uniquePalindromes, string, index, 1, false);
			}
		}
		
		return uniquePalindromes.size();
	}
	
	private static void circulate (Set<String> uniquePalindromes, String string, int center, int offset, boolean isEven) {
		if ((center - offset) >= 0) {
			if (isEven 
					&& (center + offset - 1 < string.length())
					&& string.charAt(center - offset) == string.charAt(center + offset - 1)) {
				
				uniquePalindromes.add(string.substring(center - offset, center + offset));
				circulate(uniquePalindromes, string, center, offset + 1, true);
			}
			
			if (!isEven 
					&& (center + offset) < string.length()) {
				
				if (string.charAt(center - offset) == string.charAt(center + offset)) {
					uniquePalindromes.add(string.substring(center - offset, center + offset + 1));
					circulate(uniquePalindromes, string, center, offset + 1, false);
				}
			}
		}
	}
	
	// driver method
    public static void main(String[] args) throws FileNotFoundException {
    	_instance.runTest("abaaa", 5);
    	_instance.runTest("geek", 4);
    }

	public void runTest(final String s, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
	
	
}
