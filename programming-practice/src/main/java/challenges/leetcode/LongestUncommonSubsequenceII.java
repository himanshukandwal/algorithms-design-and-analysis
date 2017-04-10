package challenges.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 522. Longest Uncommon Subsequence II
 * 
 * Given a list of strings, you need to find the longest uncommon subsequence among them. 
 * The longest uncommon subsequence is defined as the longest subsequence of one of these strings and 
 * this subsequence should not be any subsequence of the other strings.
 * 
 * A subsequence is a sequence that can be derived from one sequence by deleting some characters without 
 * changing the order of the remaining elements. Trivially, any string is a subsequence of itself and an 
 * empty string is a subsequence of any string.
 * 
 * The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence. 
 * If the longest uncommon subsequence doesn't exist, return -1.
 * 
 * Example 1:
 * 		Input: "aba", "cdc", "eae"
 * 		Output: 3
 * 
 * Note:
 * 		All the given strings' lengths will not exceed 10.
 * 		The length of the given list will be in the range of [2, 50].
 * 
 * @author Hxkandwal
 */
public class LongestUncommonSubsequenceII extends AbstractCustomTestRunner {
	
	public int _findLUSlength(String[] strs) {
		Map<String, Boolean> map = new HashMap<>();
        for (String str : strs) {
            if (map.containsKey (str)) map.put (str, false);
            else map.put (str, true);
        }
        
        for (Iterator<Map.Entry<String, Boolean>> entryIterator = map.entrySet().iterator(); entryIterator.hasNext();) {
            Map.Entry<String, Boolean> entry = entryIterator.next();
            
            if (entry.getValue()) {
                for (Map.Entry<String, Boolean> innerEntry : map.entrySet()) {
                    if (!innerEntry.getValue() && isSubSequence (innerEntry.getKey(), entry.getKey())) {
                    	entry.setValue(false);
                    	break;
                    }
                }
            }
        }
        
        int max = -1;
        for (Map.Entry<String, Boolean> entry : map.entrySet()) 
            if (entry.getValue()) max = Math.max (max, entry.getKey().length());
        return max;
    }
    
    private boolean isSubSequence (String s, String t) {
        int tidx = 0;
        for (int sidx = 0; sidx < s.length() && tidx < t.length(); sidx ++)
            if (s.charAt(sidx) == t.charAt(tidx)) tidx ++;
        return tidx >= t.length();
    }

}
