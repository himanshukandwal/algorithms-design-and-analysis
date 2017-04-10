package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Let's map the letters of the English alphabet to powers of 2 as follows:
 * 
 * 		a = 20 = 1, b = 21 = 2, c = 22 = 4, d = 23 = 8, etc.
 * 
 * Then define a sum of a word as the sum of the corresponding powers of two of the word's letters. For example,
 * 
 * sum("abac") = 1 + 2 + 1 + 4 = 8.
 * 
 *  For a word w that contains only English letters we can define its abbreviated form aw as follows:
 *  a)	aw also contains only English letters;
 *  b)	w and aw have the same sum as defined above;
 *  c)	the letters of aw are placed in a strictly increasing alphabetical order.

 * link: https://codefights.com/tournaments/CXkkSme95dZedcwPm/C
 * 
 * @author Hxkandwal
 */
public class WordAbbreviation extends AbstractCustomTestRunner {
	
	private static WordAbbreviation _instance = new WordAbbreviation();
	
	private WordAbbreviation() {}
	
	public static String _wordAbbreviation2(String a) {
	    int sum = 0;
	    for (int idx = 0; idx < a.length(); idx ++)
	        sum += (int) Math.pow(2, a.charAt(idx) - 'a');
	    
	    StringBuffer ans = new StringBuffer();
	    int [] binaryAns = new int [ (int) Math.ceil((Math.log(sum) / Math.log(2))) + 1];
	    int bIdx = 0;
	    
	    while (sum / 2 > 0 || sum % 2 > 0) {
	    	binaryAns [bIdx ++] = sum % 2;
	    	sum /= 2;
	    }
	    
	    for (bIdx = binaryAns.length - 1; bIdx >= 0; bIdx --)
	    	if (binaryAns [bIdx] > 0)
	    		ans.append("" + ((char) ('a' + bIdx)));
	    
	    return ans.reverse().toString();
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("abac", "d");
		_instance.runTest("aaaaa", "ac");
		_instance.runTest("abcabc", "bcd");		
	}

	public void runTest(final String s, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}

}
