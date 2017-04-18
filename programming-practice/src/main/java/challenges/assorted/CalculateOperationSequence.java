package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * Calculate Operation Sequence
 * 
 * As you know, two operations of Stack are push and pop. Now give you two integer arrays, one is the original array before push and pop 
 * operations, the other one is the result array after a series of push and pop operations to the first array. Please give the push and 
 * pop operation sequence.
 * 
 * For example:
 * 	If the original array is a[] = {1,2,3}, and the result array is b[] = {1,3,2}.
 * 	Then, the operation sequence is “push1|pop1|push2|push3|pop3|pop2”(operations are split by ‘|’ and no space).
 * 	
 * 	Rules:
 * 		The push and pop operations deal with the original int array from left to right.
 * 		The input is two integer array. They are the original array and the result array. These interger array is split by space.
 * 		The output is the operation sequence.
 * 		If the original array cannot make to the result array with stack push and pop, The output should be 'None'.
 * 		The operation "push1" means push the first element of the original array to the stack.
 * 		The operation "pop1" means pop the first element of the original array from the stack, and add this element to the tail of the
 * 			result array.
 * 		Please don't include any space in the output string.
 * 
 * link: https://github.com/windynight/InterviewPuzzle/blob/master/CalculateOperationSequence.cpp
 *  	
 * @author Hxkandwal
 */
public class CalculateOperationSequence extends AbstractCustomTestRunner {
	
	private static CalculateOperationSequence _instance = new CalculateOperationSequence();

	public String _sequence(int[] a, int [] b) {
		StringBuilder ans = new StringBuilder ();
		int aidx = 0, bidx = 0;
		Stack<Integer> stack = new Stack<>();
		while (aidx < a.length) {
			while (aidx < a.length && a [aidx] != b [bidx]) { ans.append ("push" + a [aidx] + "|"); stack.push(a [aidx ++]); }
			if (a [aidx] == b [bidx]) { 
				if (stack.isEmpty() || stack.peek() != a [aidx]) ans.append ("push" + a [aidx] + "|");
				else stack.pop();
				ans.append("pop" + a [aidx] + "|"); 
				aidx ++; bidx ++; 
			}
		}
		while (bidx < b.length) ans.append("pop" + b [bidx ++] + "|");
		if (ans.charAt(ans.length() - 1) == '|') ans.deleteCharAt(ans.length() - 1);
		return ans.toString();
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 2, 3 }, new int[] { 1, 3, 2 }, "push1|pop1|push2|push3|pop3|pop2");
		_instance.runTest(new int[] { 1, 2, 3, 4 }, new int[] { 1, 2, 3, 4 }, "push1|pop1|push2|pop2|push3|pop3|push4|pop4");
		_instance.runTest(new int[] { 1, 2, 3, 4 }, new int[] { 4, 3, 2, 1 }, "push1|push2|push3|push4|pop4|pop3|pop2|pop1");
	}

	public void runTest(final int[] a, final int [] b, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a, b });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}