package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Sherlock and The Beast
 * 
 * Sherlock Holmes suspects his archenemy, Professor Moriarty, is once again plotting something diabolical. Sherlock's companion, 
 * Dr. Watson, suggests Moriarty may be responsible for MI6's recent issues with their supercomputer, The Beast.
 * 
 * Shortly after resolving to investigate, Sherlock receives a note from Moriarty boasting about infecting The Beast with a virus; 
 * however, he also gives him a clue—a number, N. Sherlock determines the key to removing the virus is to find the largest Decent 
 * Number having N digits.
 * 
 * A Decent Number has the following properties:
 * 	-	Its digits can only be 3's and/or 5's.
 * 	-	The number of 3's it contains is divisible by 5.
 * 	-	The number of 5's it contains is divisible by 3.
 * 	-	If there are more than one such number, we pick the largest one.
 * 
 * link : https://www.hackerrank.com/challenges/sherlock-and-the-beast
 * 
 * @author Hxkandwal
 */
public class SherlockAndTheBeast extends AbstractCustomTestRunner {
	
	private static SherlockAndTheBeast _instance = new SherlockAndTheBeast();

	public String _findDecentNumber (int n) {
		int z = n;
        while (z % 3 != 0) z-= 5;
        if (z < 0) return "-1";
        StringBuilder ans = new StringBuilder();
        for (int idx = 0; idx < z; idx ++) ans.append ("5");
        for (int idx = 0; idx < n - z; idx ++) ans.append ("3");
        return ans.toString();       
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(3, "555");
		_instance.runTest(4, "-1");
		_instance.runTest(5, "33333");
		_instance.runTest(8, "55533333");
		_instance.runTest(9, "555555555");
		_instance.runTest(12, "555555555555");
	}

	public void runTest(final int n, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
