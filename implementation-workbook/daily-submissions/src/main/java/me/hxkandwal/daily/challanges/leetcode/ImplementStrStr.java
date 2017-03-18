package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 28. Implement strStr()
 * 
 * Implement strStr().
 * 
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *  
 * @author Hxkandwal
 *
 */
public class ImplementStrStr extends AbstractCustomTestRunner {

	private static ImplementStrStr _instance = new ImplementStrStr();
	
	private ImplementStrStr() {};
	
    public int _strStr(String haystack, String needle) {
        int res = -1;
        for (int idx = 0; idx <= haystack.length() - needle.length(); idx ++) {
            boolean found = true; 
            for (int jdx = 0; jdx < needle.length(); jdx ++) {
                if (needle.charAt(jdx) != haystack.charAt(idx + jdx)) {
                    found = false;
                    break;
                }
            }
            if (found) return idx;
        }
        return res;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("leetcode", "leetcode", 0);
		_instance.runTest("leetcode", "leetcodes", -1);
		_instance.runTest("eleetcode", "leetcode", 1);
		_instance.runTest("mississippi", "issip", 4);
	}
	
	public void runTest(final String haystack, final String needle, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { haystack, needle });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}    
    
}
