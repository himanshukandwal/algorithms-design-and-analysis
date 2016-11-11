package me.hxkandwal.daily.challanges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Gemstones
 * 
 * John has discovered various rocks. Each rock is composed of various elements, and each element is 
 * represented by a lower-case Latin letter from 'a' to 'z'. An element can be present multiple times 
 * in a rock. An element is called a gem-element if it occurs at least once in each of the rocks.
 * 
 * Given the list of N rocks with their compositions, display the number of gem-elements that exist in 
 * those rocks.
 *
 * Example:
 * a.		Input:	abcdde	baccd	eeabg
 * 			Output:	2
 * 
 * @author Hxkandwal
 *
 */
public class Gemstones extends AbstractCustomTestRunner {
	
	private static Gemstones _instance = new Gemstones();
	
	private Gemstones() {}
	
	// can also be done by set + retainAll method.
	public static int _getGemstones(String[] strings) {
		int [] global_alphabets = new int ['z' - 'a' + 1];
		for (String s : strings) {
			int [] local_alphabets = new int ['z' - 'a' + 1];
			for (int idx = 0; idx < s.length(); idx ++)
				local_alphabets [s.charAt(idx) - 'a'] = (local_alphabets [s.charAt(idx) - 'a'] == 0) ? 1 : local_alphabets [s.charAt(idx) - 'a'];
			
			for (int idx = 0; idx < local_alphabets.length; idx ++)
				global_alphabets [idx] += local_alphabets [idx];
		}
		
		int count = 0;
		for (int idx = 0; idx < global_alphabets.length; idx++)
			if (global_alphabets [idx] == strings.length)
				count ++;
		
		return count;
	}
	
    // driver method
    public static void main(String[] args) {
        _instance.runTest(new String[] { "abcdde", "baccd", "eeabg" }, 2);
    }

    public void runTest(final String[] input, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { input });

        for (Object answer : answers)
            assertThat((int) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
