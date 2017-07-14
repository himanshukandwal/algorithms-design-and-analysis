package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 583. Delete Operation for Two Strings
 * 
 * Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can 
 * delete one character in either string.
 * 
 * Example 1:
 * 		Input	: "sea", "eat"
 * 		Output	: 2
 * 		Explanation	: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 * 
 * Note:
 * 	-	The length of given words won't exceed 500.
 * 	-	Characters in given words can only be lower-case letters.
 * 
 * @author Hxkandwal
 */
public class DeleteOperationForTwoStrings extends AbstractCustomTestRunner {

	public int minDistance(String word1, String word2) {
        int [][] dp = new int [word1.length() + 1][word2.length() + 1];
        for (int row = 0; row < word1.length(); row ++) 
            for (int col = 0; col < word2.length(); col ++)
                if (word1.charAt (row) == word2.charAt (col)) dp [row + 1][col + 1] = dp [row][col] + 1;
                else dp [row + 1][col + 1] = Math.max (dp [row][col + 1], dp [row + 1][col]);
        int ans = dp [word1.length()][word2.length()];
        return (word1.length () - ans) + (word2.length () - ans);
    }
	
}
