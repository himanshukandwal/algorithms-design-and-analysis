package challenges.leetcode;

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
public class LetterCombinationsOfAPhoneNumber extends AbstractCustomTestRunner {
	
	public List<String> _letterCombinations(String digits) {
        if (digits.length () == 0) return Arrays.asList();
        Map<Character, List<String>> map = new HashMap<>();
        map.put ('0', Arrays.asList(""));
        map.put ('1', Arrays.asList(""));
        map.put ('2', Arrays.asList("a", "b", "c"));
        map.put ('3', Arrays.asList("d", "e", "f"));
        map.put ('4', Arrays.asList("g", "h", "i"));
        map.put ('5', Arrays.asList("j", "k", "l"));
        map.put ('6', Arrays.asList("m", "n", "o"));
        map.put ('7', Arrays.asList("p", "q", "r", "s"));
        map.put ('8', Arrays.asList("t", "u", "v"));
        map.put ('9', Arrays.asList("w", "x", "y", "z"));
        return dfs (map, digits, 0);
    }
    
    private List<String> dfs (Map<Character, List<String>> map, String digits, int idx) {
        if (idx == digits.length ()) return Arrays.asList("");
        List<String> ans = new ArrayList<>();
        for (String str : dfs (map, digits, idx + 1)) 
            for (String pre : map.get (digits.charAt (idx))) ans.add (pre + str);
        return ans;
    }
	
}
