package challenges.codefights;

import java.util.ArrayList;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Binary Generator
 * 
 * Let's say that binary sequence s generates binary sequence t if: s and t are of the same length; 
 * for each i such that s[i] = 1 it is true that t[i] = 1.
 * 
 * Given a sequence s, return an array of all the sequences it generates, sorted lexicographically.
 * 
 * Example :
 * 	For s = "01101", the output should be binaryGenerator(s) = ["01101", "01111", "11101", "11111"].
 * 
 * @author Hxkandwal
 */
public class BinaryGenerator extends AbstractCustomTestRunner {

	public String[] binaryGenerator(String s) {
	    List<String> ans = binaryGeneratorInner(s, 0);
	    return ans.toArray (new String [0]);
	}

	public List<String> binaryGeneratorInner (String s, int index) {
	    List<String> ans = new ArrayList<>();
	    if (index < s.length()) {
	        List<String> next = binaryGeneratorInner (s, index + 1);
	        if (s.charAt (index) == '0')
	            for (String str : next) ans.add ('0' + str);
	        for (String str : next) ans.add ('1' + str);
	    } else ans.add ("");   
	    return ans;
	}

}
