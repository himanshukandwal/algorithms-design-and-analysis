package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Find Consecutive Repetitive Character
 * 
 * link: https://www.careercup.com/question?id=5068525614923776
 * 
 * @author Hxkandwal
 */
public class FindConsecutiveRepetitiveCharacter extends AbstractCustomTestRunner {
	
	private static FindConsecutiveRepetitiveCharacter _instance = new FindConsecutiveRepetitiveCharacter();
	
	public char _findMaxRep (String s) {
		if (s.length() == 0) return ' ';
		int max = 1, local = 1; 
		char maxCh = s.charAt(0), prevCh = maxCh;
		
		for (int idx = 1; idx < s.length(); idx ++) {
			char ch = s.charAt(idx);
			local = (ch == prevCh) ? local + 1 : 1;
			if (local > max) { max = local; maxCh = ch; }
			prevCh = ch;
		}
		
		return maxCh;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("ffgggtvshjsdhjfffffffhvjbjcharu", 'f');
	}

	public void runTest(final String s, final char expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });

		for (Object answer : answers)
			assertThat((Character) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
