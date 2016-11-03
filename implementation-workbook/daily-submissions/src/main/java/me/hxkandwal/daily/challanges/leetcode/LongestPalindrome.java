package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
	
	private LongestPalindrome() {}

	public static int _longestPalindrome(String s) {
		Map<Character, Integer> alphabetMap = new HashMap<>(); 
		
		for (int idx = 0; idx < s.length(); idx++)
			if (alphabetMap.containsKey(s.charAt(idx))) 
				alphabetMap.put(s.charAt(idx), alphabetMap.get(s.charAt(idx)) + 1);
			else
				alphabetMap.put(s.charAt(idx), 1);
		
		int length = 0;
		int maxOdd = 0;
		
		// handle lowercase
		for (Map.Entry<Character, Integer> entry : alphabetMap.entrySet()) {
			if (entry.getValue() % 2 == 0)
				length += entry.getValue();
			else
				maxOdd = Math.max(maxOdd, entry.getValue());
		}
		
		return length + maxOdd;
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
