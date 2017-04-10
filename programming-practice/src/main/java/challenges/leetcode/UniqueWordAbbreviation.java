package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * 288. Unique Word Abbreviation
 * 
 * @author Hxkandwal
 */
public class UniqueWordAbbreviation extends AbstractCustomTestRunner {

	Map<String, Set<String>> abbr = new HashMap<>();
    
    public UniqueWordAbbreviation (String[] dictionary) {
        for (String di : dictionary) {
            if (di.length() <= 2) {
                abbr.put (di, abbr.getOrDefault (di, new HashSet<>()));
                abbr.get (di).add (di);
            } else {
                String abv = String.valueOf(di.charAt(0)) + (di.length() - 2) + String.valueOf(di.charAt (di.length() - 1));
                abbr.put (abv, abbr.getOrDefault (abv, new HashSet<>()));
                abbr.get (abv).add (di);
            }
        }
    }
    
    public boolean isUnique(String word) {
        String abv = (word.length() <= 2) ? word : String.valueOf(word.charAt(0)) + (word.length() - 2) + String.valueOf(word.charAt (word.length() - 1));
        return !abbr.containsKey (abv) || (abbr.get (abv).size () == 1 && abbr.get (abv).contains (word));
    }
    
	// driver method
	public static void main(String[] args) {
		UniqueWordAbbreviation abbreviation = new UniqueWordAbbreviation(new String[] { "door", "naive" });
		
		assertThat(abbreviation.isUnique("cat")).isEqualTo(true);
		assertThat(abbreviation.isUnique("cat")).isEqualTo(true);
		System.out.println("ok!");
	}
	
}
