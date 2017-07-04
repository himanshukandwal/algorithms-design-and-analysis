package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import challenges.AbstractCustomTestRunner;

/**
 * 205. Isomorphic Strings
 * 
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * 
 * All occurrences of a character must be replaced with another character while preserving the order 
 * of characters. No two characters may map to the same character but a character may map to itself.
 * 
 * For example,
 * 
 * (a)		Given "egg", "add", return true.
 * (b)		Given "foo", "bar", return false.
 * (c)		Given "paper", "title", return true.
 * 
 * @author Hxkandwal
 *
 */
public class IsomorphicStrings extends AbstractCustomTestRunner {

	private static IsomorphicStrings _instance = new IsomorphicStrings();

	public static boolean isIsomorphicWay1(String s, String t) {
        int [] sArr = new int [256], tArr = new int [256];
        for (int idx = 0; idx < s.length(); idx ++) {
            char sch = s.charAt (idx), tch = t.charAt (idx);
            if (sArr [sch] != tArr [tch]) return false;
            sArr [sch] = idx + 1;
            tArr [tch] = idx + 1;
        }
        return true;
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean isIsomorphicWay2(String s, String t) {
        if (s.length() != t.length()) return false;
        Map map = new HashMap();
        for (int idx = 0; idx < s.length(); idx ++) 
            if (!Objects.equals (map.put (s.charAt (idx), idx), map.put (t.charAt (idx) + "", idx))) return false;
        return true;
    }
	
	// same as way1
	public boolean isIsomorphicWay3(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> rmap = new HashMap<>();
        for (int idx = 0; idx < s.length(); idx ++) 
            if (!Objects.equals (map.put (s.charAt (idx), idx), rmap.put (t.charAt (idx), idx))) return false;
        return true;
    }
	
	// driver method
    public static void main(String[] args) throws FileNotFoundException {
    	_instance.runTest("egg", "add", true);
    	_instance.runTest("ab", "aa", false);
		_instance.runTest("foo", "bar", false);
		_instance.runTest("paper", "title", true);
		_instance.runTest("abca", "zbxz", true);
		_instance.runTest("abcb", "zbxz", false);
		_instance.runTest("abcb", "zbxb", false);
    }

	public void runTest(final String s, final String t, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, t });
		
		for (Object answer : answers) 
			assertThat((boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
