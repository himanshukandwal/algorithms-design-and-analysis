package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

public class TargetSum extends AbstractCustomTestRunner {
	
	private static TargetSum _instance = new TargetSum();
	
	private TargetSum() {}
	
	public static boolean _checkPossible(int[] batteryOne, int[] batteryTwo, int sum) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int idx = 0; idx < batteryOne.length; idx ++) {
			int diff = sum - batteryOne [idx];
			
			if (!map.containsKey(diff)) 
				map.put(diff, batteryOne [idx]);
		}
		
		for (int idx = 0; idx < batteryTwo.length; idx ++)
			if (map.containsKey(batteryTwo [idx])) 
				return true;
		
		return false;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 5, 7, 2, 4 }, new int[] { -3, 0, 1 }, 8, true);
		_instance.runTest(new int[] { 6, 3, 1, 9, 5, 4, 0, 1, -29, 12, 45, 2, 6 }, 
						  new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 }, 100, false);
	}

	public void runTest(final int[] batteryOne, final int[] batteryTwo, final int sum, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { batteryOne, batteryTwo, sum });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
