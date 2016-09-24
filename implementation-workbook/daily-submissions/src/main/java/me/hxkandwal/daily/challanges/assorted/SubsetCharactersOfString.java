package me.hxkandwal.daily.challanges.assorted;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * program that creates subsets of the characters of a string and prints and returns the count of all.
 * 
 * @author Hxkandwal
 *
 */
public class SubsetCharactersOfString extends AbstractCustomTestRunner {

	private static SubsetCharactersOfString _instance = new SubsetCharactersOfString();

	private SubsetCharactersOfString() {}

	public static String[] _buildSubsequences(String s) {
		if (s.length() == 0)
			return new String[] {};

		return permuteInner("", s).toArray(new String[0]);
	}


	private static List<String> permuteInner(String soFar, String remaining) {
		if (remaining.length() == 0) {
			System.out.println(" > " + soFar);
			if (soFar.length() > 0) {
				List<String> list = new ArrayList<>();
				list.add(soFar);
				return list;
			}
			else {
				return new ArrayList<>();
			}

		}

		List<String> out = new ArrayList<>();
		out.addAll(permuteInner(soFar + remaining.charAt(0), remaining.substring(1)));
		out.addAll(permuteInner(soFar, remaining.substring(1)));
		return out;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("abc", 2);
		_instance.runTest("123", 6);
	}

	public void runTest(final String input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
