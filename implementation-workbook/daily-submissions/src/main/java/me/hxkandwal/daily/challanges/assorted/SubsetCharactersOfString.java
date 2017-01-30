package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * program that creates subsets of the characters of a string and prints and returns the count of all.
 * 
 * @author Hxkandwal
 *
 */
public class SubsetCharactersOfString extends AbstractCustomTestRunner {

	private static SubsetCharactersOfString _instance = new SubsetCharactersOfString();

	private SubsetCharactersOfString() {}

    // method 1 : calculation of subsets using remaining string manipulation
	public static int _buildSubsequences(String s) {
		if (s.length() == 0)
		    return 0;

		Set<String> subsets = new HashSet<>();
		buildSubsequencesInner("", s, subsets);

		System.out.println(subsets);
		return subsets.size();
	}

	private static void buildSubsequencesInner(String soFar, String remaining, Set<String> subsets) {
        if (remaining.length() == 0) {
            if (soFar.length() > 0)
                subsets.add(soFar);
        } else {
            // with inclusion
            buildSubsequencesInner(soFar + remaining.charAt(0), remaining.substring(1), subsets);

            // without inclusion
            buildSubsequencesInner(soFar, remaining.substring(1), subsets);
        }
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("abc", 7);
	}

	public void runTest(final String input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
