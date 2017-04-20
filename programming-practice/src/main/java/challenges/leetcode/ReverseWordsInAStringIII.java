package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 557. Reverse Words in a String III
 * 
 * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving 
 * whitespace and initial word order.
 * 
 * Example 1:
 * 		Input: "Let's take LeetCode contest"
 * 		Output: "s'teL ekat edoCteeL tsetnoc"
 * 
 * @author Hxkandwal
 */
public class ReverseWordsInAStringIII extends AbstractCustomTestRunner {
	
	private static ReverseWordsInAStringIII _instance = new ReverseWordsInAStringIII();

	public String _reverseWords(String s) {
        char [] charr = (s.trim() + " ").toCharArray();
        for (int idx = 0, start = 0; idx < charr.length; idx ++)
            if (charr [idx] == ' ') 
            	{ reverse (charr, start, idx); start = idx + 1; }
        return String.valueOf(charr).trim();
    }
    
    private void reverse (char [] charr, int from, int to) {
        for (int idx = 0; idx < (to - from)/2; idx ++) {
            char ch = charr [from + idx];
            charr [from + idx] = charr [to - idx - 1];
            charr [to - idx - 1] = ch;
        }    
    }
    
 	// driver method
 	public static void main(String[] args) {
 		_instance.runTest("Let's take LeetCode contest", "s'teL ekat edoCteeL tsetnoc");
 	}

 	public void runTest(final String s, final String expectedOutput) {
 		List<Object> answers = runAll(getClass(), new Object[] { s });

 		for (Object answer : answers)
 			assertThat((String) answer).isEqualTo(expectedOutput);

 		System.out.println("ok!");
 	}
}
