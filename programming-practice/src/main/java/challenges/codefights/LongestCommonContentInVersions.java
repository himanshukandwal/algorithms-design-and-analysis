package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Once upon a time there was a File. It was perfect and there was no need to change it. 
 * But one day a group of programmers came across it and decided to make a couple of changes 
 * by adding new symbols here and there. They ended up with two versions of the File. 
 * 
 * You think that the programmers didn't have enough time to ruin it completely, so you want to 
 * restore it.
 * 
 * Your approach is as follows: if a string result can be obtained from both versions by removing 
 * some symbols in them, then it can be the File. You need to find the longest result possible.
 * 
 * Example:
 * 		For version1 = "CodeFigh#ts" 
 * 		and version2 = "Co$deFig?hts", 
 * 		the output should be "CodeFights"
 * 
 * link : https://codefights.com/tournaments/nCTLjbYGLAD38gLZT/C
 * 
 * @author Hxkandwal
 *
 */
public class LongestCommonContentInVersions extends AbstractCustomTestRunner {
	
	private static LongestCommonContentInVersions _instance = new LongestCommonContentInVersions();
	
	private LongestCommonContentInVersions() {}
	
	// sum of previous exact match (top diagonal) and max of top and left.
	// in case of string retrieval better in-line with a wall to secure ourselves from stupid array exceptions.
	public static String _commonContent (String version1, String version2) {
		
		// reason mentioned above in for length() + 1
		int[][] patternMatrix = new int [version1.length() + 1] [version2.length() + 1];

	    // dp build up. (added + 1, due to in-lining)
	    for (int row = 0; row < version1.length(); row ++)
	    	for (int col = 0; col < version2.length(); col ++)
		    	if (version1.charAt(row) == version2.charAt(col))
		    		patternMatrix [row + 1][col + 1] = patternMatrix [row][col] + 1;
		    	else
		    		patternMatrix [row + 1][col + 1] = Math.max (patternMatrix [row + 1][col], patternMatrix [row][col + 1]);
	    
	    // string retrieval from pattern matrix.
	    
	    int row = version1.length(), col = version2.length();
	    StringBuilder answer = new StringBuilder();
	    
	    // no need to hit the zeros, powerful in-lining in place.
		while (row > 0 && col > 0) {
			if (version1.charAt(row - 1) == version2.charAt(col - 1)) {
				answer.append(version1.charAt(row - 1));
				row--; col--;
				continue;
			}
			if (patternMatrix [row - 1][col] == patternMatrix [row][col]) {
				row--;
				continue;
			} 
			if (patternMatrix [row ][col - 1] == patternMatrix [row][col]) {
				col--;
				continue;
			}
		}
	    
		return answer.reverse().toString();
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("CodeFigh#ts", "Co$deFig?hts", "CodeFights");
		_instance.runTest("ABCDGH", "AEDFHR", "ADH");
		_instance.runTest("", "ololo", "");
		_instance.runTest("grHenllso weortlds!", "H5elelo 1dworlbd!", "Hello world!");
		_instance.runTest("aaca", "abaa", "aaa");
		_instance.runTest("aacaaa", "abaa", "aaa");
		_instance.runTest("123142342himansa543432534hu kan39483290483dwa13434l", "%&^&^&(*hima&*()*))(ns**(*hu ka(*()&*(&ndw#@%$@%$@al", "himanshu kandwal");
	}

	public void runTest(final String version1, final String version2, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { version1, version2 });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
}
