package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 165. Compare Version Numbers
 * 
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 * 
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
 * 
 * Here is an example of version numbers ordering:
 * 
 * 		0.1 < 1.1 < 1.2 < 13.37
 * 
 * @author Hxkandwal
 */
public class CompareVersionNumbers extends AbstractCustomTestRunner {
	
	private static CompareVersionNumbers _instance = new CompareVersionNumbers();

	public int _compareVersion(String version1, String version2) {
        String [] levels1 = version1.split ("\\.");
        String [] levels2 = version2.split ("\\.");
        
        int m = levels1.length, n = levels2.length, cmp = 0;
        for (int idx = 0; idx < Math.min (m, n); idx ++) 
            if  ((cmp = compare (levels1 [idx], levels2 [idx])) != 0) return cmp;
        
        if (m > n) {
        	for (int idx = n; idx < m; idx ++) {
        		int i = 0; String s1 = levels1 [idx];
                for (; i < s1.length(); i ++) if (s1.charAt (i) != '0') break;
                if (s1.length() - i > 0) return 1;
        	}
        } else if (m < n) {
        	for (int idx = m; idx < n; idx ++) {
        		int i = 0; String s2 = levels2 [idx];
        		for (; i < s2.length(); i ++) if (s2.charAt (i) != '0') break;
                if (s2.length() - i > 0) return -1;
        	}
        }
        return 0;
    }
    
    private int compare (String s1, String s2) {
    	int i = 0;
        for (; i < s1.length(); i ++) if (s1.charAt (i) != '0') break;
        s1 = s1.substring (i);
        
        for (i = 0; i < s2.length(); i ++) if (s2.charAt (i) != '0') break;
        s2 = s2.substring (i);
        
        if (s1.length() != s2.length()) return (s1.length() > s2.length() ? 1 : -1);
        
        for (int idx = 0; idx < s1.length(); idx ++) {
            if (s1.charAt (idx) > s2.charAt (idx)) return 1;
            else if (s1.charAt (idx) < s2.charAt (idx)) return -1;
        }
        return 0;
    }
    
    // with using Integer parsing.
    public int _compareVersionInteger(String version1, String version2) {
        String [] levels1 = version1.split ("\\.");
        String [] levels2 = version2.split ("\\.");
        
        int m = levels1.length, n = levels2.length, cmp = 0;
        for (int idx = 0; idx < Math.min (m, n); idx ++) 
            if  ((cmp = (Integer.valueOf (levels1 [idx]) - Integer.valueOf (levels2 [idx]))) != 0) return cmp > 0 ? 1 : -1;
        
        if (m > n) {
        	for (int idx = n; idx < m; idx ++) 
        		if (Integer.valueOf (levels1 [idx]) > 0) return 1;
        } else if (m < n) {
        	for (int idx = m; idx < n; idx ++) 
        		if (Integer.valueOf (levels2 [idx]) > 0) return -1;
        }
        return 0;
    }
    
    // with using Integer parsing (Solution 2) - more optimistic
    public int _compareVersionIntegerAnother(String version1, String version2) {
        String[] levels1 = version1.split("\\.");
        String[] levels2 = version2.split("\\.");
        
        int length = Math.max(levels1.length, levels2.length);
        for (int i=0; i<length; i++) {
        	Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
        	Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
        	int compare = v1.compareTo(v2);
        	if (compare != 0) {
        		return compare;
        	}
        }
        
        return 0;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("1.1", "1", 1);
		_instance.runTest("1.0.0.0", "1", 0);
		_instance.runTest("1.0.0.1", "1", 1);
		_instance.runTest("1.2", "1.10", -1);
		_instance.runTest("1.1", "1.1", 0);
	}

	public void runTest(final String version1, final String version2, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { version1, version2 });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}    
    
}
