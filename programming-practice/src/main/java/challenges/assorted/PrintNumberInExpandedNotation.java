package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Given a number, print it in expanded notation.
 * 
 * @author Hxkandwal
 */
public class PrintNumberInExpandedNotation extends AbstractCustomTestRunner {

	private static PrintNumberInExpandedNotation _instance = new PrintNumberInExpandedNotation();
	
	private PrintNumberInExpandedNotation() {}
	
	public static String[] units = new String [] {
			"", "one", "two", "three", "four", "five",
			"six", "seven", "eight", "nine", "ten", "eleven", "twelve", 
			"thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
			"eighteen", "nineteen"
	};
	
	public static String[] tens = new String [] {
			"", "ten", "twenty", "thirty", "fourty", "four", "fifty",
			"sixty", "seventy", "eighty", "ninety", "twenty"
	};
	
	public static String[] powers = new String[] {
			"", "", "hundered", "thousand", "", "", "million", "", "", "billion"
	};
	
	public static String _convert (int number) {
		if (number < 20) 
			return units [number];
		
		String answer = "";
		while (number >= 20) {
			if (number < 100) {
				answer = tens [number / 10];
				number = number % 10;
			}
			
			else if (number < 1000) {
				answer = answer + units [number / 100] + " hundred ";
				number =  number % 100;
			}
			
			else {
				int power = 1;
				while (((int) (number / Math.pow(10, power))) > 20)
					power ++;
				
				answer = answer + units [(int) (number / Math.pow (10, power))] + " " + powers [power] + " ";
				number = (int) (number % Math.pow(10, power));
			}
		}
		
		answer = answer + units [number];
		return answer;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(10, "ten");
		_instance.runTest(15, "fifteen");
		_instance.runTest(17, "seventeen");
		_instance.runTest(117, "one hundred seventeen");
		_instance.runTest(617, "six hundred seventeen");
		_instance.runTest(1617, "sixteen hundered seventeen");
		_instance.runTest(2617, "two thousand six hundred seventeen");
		_instance.runTest(12617, "twelve thousand six hundred seventeen");
	}

	public void runTest(final int input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
