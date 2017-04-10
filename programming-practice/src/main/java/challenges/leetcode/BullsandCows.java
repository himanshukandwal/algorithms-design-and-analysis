package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 299. Bulls and Cows
 * 
 * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to 
 * guess what the number is.
 * 
 * Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret 
 * number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in 
 * the wrong position (called "cows").
 * 
 * Your friend will use successive guesses and hints to eventually derive the secret number.
 * 
 * For example: Secret number: "1807" Friend's guess: "7810"
 * 
 * Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
 * 
 * Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B 
 * to indicate the cows.
 * 
 * In the above example, your function should return "1A3B".
 * 
 * Please note that both secret number and friend's guess may contain duplicate digits, for example:
 * 
 * Secret number: "1123" Friend's guess: "0111"
 * 
 * In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".
 * 
 * You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
 * 
 * @author Hxkandwal
 *
 */
public class BullsandCows extends AbstractCustomTestRunner {

	private static BullsandCows _instance = new BullsandCows();
			
	private BullsandCows() {}
	
	// my attempt.
	public String getHint(String secret, String guess) {
		
		// small and cheap mini maps
		int[] seen = new int[10]; 
		int bullscount = 0, cowscount = 0;
		
		for (int idx = guess.length() -1; idx >= 0; idx --) {
			int guessValue = Character.getNumericValue(guess.charAt(idx));
			int secretValue = Character.getNumericValue(secret.charAt(idx));
			
			if (guessValue == secretValue)
				bullscount ++;
			else {
				if (seen [guessValue] == 0)
					seen [guessValue] = 1;
			}
		}
		
		for (int idx = 0; idx < seen.length; idx ++)
			cowscount += (seen [idx] > 0) ? 1 : 0;
		
		return new StringBuilder().append(bullscount + "A").append(cowscount + "B").toString();
	}
	
	
	// working code.
	public String _getHint2(String secret, String guess) {
	    int bulls = 0;
	    int cows = 0;
	    int[] numbers = new int[10];
	    
	    for (int i = 0; i<secret.length(); i++) {
	        if (secret.charAt(i) == guess.charAt(i)) bulls++;
	        else {
	            if (numbers[secret.charAt(i)-'0']++ < 0) cows++;
	            if (numbers[guess.charAt(i)-'0']-- > 0) cows++;
	        }
	    }
	    
	    return bulls + "A" + cows + "B";
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("1807", "7810", "1A3B");
		_instance.runTest("1807", "1807", "4A0B");
		_instance.runTest("1123", "0111", "1A1B");
		_instance.runTest("11", "10", "1A0B");
	}

	public void runTest(final String secret, final String guess, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { secret, guess });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
