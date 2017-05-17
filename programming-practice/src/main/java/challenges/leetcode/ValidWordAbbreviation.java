package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 408. Valid Word Abbreviation
 * 
 * Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
 * 
 * A string such as "word" contains only the following valid abbreviations:
 * 
 * 		["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * 
 * Notice that only the above abbreviations are valid abbreviations of the string "word". 
 * Any other string is not a valid abbreviation of "word".
 * 
 * Note: Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.
 * 
 * Example 1:	Given s = "internationalization", abbr = "i12iz4n":
 * 				Return true.
 * 
 * Example 2:	Given s = "apple", abbr = "a2e":
 * 				Return false.
 * 
 * @author Hxkandwal
 */
public class ValidWordAbbreviation extends AbstractCustomTestRunner {
	
	private static ValidWordAbbreviation _instance = new ValidWordAbbreviation();

	public boolean _validWordAbbreviation(String word, String abbr) {
        if (word.length() == 0) return abbr.length() == 0;
        int idx = 0, aidx = 0;
        while (idx < word.length() && aidx < abbr.length ()) {
            if (isNumber (abbr.charAt (aidx))) {
            	if (abbr.charAt (aidx) == '0') return false;					// corner case : number cannot start with 0.
                int num = 0;
                while (aidx < abbr.length () && isNumber (abbr.charAt (aidx))) 
                    num = num * 10 + (abbr.charAt (aidx ++) - '0');
                if (idx + num > word.length()) return false;
                idx += num;
            } else if (word.charAt (idx ++) != abbr.charAt (aidx ++)) return false;
        }
        return idx == word.length() && aidx == abbr.length ();
    }
    
    private boolean isNumber (char ch) {
        return ch >= '0' && ch <= '9';
    }
    
    // Much nicer, I just turn an abbreviation like "i12iz4n" into a regular expression like "i.{12}iz.{4}n". Duh.
    public boolean _validWordAbbreviationSimple(String word, String abbr) {
        return word.matches(abbr.replaceAll("[1-9]\\d*", ".{$0}"));
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("internationalization", "i12iz4n", true);
		_instance.runTest("apple", "a2e", false);
	}

	public void runTest(final String word, final String abbr, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { word, abbr });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
