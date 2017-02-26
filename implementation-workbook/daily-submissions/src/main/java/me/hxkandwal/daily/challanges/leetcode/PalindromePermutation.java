package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 266. Palindrome Permutation
 * 
 * @author Hxkandwal
 */
public class PalindromePermutation extends AbstractCustomTestRunner {

	public boolean canPermutePalindrome(String s) {
        int[] odds = new int [256]; int count = 0;
        for (char c : s.toCharArray()) odds [c] ^= 1;
        for (int odd : odds) count += odd;
        return count <= 1;
    }
	
}
