package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * You categorize strings into three types: good, bad, or mixed. If a string has 3 consecutive vowels or 5 consecutive consonants, 
 * or both, then it is categorized as bad. Otherwise it is categorized as good. 
 * 
 * Vowels in the English alphabet are ["a", "e", "i", "o", "u"] and all other letters are consonants.
 * 
 * The string can also contain the character ?, which can be replaced by either a vowel or a consonant. 
 * This means that the string "?aa" can be bad if ? is a vowel or good if it is a consonant. This kind of string is categorized as mixed.
 * 
 * Implement a function that takes a string s and returns its category: good, bad, or mixed.
 * 
 * Example:
 * 		For s = "aeu", the output should be classifyStrings(s) = "bad";
 * 		For s = "a?u", the output should be classifyStrings(s) = "mixed";
 * 		For s = "aba", the output should be classifyStrings(s) = "good".
 * 
 * link: https://codefights.com/interview/3kHnLbdwLDmySbCpj/companies/gDDsAwPekpst2TjgW
 * 
 * @author Hxkandwal
 */
public class ClassifyStrings extends AbstractCustomTestRunner {
	
	private static ClassifyStrings _instance = new ClassifyStrings();
	
	public String _classifyStrings(String s) {
		Set<Character> vowels = new HashSet<>();
	    vowels.add ('a'); vowels.add ('e'); vowels.add ('i'); vowels.add ('o'); vowels.add ('u'); 
	    
	    int count = 0; boolean vowelFound = false, qmarkFound = false;
	    for (int idx = 0; idx < s.length(); idx ++) {
	        char ch = s.charAt(idx);
	        if (vowels.contains (ch)) {
	            if (vowelFound) { 
	                count ++; 
	                if (count == 3) 
	                    return qmarkFound ? "mixed" : "bad"; 
	            }
	            else count = 1;
	            qmarkFound = false; vowelFound = true;
	        } else if (ch != '?') {
	            if (!vowelFound) { 
	                count ++;
	                if (count == 5) 
	                    return qmarkFound ? "mixed" : "bad"; 
	            }
	            else count = 1;
	            qmarkFound = false; vowelFound = false;
	        } else {
	            if ((vowelFound && count == 2) || (!vowelFound && count == 4)) 
	                return "mixed";
	            count ++; qmarkFound = true;
	        }
	    }
	    return "good";
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("aeu", "bad");
		_instance.runTest("a?u", "mixed");
		_instance.runTest("aba", "good");
		_instance.runTest("abaaa", "bad");
		_instance.runTest("aa??bbb", "mixed");
		_instance.runTest("aa?bbbb", "bad");
		_instance.runTest("aa?bbb?a?bbbb", "bad");
	}

	public void runTest(final String s, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
