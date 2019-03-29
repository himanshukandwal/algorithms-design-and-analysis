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
 * An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:
 *
 *          a) it                      --> it    (no abbreviation)
 *
 *            1
 *            ↓
 *          b) d|o|g                   --> d1g
 *
 *                       1    1  1
 *              1---5----0----5--8
 *              ↓   ↓    ↓    ↓  ↓
 *         c) i|nternationalizatio|n  --> i18n
 *
 *                   1
 *               1---5----0
 *               ↓   ↓    ↓
 *          d) l|ocalizatio|n          --> l10n
 *
 * Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary.
 * A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
 *
 * Example:
 *          Given dictionary = [ "deer", "door", "cake", "card" ]
 *
 *          isUnique("dear") -> false
 *          isUnique("cart") -> true
 *          isUnique("cane") -> false
 *          isUnique("make") -> true
 * 
 * @author Hxkandwal
 */
public class UniqueWordAbbreviation extends AbstractCustomTestRunner {

	Map<String, Set<String>> map = new HashMap<>();
    
    public UniqueWordAbbreviation (String[] dictionary) {
        for (String w : dictionary)
            if (w.length() >= 2) {
                String abbr = "" + w.charAt(0) + w.substring(1, w.length() - 1).length() + w.charAt(w.length() - 1);
                map.computeIfAbsent(abbr, k -> new HashSet<>()).add (w);
            }
    }
    
    public boolean isUnique(String w) {
        if (w.length() < 2) return true;
        String abbr = "" + w.charAt(0) + w.substring(1, w.length() - 1).length() + w.charAt(w.length() - 1);

        return  !map.containsKey(abbr) || (map.get(abbr).contains(w) && map.get(abbr).size() == 1);
    }
    
	// driver method
	public static void main(String[] args) {
		UniqueWordAbbreviation abbreviation = new UniqueWordAbbreviation(new String[] { "door", "naive" });
		
		assertThat(abbreviation.isUnique("cat")).isEqualTo(true);
		assertThat(abbreviation.isUnique("cat")).isEqualTo(true);
		System.out.println("ok!");
	}
	
}
