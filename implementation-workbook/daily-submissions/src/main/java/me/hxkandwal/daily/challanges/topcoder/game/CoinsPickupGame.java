package me.hxkandwal.daily.challanges.topcoder.game;

import static com.google.common.truth.Truth.assertThat;

import java.io.FileNotFoundException;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Implemented and Inspired from the article :
 *  
 * 		https://www.topcoder.com/community/data-science/data-science-tutorials/algorithm-games/
 * 
 * A simple example is the following game, played by two players who take turns moving. At the beginning there are n coins. 
 * When it is a player’s turn he can take away 1, 3 or 4 coins. The player who takes the last one away is declared the winner 
 * (in other words, the player who can not make a move is the loser).
 * 
 * The question is: For what n will the first player win if they both play optimally?
 *  
 * @author Hxkandwal
 *
 */
public class CoinsPickupGame extends AbstractCustomTestRunner {
	
	private static CoinsPickupGame _instance = new CoinsPickupGame();
	
	private CoinsPickupGame() {}
	
	public static enum Player {
		FIRST,
		SECOND;
		
		public Player other() {
			return this == FIRST ? SECOND : FIRST;
		}
	}
	
	public static Player _getWinningPlayer(int coins, int[] choices) {
		return playRecursively(coins, choices, Player.FIRST);
	}
	
	private static Player playRecursively(int coins, int[] choices, Player player) {
		if (coins > 0)
			for (int idx = 0; idx < choices.length; idx++)
				if ((coins - choices[idx] == 0) || (coins - choices[idx] >= 0 && player == playRecursively (coins - choices[idx], choices, player.other())))
					return player;

		return player.other();
	}
	
	// driver method
    public static void main(String[] args) throws FileNotFoundException {
    	_instance.runTest(0, new int [] { 1, 3, 4 }, Player.SECOND);
    	_instance.runTest(1, new int [] { 1, 3, 4 }, Player.FIRST);
    	_instance.runTest(2, new int [] { 1, 3, 4 }, Player.SECOND);
    	_instance.runTest(3, new int [] { 1, 3, 4 }, Player.FIRST);
    	_instance.runTest(4, new int [] { 1, 3, 4 }, Player.FIRST);
    	_instance.runTest(5, new int [] { 1, 3, 4 }, Player.FIRST);
    	_instance.runTest(6, new int [] { 1, 3, 4 }, Player.FIRST);
    	_instance.runTest(7, new int [] { 1, 3, 4 }, Player.SECOND);
    	_instance.runTest(8, new int [] { 1, 3, 4 }, Player.FIRST);
    	_instance.runTest(9, new int [] { 1, 3, 4 }, Player.SECOND);
    	_instance.runTest(10, new int [] { 1, 3, 4 }, Player.FIRST);
    	_instance.runTest(11, new int [] { 1, 3, 4 }, Player.FIRST);
    	
    	/*
    	 * Notes :	It can be seen that whether a position is winning or losing depends only on the last k positions, 
    	 * 			where k is the maximum number of coins we can take away. 
    	 */
    }

	public void runTest(final int n, final int[] S, final Player expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n, S });
		
		for (Object answer : answers) 
			assertThat((Player) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
}
