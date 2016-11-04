package me.hxkandwal.daily.challanges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
	
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("CodeFigh#ts", "Co$deFig?hts", "CodeFights");
		_instance.runTest("grHenllso weortlds!", "H5elelo 1dworlbd", "Hello world!");
		_instance.runTest("ABCDGH", "AEDFHR", "ADH");
	}

	public void runTest(final String version1, final String version2, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { version1, version2 });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
}
