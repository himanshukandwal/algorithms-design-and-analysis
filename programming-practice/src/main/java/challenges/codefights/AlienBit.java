package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Alien Bit
 * 
 * Earth-Alien Transmissions Services (EATS) have intercepted some transmissions from a nearby star. It appears that 
 * aliens are trying to communicate with us, but the transmissions are just long strings of numbers with a decimal 
 * place at the beginning.
 * 
 * You, a genius cryptographer and linguist, managed to figure out what these numbers mean. It looks like the aliens just 
 * have a different way of computing! Rather than having just two states like we do (1 and 0), the alien computers measure 
 * the intensity of a single particle with an accuracy that allows reading nearly unlimited states between 0 and 1. This means 
 * that a long string's worth of data can be stored in one alien "bit'.
 * 
 * Conveniently, the aliens also use ASCII - what luck! Now all you need to do is write a program that turns an alien bit abit 
 * into a translated string.
 * 
 * Example:
 * 	For abit = "0.116101115116035049", the output should be alienBit(abit) = "test#1".
 * 	
 * 	The alien bit can be split into 6 ASCII symbols:
 * 		116 -> 't'
 * 		101 -> 'e'
 * 		115 -> 's'
 * 		116 -> 't'
 * 		35 -> '#'
 * 		49 -> '1'
 * 
 * Thus, this alien transmission contains the message "test#1".
 * 
 * @author Hxkandwal
 */
public class AlienBit extends AbstractCustomTestRunner {
	
	private static AlienBit _instance = new AlienBit();

	public String _alienBit(String abit) {
	    StringBuilder ans = new StringBuilder ();
	    int idx = 0;
	    while (abit.charAt (idx) != '.') idx ++;
	    idx ++;
	    while (idx < abit.length ()) {
	        int num = 0;
	        while (idx < abit.length () && (num * 10 + (abit.charAt (idx) - '0')) < 127) 
	        	num = num * 10 + (abit.charAt (idx ++) - '0');
	        ans.append ((char) num);
	    }
	    return ans.toString();
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("0.116101115116035049", "test#1");
	}

	public void runTest(final String abit, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { abit });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	

}
