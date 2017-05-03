package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

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
	
	public int _strstr (String haystack, String needle) {
		for (int idx = 0; idx <= haystack.length() - needle.length(); idx ++) {
            boolean found = true;
            for (int j = 0; j < needle.length(); j ++)
                if (haystack.charAt (idx + j) != needle.charAt (j)) { found = false; break; }
            
            if (found) return idx;
        } 
        return -1;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("aabaaabaaac", "aabaaac", 4);
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
