package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * Sort By String
 * 
 * Sort the letters in the string s by the order they occur in the string t.
 * Example:
 * 		For s = "weather" and t = "therapyw", the output should be
 * 		sortByString(s, t) = "theeraw";

For s = "good" and t = "odg", the output should be
sortByString(s, t) = "oodg".

 * @author Hxkandwal
 */
public class SortByString extends AbstractCustomTestRunner {
	
	private static SortByString _instance = new SortByString();

	public String _sortByStringSort(String s, String t) {
	    Character [] charr = new Character [s.length()];
	    for (int idx = 0; idx < s.length(); idx ++) charr [idx] = s.charAt(idx);
	    Arrays.sort(charr, (a, b) -> t.indexOf(a) - t.indexOf(b));
	    StringBuilder sb = new StringBuilder();
	    for (int idx = 0; idx < charr.length; idx ++) sb.append(charr [idx]);
	    return sb.toString();
	}

	public String _sortByString(String s, String t) {
	    Map<Character, Integer> map = new HashMap<>();
	    for (char ch : s.toCharArray()) map.put(ch, map.getOrDefault(ch, 0) + 1);
	    
	    StringBuilder sb = new StringBuilder();
	    for (char ch : t.toCharArray()) {
	    	if (map.containsKey(ch)) {
	    		for (int k = 0; k < map.get(ch); k ++) sb.append(ch);
	    		map.remove(ch);
	    	}
	    }
	    return sb.toString();
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("weather", "therapyw", "theeraw");
	}
	
	public void runTest(final String s, final String t, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, t });
		
		for (Object answer : answers) 
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
