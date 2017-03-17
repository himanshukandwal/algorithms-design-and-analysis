package me.hxkandwal.daily.challanges.leetcode;

import java.util.ArrayList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 293. Flip Game
 * 
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your 
 * friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other 
 * person will be the winner.
 * 
 * Write a function to compute all possible states of the string after one valid move.
 * 
 * For example, given s = "++++", after one move, it may become one of the following states:
 * 		[
 * 		  "--++",
 * 		  "+--+",
 * 		  "++--"
 * 		]
 * 
 * If there is no valid move, return an empty list [].
 * 
 * @author Hxkandwal
 */
public class FlipGame extends AbstractCustomTestRunner {
	
	public List<String> generatePossibleNextMoves(String s) {
        List<String> ans = new ArrayList<>();
        
        for (int idx = 0; idx < s.length() - 1; idx ++)
            if (s.charAt(idx) == '+' && s.charAt(idx + 1) == '+')
                ans.add (s.substring (0, idx) + "--" + s.substring(idx + 2));
                
        return ans;
    }

}
