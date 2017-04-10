package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * String detection from parts.
 * 
 * @author Hxkandwal
 *
 */
public class StringDetectionFromParts extends AbstractCustomTestRunner {
	
	private static StringDetectionFromParts _instance = new StringDetectionFromParts();
	
	private StringDetectionFromParts() {}

	public static boolean _canBeConstructed(String part1, String part2, String string) {
		List<AbstractMap.SimpleEntry<Integer, Integer>> entryList = new ArrayList<>();
		
		if (part1.length() + part2.length() == string.length()) {
			int index1 = 0, index2 = 0, index = 0;
			
			while (index < string.length()) {
				char ch = string.charAt(index ++);
				
				if ((index1 < part1.length() && part1.charAt(index1) == ch) && (index2 < part2.length() && part2.charAt(index2) == ch)) 
					entryList.add(new AbstractMap.SimpleEntry<Integer, Integer>(index1, index2));
					
				if (index1 < part1.length() && part1.charAt(index1) == ch) {
					index1 ++;
				} else if (index2 < part2.length() && part2.charAt(index2) == ch) 
					index2 ++;
				else {
					if (entryList.size() == 0)
						return false;
					else {
						// backtracking.
						AbstractMap.SimpleEntry<Integer, Integer> lastEntry = entryList.remove(entryList.size() - 1);
						index1 = lastEntry.getKey();
						index2 = lastEntry.getValue();
						index = index1 + index2;
						
						index2 ++;
						index ++;
					}
				}	
			}
			return true;
		}
		
		return false;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("zps", "apo", "zappos", true);
		_instance.runTest("apo", "zps", "zappos", true);
		_instance.runTest("alg", "ano", "analog", true);
		_instance.runTest("zpsc", "apote", "zappostech", false);
		_instance.runTest("zpscho", "apote","zappostech", false);
		_instance.runTest("hewodr", "llol", "helloworld", false);
		_instance.runTest("heword", "llol", "helloworld", true);
		_instance.runTest("agths", "lorim", "algorithms", true);
		_instance.runTest("zappos", "tech", "zappostech", true);
		_instance.runTest("ppos", "zatech", "zappostech", true);
		_instance.runTest("ppos", "zatecha", "zappostech", false);
		_instance.runTest("pposa", "zatech", "zappostech", false);
		_instance.runTest("ppos", "zatech", "zappostecha", false);
		_instance.runTest("zappostecha", "", "zappostecha", true);
		_instance.runTest("", "zappostecha", "zappostecha", true);
		_instance.runTest("", "zappostecha", "zappostech", false);
		_instance.runTest("", "zappostech", "zappostech", true);
		_instance.runTest("", "abc", "abc", true);
		_instance.runTest("abc", "abc", "", false);
		_instance.runTest("abc", "abc", "ababcc", true);
		_instance.runTest("aaabda", "aabdca", "aabdcaaabdaa", true);
	}
	
	public void runTest(final String part1, final String part2, final String string, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { part1, part2, string });
		
		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
