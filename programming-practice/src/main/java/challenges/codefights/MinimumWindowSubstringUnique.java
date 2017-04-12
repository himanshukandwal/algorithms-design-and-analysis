package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * Minimum Window Substring
 * 
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * 
 * For example,
 * 		S = "ADOBECODEBANC" T = "ABC"
 * 		Minimum window is "BANC".
 * 
 * @author Hxkandwal
 */
public class MinimumWindowSubstringUnique extends AbstractCustomTestRunner {
	
	private static MinimumWindowSubstringUnique _instance = new MinimumWindowSubstringUnique();
	
	public String _minWindow(String s, String t) {
		if (t.length() == 0 || s.length() == 0 || s.length() < t.length()) return "";
        Set <Character> tset = new HashSet<>();
        for (char ch : t.toCharArray()) tset.add (ch);
        
        Map<Character, Integer> map = new HashMap<>();
        String ans = s; boolean changed = false;
        for (int idx = 0, start = 0; idx < s.length(); idx ++) {
            char ch = s.charAt (idx);
            
            if (tset.contains (ch)) {
                changed = true;
                map.put (ch, map.getOrDefault (ch, 0) + 1);
                while (start < s.length() && !tset.contains (s.charAt(start))) start ++;
                
                while (map.size() == tset.size()) {
                	if (idx - start + 1 < ans.length()) ans = s.substring (start, idx + 1);
                	
                    char sch = s.charAt (start);
                    if (tset.contains (sch) && map.put (sch, map.get (sch) - 1) == 1)
                        map.remove (sch);
                    start ++;
                }
            }
        }
        return (!changed) ? "" : ans;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("xgajymplpvftjwjqomhbnutorgysaf", "j", "j");
		_instance.runTest("zqyvbfeiee", "ze", "zqyvbfe");
		_instance.runTest("adobecodebanc", "abc", "banc");
	}

	public void runTest(final String s, final String t, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, t });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	} 

}
