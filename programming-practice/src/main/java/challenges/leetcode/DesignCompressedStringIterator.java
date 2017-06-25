package challenges.leetcode;

import java.util.LinkedList;
import java.util.Queue;

import challenges.AbstractCustomTestRunner;

/**
 * 604. Design Compressed String Iterator
 * 
 * Design and implement a data structure for a compressed string iterator. It should support the following operations: next and hasNext.
 * The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter 
 * existing in the original uncompressed string.
 * 
 * 		next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
 * 		hasNext() - Judge whether there is any letter needs to be uncompressed.
 * 
 * Note:
 * 	Please remember to RESET your class variables declared in StringIterator, as static/class variables are persisted across multiple 
 * 	test cases. Please see here for more details.
 * 
 * Example:
 * 		StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
 * 		iterator.next(); // return 'L'
 * 		iterator.next(); // return 'e'
 * 		iterator.next(); // return 'e'
 * 		iterator.next(); // return 't'
 * 		iterator.next(); // return 'C'
 * 		iterator.next(); // return 'o'
 * 		iterator.next(); // return 'd'
 * 		iterator.hasNext(); // return true
 * 		iterator.next(); // return 'e'
 * 		iterator.hasNext(); // return false
 * 		iterator.next(); // return ' '
 * 
 * @author Hxkandwal
 */
public class DesignCompressedStringIterator extends AbstractCustomTestRunner {
	
	private Queue <Object[]> queue = new LinkedList<>();
    
    public DesignCompressedStringIterator (String compressedString) {
        for (int idx = 0; idx < compressedString.length();) {
            char ch = compressedString.charAt (idx ++);
            int val = 0;
            while (idx < compressedString.length() && Character.isDigit (compressedString.charAt (idx))) 
                val = 10 * val + (compressedString.charAt (idx ++) - '0');
            queue.offer (new Object [] { ch, val });
        }
    }
    
    public char next() {
        if (queue.isEmpty ()) return ' ';
        char ch = (char) queue.peek () [0];
        if ((int) queue.peek () [1] == 1) queue.poll ();
        else queue.peek () [1] = ((int) queue.peek () [1]) - 1; 
        return ch;
    }
    
    public boolean hasNext() {
        return !queue.isEmpty ();
    }
}
