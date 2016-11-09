package me.hxkandwal.daily.challanges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Richie Rich
 * 
 * Sandy likes palindromes. A palindrome is a word, phrase, number, or other sequence of characters which 
 * reads the same backward as it does forward. For example, madam is a palindrome.
 * 
 * On her  birthday, Sandy's uncle, Richie Rich, offered her an n-digit check which she refused because the 
 * number was not a palindrome. Richie then challenged Sandy to make the number palindromic by changing no 
 * more than k digits. Sandy can only change 1 digit at a time, and cannot add digits to (or remove digits from)
 * the number.
 * 
 * Given k and an n-digit number, help Sandy determine the largest possible number she can make by changing <=k 
 * digits.
 *
 * Example:
 * a.		Input:	3943
 * 				k:	1
 * 			Output:	3993  (higher than 3443)
 * 
 * b.		Input:	092282
 * 				k:	3
 * 			Output:	992299
 *  
 * c.		Input:	0011
 * 				k:	1
 * 			Output:	-1
 * 
 * link:	https://www.hackerrank.com/challenges/richie-rich
 * 
 * @author Hxkandwal
 *
 */
public class RichieRich extends AbstractCustomTestRunner {
	
	private static RichieRich _instance = new RichieRich();
	
	private RichieRich() {}
	
	public static String _reduceToPalindrome(String s, int k) {
		boolean isEven =  (s.length() % 2 == 0);
		StringBuilder answer = new StringBuilder(s);
		circulate (answer, (isEven ? s.length()/2 - 1 : s.length()/2), 0, k, isEven);
		
		return answer.toString();
	}
	
	private static int circulate (StringBuilder s, int center, int radius, int cost, boolean isEven) {
		if (center - radius < 0 || center + radius > s.length())
			return cost;
		
		boolean isChangeFound = false;
		if (s.charAt(center - radius) != s.charAt(center + radius + (isEven ? 1 : 0))) {
			if (cost == 0) {
				s.setLength(0);
				s.append("-1");
				return cost;
			}
			
			cost --;
			isChangeFound = true;
			
			if (Character.getNumericValue(s.charAt(center - radius)) > Character.getNumericValue(s.charAt(center + radius + (isEven ? 1 : 0))))
				s.setCharAt(center + radius + (isEven ? 1 : 0), s.charAt(center - radius));
			else
				s.setCharAt(center - radius, s.charAt(center + radius + (isEven ? 1 : 0)));
		}
		
		int recievedCost = circulate (s, center, radius + 1, cost, isEven);
		
		if (recievedCost >= (isChangeFound ? 1 : 2) && Character.getNumericValue(s.charAt(center - radius)) < 9) {
			recievedCost -= (isChangeFound ? 1 : 2);
			s.setCharAt(center - radius, '9');
			s.setCharAt(center + radius + (isEven ? 1 : 0), '9');
		} 
		
		if (!isEven && recievedCost > 0 && radius == 0 && Character.getNumericValue(s.charAt(center - radius)) < 9) 
			s.setCharAt(center, '9');
		
		return recievedCost;
	}

    // driver method
    public static void main(String[] args) {
        _instance.runTest("3943", 1, "3993");
        _instance.runTest("092282", 3, "992299");
        _instance.runTest("0011", 1, "-1");
        _instance.runTest("11331", 4, "99399");
        _instance.runTest("12321", 1, "12921");
    }

    public void runTest(final String number, final int k, final String expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { number, k });

        for (Object answer : answers)
            assertThat((String) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }	

}
