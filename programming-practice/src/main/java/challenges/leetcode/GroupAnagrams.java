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

	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> ans = new ArrayList<>();
		Map<Integer, List<String>> map = new HashMap<>();
		for (String s : strs) {
			char [] arr = new char[256];
			for (char c : s.toCharArray()) arr [c] ++;
			map.computeIfAbsent(Arrays.hashCode(arr), k -> new ArrayList<>()).add (s);
		}
		ans.addAll (map.values());
		return ans;
	}
    
}
