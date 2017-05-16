package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 318. Maximum Product of Word Lengths
 * 
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common 
 * letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
 * 
 * Example 1:
 * 		Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
 * 		Return 16
 * 
 * 		The two words can be "abcw", "xtfn".
 * 
 * Example 2:
 * 		Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
 * 		Return 4
 * 	
 * 		The two words can be "ab", "cd".
 * 
 * Example 3:
 * 		Given ["a", "aa", "aaa", "aaaa"]
 * 		Return 0
 * 
 * 		No such pair of words.
 * 
 * @author Hxkandwal
 */
public class MaximumProductOfWordLengths extends AbstractCustomTestRunner {

	public int maxProduct(String[] words) {
        int ans = 0;
        int [] mask = new int [words.length];
        for (int idx = 0; idx < words.length; idx ++) {
            for (char ch : words [idx].toCharArray()) mask [idx] |= 1 << (ch - 'a');
            for (int jdx = 0; jdx < idx; jdx ++) {
                if ((mask [jdx] & mask [idx]) == 0)
                    ans = Math.max (ans, words [jdx].length() * words [idx].length());
            }
        }
        return ans;
    }
	
}
