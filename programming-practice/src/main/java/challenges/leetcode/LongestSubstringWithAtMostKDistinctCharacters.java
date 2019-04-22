package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 340. Longest Substring with At Most K Distinct Characters
 *
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 *
 * For example, Given s = “eceba” and k = 2, T is "ece" which its length is 3.
 * 
 * @author hxkandwal
 */
public class LongestSubstringWithAtMostKDistinctCharacters extends AbstractCustomTestRunner {

	public int _lengthOfLongestSubstringKDistinctOptimized(String s, int k) {
        int [] arr = new int [256];
        int counter = 0, ans = 0;
        for (int idx = 0, start = 0; idx < s.length(); idx ++) {
            if (arr [s.charAt(idx)] ++ == 0) counter ++;
            while (counter > k) {
                if (--arr [s.charAt(start ++)] == 0) counter --;
            }
            ans = Math.max(ans, idx - start + 1);
        }
        return ans;
    }
}
