package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * ref: https://www.youtube.com/watch?v=kPaJfAUwViY
 * 
 * @author Hxkandwal
 */
public class CountingInversions extends AbstractCustomTestRunner {
	
	private static CountingInversions _instance = new CountingInversions();

	public int _countInversions (int[] nums) {
		int max = 0;
		for (int num : nums) max = Math.max(max, num);
		FenwickTree ft = new FenwickTree(max);
		
		int count = 0;
		for (int idx = 0; idx < nums.length; idx ++) {
			count += ft.rangeSum (nums [idx] - 1, max - 1);
			ft.add(nums [idx] - 1, 1);
		}
		
		return count;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 7, 6, 2, 3, 1, 4, 5 }, 13);
	}

	public void runTest(final int[] nums, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	} 
	
}
