package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import challenges.AbstractCustomTestRunner;

import java.util.Set;

/**
 * Two Characters
 * 
 * String t always consists of two distinct alternating characters. For example, if string t's two 
 * distinct characters are x and y, then t could be xyxyx or yxyxy but not xxyy or xyyx.
 * 
 * You can convert some string s to string t by deleting characters from s. When you delete a character 
 * from s, you must delete all occurrences of it in s. For example, if s = abaacdabd and you delete the 
 * character a, then the string becomes bcdbd.
 * 
 * Given s, convert it to the longest possible string t. 
 * Then print the length of string t on a new line; if no string t can be formed from s, print 0 instead.
 * 
 * Example:
 * a.		s:	beabeefeab
 * 			Answer: 5
 * 		
 * 			The characters present in s are a, b, e, and f. This means that t must consist of two of those 
 * 			characters.
 * 			
 * 			If we delete e and f, the resulting string is babab. This is a valid t as there are only two 
 * 			distinct characters (a and b), and they are alternating within the string.
 * 
 * 			If we delete a and f, the resulting string is bebeeeb. This is not a valid string t because there 
 * 			are three consecutive e's present.
 * 
 * 			If we delete only e, the resulting string is babfab. This is not a valid string t because it contains 
 * 			three distinct characters.
 * 
 * 			Thus, we print the length of babab, which is 5, as our answer.
 * 
 * @author Hxkandwal
 *
 */
public class TwoCharacters extends AbstractCustomTestRunner {
	
	private static TwoCharacters _instance = new TwoCharacters();
	
	private TwoCharacters() {}
	
	public static int _getTwoCharacterStringOptimized(String s) {
		StringBuilder sb = new StringBuilder(s);
		boolean fine = false;
		
		while (!fine) {
			fine = true;
			for (int idx = 0; idx < sb.length() - 1; idx ++) {
				if (sb.charAt(idx) == sb.charAt(idx + 1)) {
					char ch = sb.charAt(idx);
					fine = false;
					for (int oIdx = 0; oIdx < sb.length(); oIdx ++)
						if (sb.charAt(oIdx) == ch)
							sb.deleteCharAt(oIdx --);
					break;
				}
			}
		}
		
		if (sb.length() <= 1) return 0;
		
		Map<Character, List<Integer>> hash = new HashMap<>();
		int distinct = 0, maxLength = 0, ans = 0;
		
		for (int idx = 0; idx < sb.length(); idx ++) {
			if (!hash.containsKey(sb.charAt(idx))) {
				distinct ++;
				List<Integer> indexes = new ArrayList<>();
				indexes.add(idx);
				hash.put(sb.charAt(idx), indexes);
			} else
				hash.get(sb.charAt(idx)).add(idx);
			
			maxLength = Math.max (maxLength, hash.get(sb.charAt(idx)).size());
		}
		
		if (distinct == 2) return sb.length();
		if (maxLength == 1 && sb.length() > 2) return 0;
	
		while (hash.size() > 1) {
			List<Integer> firstList = null;
			for (Iterator<Map.Entry<Character, List<Integer>>> mapEntrySetIterator = hash.entrySet().iterator(); mapEntrySetIterator.hasNext();) {
				Entry<Character, List<Integer>> entry = mapEntrySetIterator.next();
				if (entry.getValue().size() == maxLength) {
					firstList = entry.getValue();
					mapEntrySetIterator.remove();
					break;
				}
			}
			
			// try overlapping, with candidates
			for (Iterator<Map.Entry<Character, List<Integer>>> mapEntrySetIterator = hash.entrySet().iterator(); mapEntrySetIterator.hasNext();) {
				Entry<Character, List<Integer>> entry = mapEntrySetIterator.next();
				List<Integer> secondList = entry.getValue();
				
				if (firstList.size() <= secondList.size() + 1) {
					// merging
					int firstIdx = 0, secondIdx = 0;
					
					// true - first list used, false - second list used.
					boolean alt = firstList.get(firstIdx) < secondList.get(secondIdx);
					
					if (alt) firstIdx ++; else secondIdx ++;
					
					boolean failed = false;
					while (firstIdx < firstList.size() && secondIdx < secondList.size()) {
						if (alt) {
							if (secondList.get(secondIdx) < firstList.get(firstIdx)) {
								alt = !alt; secondIdx ++;
							} else { failed = true; break; }
						} else {
							if (firstList.get(firstIdx) < secondList.get(secondIdx)) {
								alt = !alt; firstIdx ++;
							} else { failed = true; break; }
						}
					}
					
					if (!failed && !(firstIdx < firstList.size() - 1 || secondIdx < secondList.size() - 1)) 
						ans = Math.max (ans, firstList.size() + secondList.size());
				}
			}
			
			maxLength = 0;
			for (Iterator<Map.Entry<Character, List<Integer>>> mapEntrySetIterator = hash.entrySet().iterator(); mapEntrySetIterator.hasNext();)
				maxLength = Math.max(maxLength, mapEntrySetIterator.next().getValue().size());
		}
		
		return ans;
	}
	
