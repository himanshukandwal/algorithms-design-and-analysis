package me.hxkandwal.daily.challanges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Strings Rearrangement
 * 
 * Given an array of equal-length strings, check if it is possible to rearrange the strings in such a way that after 
 * the rearrangement the strings at consecutive positions would differ by exactly one character.
 * 
 * Example:
 * 	For inputArray = ["aba", "bbb", "bab"], the output should be stringsRearrangement(inputArray) = false;
 * 	For inputArray = ["ab", "bb", "aa"], the output should be stringsRearrangement(inputArray) = true.
 * 
 * link: https://codefights.com/challenge/RWQS5cCEodqSWx4bR
 * 
 * @author Hxkandwal
 */
public class StringsRearrangement extends AbstractCustomTestRunner {
	
	private static StringsRearrangement _instance = new StringsRearrangement();

	public boolean _stringsRearrangement(String[] inputArray) {
		if (inputArray.length == 0) return true;
		int len = inputArray [0].length();
		 
		Map<String, Integer> map = new HashMap<>();
		for (String str : inputArray) map.put(str, map.getOrDefault(str, 0) + 1);
		
		Map<String, Set<String>> graph = new HashMap<>();
		for (String str : inputArray) graph.putIfAbsent(str, new HashSet<>());
		
		for (String str : graph.keySet()) {
			for (int idx = 0; idx < len; idx ++) {
				for (int cs = 0; cs < 26; cs ++) {
					char ch = (char) ('a' + cs);
					if (ch == str.charAt(idx)) continue;
					String next = str.substring(0, idx) + ch + str.substring(idx + 1);
					if (graph.containsKey (next)) { graph.get(str).add(next); graph.get(next).add(str); }
				}
			}
		}
		int size = inputArray.length;
		
		for (String str : graph.keySet()) {
			Map<String, Integer> coverage = new HashMap<>();
			for (String key : map.keySet()) coverage.put(key, 0);

			String curr = str; int count = 1;
			while (coverage.get(curr) < map.get(curr)) {
				coverage.put(curr, coverage.get(curr) + 1);

				for (String next : graph.get(curr)) {
					if (coverage.get(next) < map.get(next)) {
						curr = next; count ++;
						break;
					}
				}
			}
			
			if (count == size) return true;
		}
		return false;
	}
	
    // driver method
    public static void main(String[] args) {
    	_instance.runTest(new String[] { "q" }, true);
    	_instance.runTest(new String[] { "q", "q" }, false);
		_instance.runTest(new String[] { "abc", "abx", "axx", "abx", "abc" }, true);
		_instance.runTest(new String[] { "abc", "abx", "axx", "abc" }, false);
		_instance.runTest(new String[] { "aba", "bbb", "bab" }, false);
		_instance.runTest(new String[] { "ab", "bb", "aa" }, true);
		_instance.runTest(new String[] { "zzzzab", "zzzzbb", "zzzzaa" }, true);
    }

    public void runTest(final String[] inputArray, final boolean expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { inputArray });

        for (Object answer : answers)
            assertThat((Boolean) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }	
}
