package me.hxkandwal.daily.challanges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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
		Map<String, Integer> map = new HashMap<>();
		for (String str : inputArray) map.put(str, map.getOrDefault(str, 0) + 1);
		
		Queue<String> queue = new LinkedList<>();
		for (Iterator<Map.Entry<String, Integer>> entryIterator = map.entrySet().iterator(); entryIterator.hasNext();) {
			Map.Entry<String, Integer> entry = entryIterator.next();
			if (entry.getValue() == 1) queue.offer(entry.getKey());
		}
		
		if (queue.isEmpty()) queue.offer(inputArray [0]);
		while (!queue.isEmpty()) {
			String start = queue.poll();
			int len = start.length();
			
			Map<String, Integer> iMap = new HashMap<>(map);
			iMap.put(start, map.get(start) - 1);
			if (iMap.get(start) == 0) iMap.remove(start);
			
			boolean found = true;
			while (found) {
				found = false;
				for (int idx = 0; idx < len; idx ++) {
					for (int cs = 0; cs < 26; cs ++) {
						char ch = (char) ('a' + cs);
						String next = start.substring(0, idx) + ch + start.substring(idx + 1);
						if (!next.equals(start) && iMap.containsKey(next)) {
							if (iMap.put(next, iMap.get(next) - 1) == 1) iMap.remove(next);
							start = next;
							found = true;
							break;
						}
					}
					if (found) break;
				}
			}
			if (iMap.size() == 0) return true; 
		}
		return false;
	}

    // driver method
    public static void main(String[] args) {
    	_instance.runTest(new String[] { "q" }, true);
    	_instance.runTest(new String[] { "q", "q" }, false);
		_instance.runTest(new String[] { "abc", "abx", "axx", "abx", "abc" }, true);
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