	// to visualize the logic, do a simple dry run.
	public static int getTwoCharacterString(String s) {
		Set<Character> ignoredCharacters = new HashSet<>();
		HashMap<Character, Set<Character>> notpossiblePairMap = new HashMap<>();
		
		// pre-processing.
		for (int idx = 0; idx < s.length() - 1; idx++) 
			if (s.charAt(idx) == s.charAt(idx + 1)) 
				ignoredCharacters.add(s.charAt(idx));		// Permanently ignored
		
		int maxLen = 0;
		
		for (int idx = 0; idx < s.length(); idx++) {
			
			char ch = s.charAt(idx);
			if (!ignoredCharacters.contains(ch)) { 
				for (int innerIdx = idx + 1; innerIdx < s.length(); innerIdx++) {
					char sch = s.charAt(innerIdx);
	
					// augment pre-processing task.
					if (ch == sch) {
						ignoredCharacters.add(sch);
						// breaking as sch == ch, break all future occurrences of ch.
						break;			
					}
				
					if (!ignoredCharacters.contains(sch) && (notpossiblePairMap.containsKey(ch) ? !notpossiblePairMap.get(ch).contains(sch) : true)) {
						int localLen = 2;
						boolean foundAlternative = false;
						for (int innermostIdx = innerIdx + 1; innermostIdx < s.length(); innermostIdx++) {
							if (s.charAt(innermostIdx) == ch || s.charAt(innermostIdx) == sch) {
								if (!foundAlternative && s.charAt(innermostIdx) == sch) {
									localLen = 0;
									if (notpossiblePairMap.containsKey(ch)) 
										notpossiblePairMap.get(ch).add(sch);
									else {
										Set<Character> set = new HashSet<>() ;
										set.add(sch);
										notpossiblePairMap.put(ch, set);
									}
									
									break;
								} else if (foundAlternative && s.charAt(innermostIdx) == ch) {
									localLen = 0;
									if (notpossiblePairMap.containsKey(ch)) 
										notpossiblePairMap.get(ch).add(sch);
									else {
										Set<Character> set = new HashSet<>() ;
										set.add(sch);
										notpossiblePairMap.put(ch, set);
									}
									
									break;
								} else {
									localLen++;
									foundAlternative = !foundAlternative;
								}
							}
						}
						maxLen = Math.max (maxLen, localLen);
					}
				}
			}
		}
		
		return maxLen;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("xyxyzz", 4);
		_instance.runTest("xxyxyyz", 0);
		_instance.runTest("xyxyyzw", 2);
		_instance.runTest("beabeefeab", 5);
		_instance.runTest("xyxyxyzxzyzyxxyz", 0);
		_instance.runTest("abcabccbad", 0);
		_instance.runTest("abcdebfgcb", 5);
		_instance.runTest("pvmaigytciycvjdhovwiouxxylkxjjyzrcdrbmokyqvsradegswrezhtdyrsyhg", 6);
		_instance.runTest("bleinlirvdllgpqysgejejaptjrnmuvsulpwwofocahzbdngybbqdfleycicnpbrdkzmzraayiqzwplgnnmirnddadidsyftrezectrelgwzegsrvzy"
				+ "jramgqvwwotacyurxrpfyrvkrqpcjrezpbizwkzwtucohtcwqktiyrlvxtwuilwvjdhoqaiiqjpkosjkolpgojlfabpperqqtmnjowynwmxavicjdknpgnmpkto"
				+ "vtssynavflaxqbxygryjbfymjfcemqgnhrhyunopicpsskhzkvdnedrweuneshcuoegxzlmcuvojbnqcyapqvnwpfufqpekjvxxujogguhhtvwlrrvctqdllpde"
				+ "gtwwmfnceaiqfpfoqggkqpbmdzhdpsrklllsssazidvcpsipxhucgcqxpekijfijqblnvbrubgqohwpqrngilierbzjrjozslmibpocyzeqrtfenkcklvtajhrz"
				+ "umyjgdkzdaztytogvrncqgcutwdpvnuesbadhfgijjcjygonhvkhlypwnexumozowkfnykdovqjrwnwsudufhvcikaedsfiyzoqyvodmwixpmdpxtveinykvdrj"
				+ "dbmandgzcouzdlpiynwlhcwqafaqpqjdkbouelfbmztbqshzlgedbduhgcerrbqnqzfvgpfheqrnwlsduxklrfjjnkmvetkuzagkdmkaugptrdenqfiavgqzfub"
				+ "ybmjcgoqlmvgcdmddwigtqmvjpkwlkuyxdycuriyrvlbghvyagxulvqmrkxlqfpxblnwdctznlrbbactsrbubcaayntkjmhzjzuyruejekcorvtbglaccnzxhut"
				+ "fqzjrfadgpgubqynmbxziudhmzcpmpx", 0);
		_instance.runTest("tlymrvjcylhqifsqtyyzfaugtibkkghfhyzxqbsizkjguqlqwwetyofqihtpkmpdlgthfybfhhmjerjdkybwppwrdapirukcshkpngayrdruanjtzik"
				+ "nnwxmsjpnuswllymhkmztsrcwwzmlbcoakswwffveobbvzinkhnmvwqhpfednhsuzmffaebi", 0);
	}

	public void runTest(final String input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((int) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
