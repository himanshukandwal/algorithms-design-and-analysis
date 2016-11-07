package me.hxkandwal.daily.challanges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
	
	// to visualize the logic, do a simple dry run.
	public static int _getTwoCharacterString(String s) {
		Set<Character> ignoredCharacters = new HashSet<>();
		
		// pre-processing.
		for (int idx = 0; idx < s.length() - 1; idx++) 
			if (s.charAt(idx) == s.charAt(idx + 1)) 
				ignoredCharacters.add(s.charAt(idx));		// permenantly ignored
		
		int maxLen = 0;
		
		for (int idx = 0; idx < s.length(); idx++) {
			char ch = s.charAt(idx);
			if (!ignoredCharacters.contains(ch)) { 
				for (int innerIdx = idx + 1; innerIdx < s.length(); innerIdx++) {
					char sch = s.charAt(innerIdx);
	
					// augment pre-processing task.
					if (ch == sch) {
						ignoredCharacters.add(sch);
						// breaking as sch == ch, break all future occurences of ch.
						break;			
					}
						
					if (!ignoredCharacters.contains(sch)) {
						int localLen = 2;
						boolean foundAlternative = false;
						for (int innermostIdx = innerIdx + 1; innermostIdx < s.length(); innermostIdx++) {
							if (s.charAt(innermostIdx) == ch || s.charAt(innermostIdx) == sch) {
								if (!foundAlternative && s.charAt(innermostIdx) == sch) {
									localLen = 0;
									break;
								} else if (foundAlternative && s.charAt(innermostIdx) == ch) {
									localLen = 0;
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
