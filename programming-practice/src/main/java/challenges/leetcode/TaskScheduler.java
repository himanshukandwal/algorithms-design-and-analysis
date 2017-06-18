package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

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

	public int _leastInterval(char[] tasks, int n) {
		Map<Character, Integer> map = new HashMap<>();
		for (char ch : tasks) map.put (ch, map.getOrDefault (ch, 0) + 1);
		char maxch = tasks [0];
        for (Character ch : map.keySet()) if (map.get(ch) > map.get(maxch)) maxch = ch;
        
        int ans = 0;
        while (map.size() > 0) {
        	if (!map.containsKey(maxch)) maxch = map.keySet().iterator().next();
        	if (map.put (maxch, map.get (maxch) - 1) == 1) map.remove (maxch);
        	ans ++;
        	int itr = 0;
            for (Iterator <Character> it = map.keySet().iterator(); it.hasNext() && itr < n;) {
                Character ch = it.next ();
                if (ch == maxch) continue;
                if (map.put (ch, map.get (ch) - 1) == 1) it.remove ();
                itr ++;
            }
            ans += itr + (map.size() > 0 ? n - itr : 0);
        }
        return ans;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new char[] { 'A', 'B', 'B' }, 2, 4);
		_instance.runTest(new char[] { 'A', 'B', 'B', 'C' }, 2, 4);
		_instance.runTest(new char[] { 'A', 'A', 'A', 'B', 'B', 'B' }, 0, 6);
		_instance.runTest(new char[] { 'A', 'A', 'A', 'B', 'B', 'B' }, 2, 8);
	}

	public void runTest(final char[] tasks, final int n, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { tasks, n });

		for (Object answer : answers)
			assertThat((int) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
}
