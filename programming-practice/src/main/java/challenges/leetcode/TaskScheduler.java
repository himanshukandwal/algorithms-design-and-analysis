package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 621. Task Scheduler
 * 
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent 
 * different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU 
 * could finish one task or just be idle.
 * 
 * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that 
 * CPU are doing different tasks or just be idle.
 * 
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 * 
 * Example 1:
 * 		Input: tasks = ['A','A','A','B','B','B'], n = 2
 * 		Output: 8
 * 		Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 * 
 * Note:
 * 	-	The number of tasks is in the range [1, 10000].
 * 	-	The integer n is in the range [0, 100].
 * 
 * @author Hxkandwal
 */
public class TaskScheduler extends AbstractCustomTestRunner {
	
	private static TaskScheduler _instance = new TaskScheduler();

	public int _leastIntervalMath(char[] tasks, int n) {
		int [] arr = new int [26];
		for (char c : tasks) arr [c - 'A'] ++;
		Arrays.sort (arr);

		int i = 25;
		while (i >= 0 && arr [i] == arr [25]) i --;
		return Math.max(tasks.length, (arr [25] - 1) * (n + 1) + (25 - i));
    }
	
	public int _leastIntervalBrilliant (char[] tasks, int n) {
		int [] map = new int [26], build = new int [26];
        for (char ch : tasks) map [ch - 'A'] ++;
        int ans = 0, num = tasks.length;
        
        while (num > 0) {
            ans ++;				 
            int maxIdx = -1;
            for (int idx = 0; idx < map.length; idx ++) 
                if ((build [idx] == 0 || ans - build [idx] > n) && map [idx] > 0)	// magic happens here.
                    if (maxIdx == -1 || map [maxIdx] < map [idx]) maxIdx = idx;
                    
            if (maxIdx != -1) { build [maxIdx] = ans; map [maxIdx] --; num --; }
        }
        return ans;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new char[] { 'A', 'B', 'B' }, 2, 4);
		_instance.runTest(new char[] { 'A', 'B', 'B', 'C' }, 2, 4);
		_instance.runTest(new char[] { 'A', 'B', 'B', 'B', 'C' }, 1, 5);
		_instance.runTest(new char[] { 'A', 'A', 'A', 'B', 'B', 'B' }, 0, 6);
		_instance.runTest(new char[] { 'A', 'A', 'A', 'B', 'B', 'C' }, 0, 6);
		_instance.runTest(new char[] { 'A', 'A', 'A', 'B', 'B', 'B' }, 2, 8);
	}

	public void runTest(final char[] tasks, final int n, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { tasks, n });

		for (Object answer : answers)
			assertThat((int) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
}
