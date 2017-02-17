package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class GroupAnagrams extends AbstractCustomTestRunner {
	
	private static GroupAnagrams _instance = new GroupAnagrams();
	
	private GroupAnagrams() {}
	
	// Simple usage of hashmap.
    public List<List<String>> _groupAnagrams(String[] strs) {
    	List<List<String>> answer = new ArrayList<>();
    	
        if (strs != null && strs.length > 0) { 
        	Map<Integer, List<String>> dp = new HashMap<>();
        	for (String str : strs) {
        		int[] hash = new int[26];
    			for (int idx = 0; idx < str.length(); idx ++) hash [str.charAt(idx) - 'a'] ++;
    			int hashcode = Arrays.hashCode(hash);
    			
    			if (dp.containsKey(hashcode)) 
    				dp.get(hashcode).add(str);
    			else
    				dp.put(hashcode, new ArrayList() {{ add(str); }});
			}
        	
        	for (List<String> response : dp.values()) answer.add(response);
        }
        
        return answer;
    }

    // driver method
 	public static void main(String[] args) {
 		_instance.runTest(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" }, new ArrayList() {{  add(new ArrayList() {{  add("ate"); add("eat"); add("tea"); }});
 																										 add(new ArrayList() {{  add("nat"); add("tan"); add("tea"); }});
 																										 add(new ArrayList() {{  add("bat");  }});}});
 	}

 	public void runTest(final String[] strs, final List<List<String>> expectedOutput) {
 		List<Object> answers = runAll(getClass(), new Object[] { strs });

 		for (Object answer : answers)
 			assertThat((List<List<String>>) answer).isEqualTo(expectedOutput);
 		
 		System.out.println("ok!");
 	}    

}
