package challenges.codefights;

import static org.junit.Assert.assertEquals;

/**
 * In this game, you're given a number n. You can make d moves, and on the ith move (1-based) you should delete one digit from n 
 * in such a way that the resulting number has at least one non-zero digit and is divisible by d - i + 1. 
 * 
 * If you fail to do it, the game stops. Otherwise, leading zeros (if any) are dropped and the resulting value is then used to make 
 * the next move. If you manage to make all d moves and end up with a non-zero value, you win.
 * 
 * You suspect that number game is some kind of a confidence trick, but still want to try it. In order not to lose all your money, 
 * you need to implement a function that will help you with it. Given n and d, determine the maximum number of moves you can make 
 * 
 * (note that d is an upper bound for that number).
 * 
 * For n = "102045" and d = 4, the output should be 
 *  		
 * 		numberGame(n, d) = 4.
 * 
 * For n = "5555" and d = 3, the output should be 
 * 
 * 		numberGame(n, d) = 1.
 * 
 * You can remove one digit to make the number divisible by 3, but that's the only move you can make.
 * 
 * @author Heman
 *
 */
public class NumberGame {
	
	public static void main(String[] args) {
		assertEquals(4, numberGame("102045", 4));
		assertEquals(1, numberGame("100000", 4));
		assertEquals(1, numberGame("5555", 3));
		assertEquals(0, numberGame("555", 3));
		assertEquals(0, numberGame("555", 3));
		assertEquals(0, numberGame("12", 3));
		assertEquals(1, numberGame("11", 1));
		assertEquals(2, numberGame("123", 3));
		assertEquals(2, numberGame("123", 3));
		assertEquals(0, numberGame("", 3));
		assertEquals(0, numberGame("", 0));
		assertEquals(0, numberGame("", -1));
		assertEquals(0, numberGame("123", -1));
		assertEquals(0, numberGame("1", 1));
		assertEquals(1, numberGame("10", 1));
		assertEquals(1, numberGame("010", 1));
		assertEquals(2, numberGame("100", 2));
		assertEquals(0, numberGame("100", 3));
		assertEquals(1, numberGame("88", 8));
		assertEquals(4, numberGame("61200", 4));
		assertEquals(4, numberGame("61200", 5));
	}

	public static int numberGame(String n, int d) {
	    int moves = 0;
	    
	    if (d <= 0)
	    	return moves;
	    
	    int maxChildMoves = 0;
	    for (int idx = 0; idx < n.length(); idx ++) {
	        String str = substring (n, idx).replaceAll("^0", "");

	        if (str.length() > 0 && (Integer.valueOf(str) > 0 ? Integer.valueOf(str) % d == 0 : false)) {
	        	moves = 1;
	            maxChildMoves = Math.max(maxChildMoves, numberGame(str, d-1));
	        }
	    }
	    
	    return moves + maxChildMoves;
	}

	public static String substring(String str, int pos) {
	    if (pos == 0)
	        return str.substring(1);
	    else if (pos == (str.length() - 1))
	        return str.substring(0, str.length() - 1);
	    else {
	        return str.substring(0, pos) + str.substring(pos + 1);
	    }
	}
	
}