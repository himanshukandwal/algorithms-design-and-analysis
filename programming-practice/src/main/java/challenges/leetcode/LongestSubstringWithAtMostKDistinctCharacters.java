package challenges.leetcode;

import java.util.HashMap;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 340. Longest Substring with At Most K Distinct Characters
 * 
 * @author Hxkandwal
 */
public class LongestSubstringWithAtMostKDistinctCharacters extends AbstractCustomTestRunner {

	public int lengthOfLongestSubstringKDistinctOptimized(String s, int k) {
        int[] count = new int[256];
        int num = 0, i = 0, res = 0;
        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)]++ == 0) num++;
            if (num > k) {
                while (--count[s.charAt(i++)] > 0);   // <<<<<<<<< the one which has come less often will get killed first.
                num--;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
	
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) return 0;
        int [] hash = new int [256];
        int max = 0, distinct = 0, startIdx = 0;
        Map <Character, Integer> map = new HashMap<>();
        
        for (int idx = 0; idx < s.length(); idx ++) {
            char ch = s.charAt(idx);
            if (hash [ch] ++ == 0) distinct ++;
            
            if (distinct > k) {
                int minIdx = idx;
                for (Integer index : map.values()) if (index < minIdx) minIdx = index;
                char och = s.charAt(minIdx);
                hash [och] = 0;
                startIdx = map.get(och) + 1;
                map.remove(och);
                distinct --;
            }
            
            map.put (ch, idx);
            max = Math.max (max, idx - startIdx + 1);
        }
        
        return max;
    }
	
}
