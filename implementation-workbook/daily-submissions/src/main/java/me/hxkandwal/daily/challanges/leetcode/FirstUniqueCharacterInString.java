package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 387. First Unique Character in a String.
 * 
 * Given a string, find the first non-repeating character in it and return it's
 * index. If it doesn't exist, return -1.
 * 
 * Example : s = "leetcode" return 0. s = "loveleetcode" return 2.
 * 
 * @author Hxkandwal
 *
 */
public class FirstUniqueCharacterInString extends AbstractCustomTestRunner {
	
	private static FirstUniqueCharacterInString _instance = new FirstUniqueCharacterInString();
	
	// method 1 : using collections.
	public int firstUniqCharCollections(String s) {
		if (s == null || s.isEmpty())
			return -1;
		
		if (s.length() == 1)
			return 0;
		
		Map<Character, Integer> charMap = new LinkedHashMap<>();
		Set<Character> trashedCharacters = new HashSet<>();
		
		for (int idx = 0; idx < s.length(); idx ++) {
			if (charMap.containsKey(s.charAt(idx))) {
				charMap.remove(s.charAt(idx));
				trashedCharacters.add(s.charAt(idx));
			} else
				if (!trashedCharacters.contains(s.charAt(idx)))
					charMap.put(s.charAt(idx), idx);
		}
		
		return (charMap.size() == 0 ? -1 : charMap.entrySet().iterator().next().getValue());
	}

	// method 2 : using native components.
	public int _firstUniqCharNative(String s) {
		if (s == null || s.isEmpty())
			return -1;
		
		if (s.length() == 1)
			return 0;
		
		int[] alpha = new int[26];
		
		for (int idx = 0; idx < s.length(); idx ++)
			alpha[s.charAt(idx) - 'a'] ++;
		
		int returnIdx = -1;
		
		for (int idx = 0; idx < s.length(); idx ++) 
			if (alpha[s.charAt(idx) - 'a'] == 1) {
				returnIdx = idx;
				break;
			}
		
		return returnIdx;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("leetcode", 0);
		_instance.runTest("loveleetcode", 2);
		_instance.runTest("aaabbcc", -1);
		_instance.runTest("aabbc", 4);
		_instance.runTest("aaabbc", 5);
		_instance.runTest("", -1);
		_instance.runTest(null, -1);
		
		System.out.println("ok!");
	}
	
	public void runTest(final String s, int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		String s = (String) externalVariables[0];
		
		int answer;
		try {
			answer = (int) method.invoke(_instance, new Object[] { s });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		
		return answer;
	}
	
}
