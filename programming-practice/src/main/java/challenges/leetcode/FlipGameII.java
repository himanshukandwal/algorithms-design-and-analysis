package challenges.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	/**
	 * Read about S-G functions.
	 **/
	// solution 1
	public boolean canWinFaster(String s) {
        return canWinFaster (new HashMap<>(), s);
    }
    
    private boolean canWinFaster (Map<String, Boolean> map, String s) {
        if (map.containsKey(s)) return map.get (s);
        List<Integer> indexes = new ArrayList <>();
        for (int idx = 0; idx < s.length() - 1; idx ++)
            if (s.charAt (idx) == '+' && s.charAt (idx + 1) == '+') indexes.add (idx);
        
        if (indexes.size () == 0) { map.put (s, false); return false; }
        
        boolean result = false;
        for (int index : indexes)
            if (result = !canWinFaster (map, s.substring (0, index) + "--" + s.substring (index + 2))) break;

        map.put (s, result);
        return result;
    }
    
    // solution 2
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
