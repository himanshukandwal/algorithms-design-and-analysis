package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 520. Detect Capital
 * 
 * Given a word, you need to judge whether the usage of capitals in it is right or not.
 * 
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 * 		All letters in this word are capitals, like "USA".
 * 		All letters in this word are not capitals, like "leetcode".
 * 		Only the first letter in this word is capital if it has more than one letter, like "Google".
 * 
 * Otherwise, we define that this word doesn't use capitals in a right way.
 * 
 * @author Hxkandwal
 *
 */
public class DetectCapital extends AbstractCustomTestRunner {
	
	private static DetectCapital _instance = new DetectCapital();

    public boolean _detectCapitalUse(String word) {
        if (word == null || word.length () == 0) return true;
        boolean allCapital = false, allLower = false;
        for (int idx = 0; idx < word.length(); idx ++) {
            char ch = word.charAt(idx);
            if (idx == 0) { allLower = !(ch >= 'A' && ch <= 'Z'); continue; }
            if (ch >= 'A' && ch <= 'Z') {
                if (idx == 1) allCapital = true;
                if (allLower || !allCapital) return false;
            } else if (allCapital) return false;
        }
        return true;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("USA", true);
	}

	public void runTest(final String word, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { word });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
