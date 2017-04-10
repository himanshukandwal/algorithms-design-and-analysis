package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 434. Number of Segments in a String
 * 
 * Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.
 * Please note that the string does not contain any non-printable characters.
 * 
 * Example:
 * 		Input: "Hello, my name is John"
 * 		Output: 5
 * 
 * @author Hxkandwal
 */
public class NumberOfSegmentsInAString extends AbstractCustomTestRunner {

	public int countSegmentsOptimized(String s) {
        return ("x " + s).split("\\s+").length - 1;
    }
	
	public int countSegments(String s) {
        int count = 0;
        for (int idx = 0; idx < s.length() - 1; idx ++)
            if ((s.charAt(idx) == ' ' && s.charAt(idx + 1) != ' ') || (idx == 0 && s.charAt(idx) != ' '))
                count ++;
        if (s.length() == 1 && s.charAt(0) != ' ') count ++;
        return count;
    }
	
}
