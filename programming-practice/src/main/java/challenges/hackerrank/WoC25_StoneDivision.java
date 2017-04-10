package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * Stone Division
 * 
 * Consider the following game:
 * >	There are two players, First and Second, sitting in front of a pile of n stones. First always plays first.
 * 
 * >	There is a set, S, of m distinct integers defined as S = { s0, s1, s2, s3, s4 ...., sm } .
 * 
 * >	The players move in alternating turns. During each turn, a player chooses some si in S and splits one of the piles 
 * 		into exactly si smaller piles of equal size. If no si exists that will split one of the available piles into exactly 
 * 		si equal smaller piles, the player loses.
 * 
 * >	Both players always play optimally.
 * 
 * Given n and the contents of S, find and print the winner of the game. 
 * 
 * If First wins, print First; otherwise, print Second.
 * 
 * Sample Input:
 * a.		n: 	15
 * 			S:	{ 5, 2, 3 }
 * 			Answer:	Second
 * 
 * link : https://www.hackerrank.com/contests/w25/challenges/stone-division
 * 
 * Variant of Nim Game:	https://en.wikipedia.org/wiki/Nim
 * 
 * Useful link: https://www.topcoder.com/community/data-science/data-science-tutorials/algorithm-games/
 * 
 * @author Hxkandwal
 *
 */
public class WoC25_StoneDivision extends AbstractCustomTestRunner {
	
	private static WoC25_StoneDivision _instance = new WoC25_StoneDivision();
	
	private WoC25_StoneDivision() {}
	
	public static enum Player {
		FIRST,
		SECOND;
		
		public Player other() {
			return this == FIRST ? SECOND : FIRST;
		}
	}

	public static Player _getWinningPlayer(int n, int[] S) {
		int minChoice = Integer.MAX_VALUE;
		for (int idx = 0; idx < S.length; idx ++) 
			minChoice = Math.min(minChoice, S [idx]);
		
		return playRecursively (new ArrayList() {{ add(n); }}, n, S, minChoice, Player.FIRST);
	}
	
	private static Player playRecursively (List<Integer> piles, int n, int[] S, int minChoice, Player player) {
		boolean playFurther = false;
		for (int idx = 0; idx < piles.size() && !playFurther; idx++) 
			if (piles.get(idx) > minChoice)
				playFurther = true;
		
		// can't play further, then other player won.
		if (!playFurther) 
			return player.other();	
		
		// choose all pile, try winning from all possible scenarios.
		Player solPlayer = null;
		for (int idx = 0; idx < S.length; idx++) {
			int choice = S [idx];
			
			// see if there is any pile which we can break evenly (modulus 0) from this choice.
			Set<Integer> divisblePiles = new HashSet<>();
			for (int index = 0; index < piles.size(); index ++) 
				if (piles.get(index) % choice == 0) 
					divisblePiles.add(piles.get(index));
			
			for (Integer divisblePile : divisblePiles) {
				List<Integer> pilesCopy = new ArrayList<>(piles);
				pilesCopy.remove(divisblePile);
				for (int insert = 0; insert < choice; insert ++) 
					pilesCopy.add(divisblePile / choice);
				
				solPlayer = playRecursively (pilesCopy, n, S, minChoice, player.other());
				if (solPlayer == player)
					break;
			}
			
			if (solPlayer == player)
				break;
		}
		
		return solPlayer;
	}
	
	// driver method
    public static void main(String[] args) throws FileNotFoundException {
    	_instance.runTest(15, new int [] { 5, 2, 3 }, Player.SECOND);
    	_instance.runTest(45, new int [] { 5, 3 }, Player.FIRST);
    	_instance.runTest(10, new int [] { 2, 5 }, Player.FIRST);
    	_instance.runTest(15, new int [] { 3, 5, 15 }, Player.FIRST);
    	_instance.runTest(3888, new int [] { 2, 3, 8, 9, 36 }, Player.FIRST);
    }

	public void runTest(final int n, final int[] S, final Player expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n, S });
		
		for (Object answer : answers) 
			assertThat((Player) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
}
