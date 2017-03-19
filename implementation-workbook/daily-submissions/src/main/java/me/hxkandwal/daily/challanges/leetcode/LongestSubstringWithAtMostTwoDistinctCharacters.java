package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 159. Longest Substring with At Most Two Distinct Characters
 * 
 * Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
 * 
 * For example, 
 * 		Given s = “eceba”,
 * 		T is "ece" which its length is 3.
 * 
 * @author Hxkandwal
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters extends AbstractCustomTestRunner {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int [] map = new int [256];
        int max = 0, start = 0, count = 0;
        for (int idx = 0; idx < s.length(); idx ++) {
            if (map [s.charAt(idx)] ++ == 0) count ++;
            if (count > 2) {
                while (-- map [s.charAt(start ++)] > 0);
                count --;
            }
            max = Math.max (max, idx - start + 1);
        }
        return max;
    }
    
}
