package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 17. Letter Combinations of a Phone Number
 * 
 * Given a digit string, return all possible letter combinations that the number could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * 
 * Input	: Digit string "23"
 * Output	: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * Note: Although the above answer is in lexicographical order, your answer could be in any order you want.
 * 
 * @author Hxkandwal
 */
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class LetterCombinationsOfAPhoneNumber extends AbstractCustomTestRunner {
	
	private static LetterCombinationsOfAPhoneNumber _instance = new LetterCombinationsOfAPhoneNumber();
	
	private LetterCombinationsOfAPhoneNumber() {}
	
    public List<String> _letterCombinations(String digits) {
    	if (digits == null || digits.length() == 0) return new ArrayList<>();
    	
    	Map<Character, List<String>> dict = new HashMap<>();
    	dict.put('1', new ArrayList<>());
    	dict.put('2', Arrays.asList("a", "b", "c"));
    	dict.put('3', Arrays.asList("d", "e", "f"));
    	dict.put('4', Arrays.asList("g", "h", "i"));
    	dict.put('5', Arrays.asList("j", "k", "l"));
    	dict.put('6', Arrays.asList("m", "n", "o"));
    	dict.put('7', Arrays.asList("p", "q", "r", "s"));
    	dict.put('8', Arrays.asList("t", "u", "v"));
    	dict.put('9', Arrays.asList("w", "x", "y", "z"));
    	return letterCombinationsRecursion(dict, digits, 0);
    }
    
    public List<String> letterCombinationsRecursion (Map<Character, List<String>> dict, String digits, int idx) {
    	List<String> answer = new ArrayList<>();
    	if (idx == digits.length() - 1) return dict.get(digits.charAt(digits.length() - 1));
    	
    	for (String next : letterCombinationsRecursion(dict, digits, idx + 1))
    		for (String current : dict.get(digits.charAt(idx)))  answer.add(current + next);
    	
    	return answer;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("23", new ArrayList() {{ add ("ab"); }});
	}

	public void runTest(final String digits, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { digits });

		for (Object answer : answers)
			assertThat((List<String>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
