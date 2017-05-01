package challenges.leetcode;

import java.util.ArrayList;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 294. Flip Game II
 * 
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, 
 * you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move 
 * and therefore the other person will be the winner.
 * 
 * Write a function to determine if the starting player can guarantee a win.
 * 
 * For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 * 
 * Follow up: Derive your algorithm's runtime complexity.
 * 
 * @author Hxkandwal
 */
public class FlipGameII extends AbstractCustomTestRunner {
	
	public boolean canWin(String s) {
        List<Integer> indexes = new ArrayList <>();
        for (int idx = 0; idx < s.length() - 1; idx ++)
            if (s.charAt (idx) == '+' && s.charAt (idx + 1) == '+') indexes.add (idx);
        if (indexes.size () == 0) return false;
        
        for (int index : indexes)
            if (!canWin (s.substring (0, index) + "--" + s.substring (index + 2))) return true;
        return false;
    }

}
