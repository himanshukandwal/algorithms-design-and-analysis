package challenges.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 249. Group Shifted Strings
 * 
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" 
 * which forms the sequence:
 * 
 * 		"abc" -> "bcd" -> ... -> "xyz"
 * 
 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
 * 
 * For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 * 
 * A solution is:
 * 
 * 	[
 * 		["abc","bcd","xyz"],
 * 		["az","ba"],
 * 		["acef"],
 * 		["a","z"]
 * 	]
 * 
 * @author Hxkandwal
 */
public class GroupShiftedStrings extends AbstractCustomTestRunner {

	public List<List<String>> groupStrings(String[] strings) {
		List<List<String>> result = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		
		for (String string : strings) {
			String key = getBitMap (string);
			map.computeIfAbsent(key, k -> new ArrayList<>()).add(string);
		}
		
		for (String key : map.keySet()) {
			List<String> list = map.get (key);
			Collections.sort(list);
			result.add(list);
		}
		
		return result;
	}

	private String getBitMap(String s) {
		int[] arr = new int[s.length()];
		arr[0] = 0;
		for (int i = 1; i < s.length(); i++) {
			arr[i] = s.charAt(i) - s.charAt(0) < 0 ? ((s.charAt(i) - s.charAt(0)) % 26 + 26)
					: (s.charAt(i) - s.charAt(0));
		}
		return Arrays.toString(arr);
	}

}
