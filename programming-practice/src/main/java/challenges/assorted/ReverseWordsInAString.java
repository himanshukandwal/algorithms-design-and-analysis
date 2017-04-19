package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Reverse Words in a String
 * 
 * http://www.ardendertat.com/2011/10/31/programming-interview-questions-12-reverse-words-in-a-string/
 * 
 * @author Hxkandwal
 */
public class ReverseWordsInAString extends AbstractCustomTestRunner {
	
	private static ReverseWordsInAString _instance = new ReverseWordsInAString();
	
	public String _reverse (String input) {
		StringBuilder ans = new StringBuilder();
        input = " " + input.trim();
        int start = input.length ();
        for (int idx = input.length () - 1; idx >= 0; idx --) {
            if (input.charAt(idx) == ' ') {
                if (start != idx + 1)
                    ans.append(input.substring (idx + 1, start)).append (" ");
                start = idx;
            }
        }
        return ans.toString().trim();
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("A CS degree", "degree CS A");
	}
	
	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers) 
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
