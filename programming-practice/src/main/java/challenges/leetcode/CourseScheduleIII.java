package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import challenges.AbstractCustomTestRunner;

/**
 * 630. Course Schedule III
 * 
 * There are n different online courses numbered from 1 to n. Each course has some duration(course length) t and closed on dth day. A course 
 * should be taken continuously for t days and must be finished before or on the dth day. You will start at the 1st day.
 * 
 * Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.
 * 
 * Example:
 * 		Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 * 		Output: 3
 * 		Explanation:
 * 			There're totally 4 courses, but you can take 3 courses at most:
 * 			- First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 
 * 				101st day.
 * 			- Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 
 * 				1101st day. (If you take the 2nd course here, you will end at 300th day and find that no more courses you could take.)
 * 			- Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
 * 			- The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 * 
 * Note:
 * 	-	The integer 1 <= d, t, n <= 10,000.
 * 	-	You can't take two courses simultaneously.
 * 
 * @author Hxkandwal
 */
public class CourseScheduleIII extends AbstractCustomTestRunner {
	
	private static CourseScheduleIII _instance = new CourseScheduleIII();

	public int _scheduleCourse(int[][] courses) {
		Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();        
        int s = 0;
		for (int idx = 0; idx < courses.length; idx ++) {
        	int [] course = courses [idx];
        	s += course [0];
        	pq.add (- course [0]);
			while (s > course [1]) s += pq.poll();
        }
        return pq.size();
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[][] {{ 5,15 }, { 3,19 },
									   { 6, 7 }, { 2, 10 },
									   { 5, 16 }, { 8, 14 },
									   { 10, 11 }, { 2, 19 } }, 5);
		_instance.runTest(new int[][] {{ 100, 200 }, { 200, 1300 }, { 1000, 1250 }, { 2000, 3200 }}, 3);
	}

	public void runTest(final int[][] courses, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { courses });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
}
