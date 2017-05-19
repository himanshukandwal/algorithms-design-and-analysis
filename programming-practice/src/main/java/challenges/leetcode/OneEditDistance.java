package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 161. One Edit Distance
 * 
 * Given two strings S and T, determine if they are both one edit distance apart.
 * 
 * @author Hxkandwal
 */
public class OneEditDistance extends AbstractCustomTestRunner {
	
	private static OneEditDistance _instance = new OneEditDistance();

	// using basic Levenshtein distance idea
	public boolean _isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) > 1) return false;
        int minLen = Math.min (s.length(), t.length());
        
        for (int idx = 0; idx < minLen; idx ++)
            if (s.charAt(idx) != t.charAt(idx)) 
                return (s.substring(idx + 1).equals(t.substring(idx)) 
                		|| s.substring(idx).equals(t.substring(idx + 1)) 
                		|| s.substring(idx + 1).equals(t.substring(idx + 1)));
        
        return Math.abs(s.length() - t.length()) == 1;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("a", "A", true);
		_instance.runTest("teacher", "detacher", false);
	}

	public void runTest(final String s, final String t, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, t });

		for (Object answer : answers)
				assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}    
    
}
