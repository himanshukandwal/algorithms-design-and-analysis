package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 500. Keyboard Row
 * 
 * Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.
 * 
 * Example 1:
 * 		Input: ["Hello", "Alaska", "Dad", "Peace"]
 * 		Output: ["Alaska", "Dad"]
 * 
 * Note:
 * 		You may use one character in the keyboard more than once.
 * 		You may assume the input string will only contain letters of alphabet.
 * 
 * @author Hxkandwal
 */
public class KeyboardRow extends AbstractCustomTestRunner {
	
	private static KeyboardRow _instance = new KeyboardRow();
	
	private KeyboardRow() {}

    public static String[] _findWords(String[] words) {
    	List<String> ans = new ArrayList<>();
        for (String w : words) if (w.matches ("[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*")) ans.add (w);
        return ans.toArray(new String [0]);
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new String [] { "Hello", "Alaska", "Dad", "Peace" }, new String [] { "Alaska", "Dad" });
	}

	public void runTest(final String[] words, final String[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { words });

		for (Object answer : answers)
			assertThat((String[]) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}    
    
}
