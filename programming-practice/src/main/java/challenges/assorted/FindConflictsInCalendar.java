package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * link: https://www.youtube.com/watch?v=olK6SWl8UrM
 * 
 * @author Hxkandwal
 */
@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
public class FindConflictsInCalendar extends AbstractCustomTestRunner {
	
	private static FindConflictsInCalendar _instance = new FindConflictsInCalendar();
	
	private FindConflictsInCalendar() {}
	
	public static List<Integer> _getConflicts (int[][] calendar) {
		if (calendar == null || calendar.length <= 1)
			return null;
		
		List<Integer> answer = new ArrayList<>();
		List<Integer> localAnswer = new ArrayList<>();
		
		int end = -1;
		for (int idx = 0; idx < calendar.length; idx ++) {
			if (idx == 0) {
				end = calendar [idx][1];
				localAnswer.add(calendar [idx][2]);
				continue;
			} 
			
			if (calendar [idx][0] >= end) {
				if (localAnswer.size() > 1) 
					answer.addAll(localAnswer);
				
				localAnswer.clear();
			}
			
			localAnswer.add(calendar [idx][2]);
			end = calendar [idx][1];
		}
		
		if (localAnswer.size() > 1) 
			answer.addAll(localAnswer);
		
		return answer;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[][] {
			new int [] { 1, 2, 1 }, new int [] { 3, 5, 2 },
			new int [] { 4, 6, 3 }, new int [] { 7, 10, 4 },
			new int [] { 8, 11, 5 }, new int [] { 10, 12, 6 },
			new int [] { 13, 14, 7 }, new int [] { 13, 14, 8 },
		}, new ArrayList() {{ add(2); add(3); add(4); add(5); add(6); add(7); add(8); }});
	}

	public void runTest(final int[][] calendar, final List<Integer> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { calendar });

		for (Object answer : answers)
			assertThat((List<Integer>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
