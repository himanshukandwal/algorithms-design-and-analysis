package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import java.util.Stack;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Program to validate events sequence.
 * 
 * @author Hxkandwal
 *
 */
public class ValidEventSequence extends AbstractCustomTestRunner {
	
	private static ValidEventSequence _instance = new ValidEventSequence();
	
	private ValidEventSequence() {}

	public static boolean _validate(String[] events) {
		String input = "ACQUIRE";
		String output = "RELEASE";
		
		boolean correct = true;
		Stack<Integer> eventStack = new Stack<>();
		for (String event : events) {
			if (event.startsWith(input))
				eventStack.push(Integer.valueOf(event.substring(input.length()).trim()));
			else {
				if (event.startsWith(output)) {
					int popValue = Integer.valueOf(event.substring(output.length()).trim());
					if (eventStack.size() == 0 || eventStack.pop() != popValue) {
						correct = false;
						break;
					}
				} else {
					correct = false;
					break;
				}
			}
		}
		
		if (eventStack.size() > 0)
			correct = false;
		
		return correct;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new String[] { "ACQUIRE 364", "ACQUIRE 84", "ACQUIRE 364" }, false);
		_instance.runTest(new String[] { "ACQUIRE 364", "ACQUIRE 84", "RELEASE 84", "RELEASE 364" }, true);
		_instance.runTest(new String[] { "ACQUIRE 364", "ACQUIRE 84", "RELEASE 84" }, false);
		_instance.runTest(new String[] { "ACQUIRE 364", "ACQUIRE 84", "RELEASE 364", "RELEASE 84" }, false);
	}

	public void runTest(final String[] input, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
