package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 636. Exclusive Time of Functions
 * 
 * Given the running logs of n functions that are executed in a nonpreemptive single threaded CPU, find the exclusive time 
 * of these functions. Each function has a unique id, start from 0 to n-1. A function may be called recursively or by another 
 * function.
 * 
 * A log is a string has this format : function_id:start_or_end:timestamp. For example, "0:start:0" means function 0 starts from 
 * the very beginning of time 0. "0:end:0" means function 0 ends to the very end of time 0.
 * 
 * Exclusive time of a function is defined as the time spent within this function, the time spent by calling other functions should 
 * not be considered as this function's exclusive time. You should return the exclusive time of each function sorted by their 
 * function id.
 * 
 * Example 1:
 * 		Input	:	n = 2
 * 					logs =	["0:start:0",
 * 							 "1:start:2",
 * 							 "1:end:5",
 * 							 "0:end:6"]
 * 		Output	:	[3, 4]
 * 
 * 		Explanation	:	Function 0 starts at time 0, then it executes 2 units of time and reaches the end of time 1.
 * 						Now function 0 calls function 1, function 1 starts at time 2, executes 4 units of time and end at time 5.
 * 						Function 0 is running again at time 6, and also end at the time 6, thus executes 1 unit of time.
 * 						So function 0 totally execute 2 + 1 = 3 units of time, and function 1 totally execute 4 units of time.
 * 
 * Note:
 * 	-	Input logs will be sorted by timestamp, NOT log id.
 * 	-	Your output should be sorted by function id, which means the 0th element of your output corresponds to the exclusive time 
 * 		of function 0.
 * 	-	Two functions won't start or end at the same time.
 * 	-	Functions could be called recursively, and will always end.
 * 	-	1 <= n <= 100
 * 
 * @author Hxkandwal
 */
public class ExclusiveTimeOfFunctions extends AbstractCustomTestRunner {
	
	private static ExclusiveTimeOfFunctions _instance = new ExclusiveTimeOfFunctions();

	public int[] _exclusiveTime(int n, List<String> logs) {
        int [] ans = new int [n];
        Stack <int []> stack = new Stack <>();
        int prev = 0;
        for (String log : logs) {
            int id = Integer.valueOf (log.split (":") [0]), time = Integer.valueOf (log.split (":") [2]);
            String action = log.split (":") [1];
            if (action.equals("start")) {
            	if (!stack.isEmpty()) stack.peek () [1] += (time - prev - 1);
            	stack.push (new int [] { id, 1 });
            } else ans [stack.peek () [0]] += stack.pop () [1] + (time - prev);
            prev = time;
        }
        return ans;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(2, Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6"), new int[] { 3, 4 });
		_instance.runTest(1, Arrays.asList("0:start:0", "0:start:2", "0:end:5", "0:start:6", "0:end:6", "0:end:7"), new int[] { 8 });
	}

	public void runTest(final int n, List<String> logs, final int [] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n, logs });

		for (Object answer : answers)
			assertThat((int []) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
