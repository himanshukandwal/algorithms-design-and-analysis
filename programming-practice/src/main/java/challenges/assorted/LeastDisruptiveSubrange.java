package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * link: https://www.youtube.com/watch?v=1wMBw38rAlw
 * 
 * @author Hxkandwal
 */
public class LeastDisruptiveSubrange extends AbstractCustomTestRunner {
	
	private static LeastDisruptiveSubrange _instance = new LeastDisruptiveSubrange();
	
	private LeastDisruptiveSubrange() {}
	
	public static int _leastDisruption(int [] original, int [] replacement) {
		int replacementSum = 0, originalSum = 0;
		
		for (int idx = 0; idx < replacement.length; idx ++) {
			replacementSum += replacement [idx];
			originalSum += original [idx];
		}
			
		int minDisruption = Math.abs(originalSum - replacementSum); 
		for (int idx = replacement.length; idx < original.length; idx ++) {
			originalSum += original [idx];
			originalSum -= original [idx - replacement.length];
			
			minDisruption = Math.min(minDisruption, Math.abs(originalSum - replacementSum));
		}
		
		return minDisruption;
	}
	
	// driver method
	public static void main(String[] args) throws Exception {
		_instance.runTest(new int [] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, new int [] { 3, 5, 3 }, 1);
	}
	
	public void runTest(final int [] original, final int [] replacement, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { original, replacement });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
