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
        String [] ver1 = version1.split("\\."), ver2 = version2.split("\\.");
        int max = Math.max (ver1.length, ver2.length);
        for (int idx = 0; idx < max; idx ++) {
            int f = idx < ver1.length ? Integer.valueOf(ver1 [idx]) : 0, s = idx < ver2.length ? Integer.valueOf(ver2 [idx]) : 0;
            if (f != s) return f > s ? 1 : -1;
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
