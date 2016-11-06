package me.hxkandwal.daily.challanges.topcoder.game;

import static com.google.common.truth.Truth.assertThat;

import java.io.FileNotFoundException;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * The Game of Nim
 * 
 * The most famous mathematical game is probably the Game of Nim. 
 * This is the game that you will probably encounter the most times and there are many 
 * variations on it, as well as games that can be solved by using the knowledge of how 
 * to play the game. 
 * 
 * Usually you will meet them as Division I 1000 pointers (though hopefully your next encounter 
 * will seem much easier). Although these problems often require a clever idea, they are usually 
 * very easy to code.
 * 
 * Rules of the Game of Nim:
 *  
 * 		There are n piles of coins. 
 * 		When it is a player’s turn he chooses one pile and takes at least one coin from it. 
 * 		If someone is unable to move he loses (so the one who removes the last coin is the winner).
 * 
 * @author Hxkandwal
 *
 */
public class NimGame extends AbstractCustomTestRunner {
	
	private static NimGame _instance = new NimGame();
	
	private NimGame() {}
	
	public static enum Player {
		FIRST,
		SECOND;
		
		public Player other() {
			return this == FIRST ? SECOND : FIRST;
		}
	}	
	
	/*
	 * Let n1, n2, n3, ..., nk, be the sizes of the piles.
	 *  
	 * It is a losing position for the player whose turn it is if and only if:
	 * 		
	 * 		n1 xor n2 xor .. xor nk = 0.
	 * 
	 * Reason :
	 * 
	 * In nim (in fact, in any finite combinatorial game), the following facts are true:
	 * http://math.stackexchange.com/questions/416042/why-xor-operator-works
	 * 
	 * 		> From the losing positions we can move only to the winning ones:
	 * 			if xor of the sizes of the piles is 0 then it will be changed after our move 
	 * 			(at least one 1 will be changed to 0, so in that column will be odd number of 1s).
	 * 		
	 * 		> From the winning positions it is possible to move to at least one losing:
	 * 			if xor of the sizes of the piles is not 0 we can change it to 0 by finding the left most 
	 * 			column where the number of 1s is odd, changing one of them to 0 and then by changing 0s or 
	 * 			1s on the right side of it to gain even number of 1s in every column.
	 */
	public static Player _getWinner(int[] n) {
		int xor = n [0];
		
		for (int idx = 1; idx < n.length; idx++) 
			xor = xor ^ n [idx];
		
		return (xor == 0) ? Player.SECOND : Player.FIRST; 
	}
	
	// driver method
    public static void main(String[] args) throws FileNotFoundException {
    	_instance.runTest(new int [] { 3, 4, 5 }, Player.FIRST);
    	_instance.runTest(new int [] { 3, 3 }, Player.SECOND);
    	_instance.runTest(new int [] { 3, 3, 3 }, Player.FIRST);
    }

	public void runTest(final int[] n, final Player expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });
		
		for (Object answer : answers) 
			assertThat((Player) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
