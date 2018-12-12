package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 409. Longest Palindrome
 * 
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest 
 * palindromes that can be built with those letters.
 * 
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * 
 * Note: Assume the length of given string will not exceed 1,010.
 * 
 * Example:
 * 		Input	: "abccccdd"
 * 		Output	: 7
 * 		
 * 		Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
 * 	
 * @author Hxkandwal
 *
 */
public class LongestPalindrome extends AbstractCustomTestRunner {
	
	private static LongestPalindrome _instance = new LongestPalindrome();

	public static int _longestPalindrome(String s) {
		int[] count = new int['z' - 'A' + 1];
		for (char c : s.toCharArray()) 
			count[c - 'A']++;
		
		// remove 1 from all odds (make them even) and keep them in the string. (keep one odd)
		int odd = 0;
		for (char c = 'A'; c <= 'z'; c++) 
			odd += (count[c - 'A'] & 1);
		
		return s.length() - odd + (odd > 0 ? 1 : 0);
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("abccccdd", 7);	
		_instance.runTest("AAAAAA", 6);
		_instance.runTest("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreat"
				+ "battlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlives"
				+ "thatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotcons"
				+ "ecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddor"
				+ "detractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingra"
				+ "thertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicated"
				+ "tothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfull"
				+ "measureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomand"
				+ "thatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth", 983);
	}
	
	public void runTest(final String input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
}
