package challenges.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 49. Group Anagrams
 * 
 * Given an array of strings, group anagrams together.
 * 
 * For example,
 * 		given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 		Return:
 * 				[
 * 					["ate", "eat","tea"],
 * 					["nat","tan"],
 * 					["bat"]
 * 				]
 * 
 * Note: All inputs will be in lower-case.
 * 
 * @author Hxkandwal
 */
public class GroupAnagrams extends AbstractCustomTestRunner {
	
    public List<List<String>> _groupAnagrams(String[] strs) {
    	List<List<String>> answer = new ArrayList<>();
    	Map<Integer, List<String>> dp = new HashMap<>();
    	for (String str : strs) {
    		int[] hash = new int[26];
    		for (int idx = 0; idx < str.length(); idx ++) hash [str.charAt(idx) - 'a'] ++;
    		int hashcode = Arrays.hashCode(hash);
    		
    		if (!dp.containsKey(hashcode)) dp.put(hashcode, new ArrayList<>());
    		dp.get(hashcode).add(str);
        }
        answer.addAll(dp.values());
        return answer;
    }
    
}
