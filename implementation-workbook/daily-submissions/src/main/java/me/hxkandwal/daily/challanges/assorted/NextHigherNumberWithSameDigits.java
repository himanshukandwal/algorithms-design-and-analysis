package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

public class NextHigherNumberWithSameDigits extends AbstractCustomTestRunner {
	
	private static NextHigherNumberWithSameDigits _instance = new NextHigherNumberWithSameDigits();
	
	private NextHigherNumberWithSameDigits() {}
	
	// knuth's L algorithm
	public static int _nextHigherNumber(int number) {
		char[] numberArr = String.valueOf(number).toCharArray();
		
		int idx = 0, lastIndex = numberArr.length - 1;
		for (idx = lastIndex; idx > 0 ; idx --)
			if (numberArr [idx] > numberArr [idx - 1])
				break;
		
		if (idx == 0) return number;
		
		for (int rightHalfIdx = lastIndex; rightHalfIdx >= idx; rightHalfIdx --) {
			if (numberArr [rightHalfIdx] > numberArr [idx - 1]) {
				char ch = numberArr [idx - 1];
				numberArr [idx - 1] = numberArr [rightHalfIdx];
				numberArr [rightHalfIdx] = ch;
				break;
			}
		}
		
		// reverse the right half.
		while (idx < lastIndex) {
			char ch = numberArr [idx];
			numberArr [idx] = numberArr [lastIndex];
			numberArr [lastIndex] = ch;
			lastIndex --;
			idx ++;
		}
		
		return Integer.valueOf(String.valueOf(numberArr));
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(1234, 1243);
		_instance.runTest(1243, 1324);
		_instance.runTest(4321, 4321);
		_instance.runTest(0, 0);
	}

	public void runTest(final int number, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { number });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
