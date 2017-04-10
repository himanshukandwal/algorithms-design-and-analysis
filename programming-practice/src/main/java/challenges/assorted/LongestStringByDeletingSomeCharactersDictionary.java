package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Giving a string and an string dictionary, find the longest string in dictionary which can formed by deleting some characters of the 
 * giving string. 
 * 
 * Example: S = abpcplea, Dict = {ale, apple, monkey, plea},
 * 			Answer: "apple"
 * 
 * link: https://careercup.com/question?id=5757216146587648
 * 
 * @author Hxkandwal
 */
public class LongestStringByDeletingSomeCharactersDictionary extends AbstractCustomTestRunner {

	private static LongestStringByDeletingSomeCharactersDictionary _instance = new LongestStringByDeletingSomeCharactersDictionary();
	
	private LongestStringByDeletingSomeCharactersDictionary() {}
	
	public static String _longestString (String[] dictionary, String input) {
		String answer = null;
		
		for (String item : dictionary) {
			String lcs = longestCommonSubstring(item, input);
			if (answer == null || lcs.length() > answer.length()) 
				answer = lcs;
		}
		
		return answer;
	}
	
	private static String longestCommonSubstring (String a, String b) {
		int[][] dp = new int [a.length() + 1][b.length() + 1];
		int maxLength = 0;
		
		for (int row = 0; row < a.length(); row ++) {
			for (int col = 0; col < b.length(); col ++) {
				if (a.charAt(row) == b.charAt(col))
					dp [row + 1][col + 1] = dp [row][col] + 1;
				else
					dp [row + 1][col + 1] = Math.max(dp [row][col + 1], dp [row + 1][col]);
				
				maxLength = Math.max(maxLength, dp [row + 1][col + 1]);
			}
		}
		
		char[] answer = new char [maxLength];
		int idx = answer.length - 1, row = dp.length - 1, col = dp[0].length - 1;
		
		while (row > 0 && col > 0) {
			if (dp [row][col] == dp [row - 1][col]) { row --; continue; }
			if (dp [row][col] == dp [row][col - 1]) { col --; continue; }
			
			answer [idx --] = a.charAt(row - 1);
			row --; col --;
		}
		
		return String.valueOf (answer);
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new String[] { "ale", "apple", "monkey", "plea" }, "abpcplea", "apple");
	}

	public void runTest(final String[] dictionary, final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { dictionary, input });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
