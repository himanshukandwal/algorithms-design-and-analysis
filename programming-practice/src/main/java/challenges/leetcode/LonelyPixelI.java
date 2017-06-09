package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 531. Lonely Pixel I
 * 
 * Given a picture consisting of black and white pixels, find the number of black lonely pixels.
 * The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels 
 * respectively.
 * 
 * A black lonely pixel is character 'B' that located at a specific position where the same row and same column 
 * don't have any other black pixels.
 * 
 * Example:
 * 		Input:	
 * 				[['W', 'W', 'B'],
 * 				 ['W', 'B', 'W'],
 * 				 ['B', 'W', 'W']]
 * 
 * 		Output: 3
 * 		Explanation: All the three 'B's are black lonely pixels.
 * 
 * Note:
 * 	-	The range of width and height of the input 2D array is [1,500].
 * 
 * @author Hxkandwal
 */
public class LonelyPixelI extends AbstractCustomTestRunner {
	
	private static LonelyPixelI _instance = new LonelyPixelI();

	public int _findLonelyPixel(char[][] picture) {
		int n = picture.length, m = picture[0].length;
	    
        int firstRowCount = 0;
		for (int i = 0; i < n; i ++) 
			for (int j = 0; j < m; j ++) 
                if (picture [i][j] == 'B') {   
                    if (picture [0][j] < 'Y' && picture [0][j] != 'V') picture [0][j]++;
                    if (i == 0) firstRowCount ++;
                    else if (picture [i][0] < 'Y' && picture [i][0] != 'V') picture [i][0]++;
                }
    
        int count = 0;
		for (int i = 0; i < n; i ++) 
			for (int j = 0; j < m; j ++) 
				if (picture [i][j] < 'W' && (picture [0][j] == 'C' || picture [0][j] == 'X')) { 
                    if (i == 0) count += firstRowCount == 1 ? 1 : 0;
                    else if (picture [i][0] == 'C' || picture [i][0] == 'X') count ++;
                }
                    
        return count;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new char[][] { "WWB".toCharArray(),
			 							 "WBW".toCharArray(),
			 							 "BWW".toCharArray() }, 3);
		
		_instance.runTest(new char[][] { "BBWBWBBBBW".toCharArray(),
										 "WBWBBWWWWB".toCharArray(),
										 "WBBBWWBWWB".toCharArray(),
										 "WWWWWWWBWW".toCharArray(),
										 "WBBWWWBBWB".toCharArray(),
										 "BBBBWBWBWB".toCharArray(),
										 "WWBBBWBWWB".toCharArray(),
										 "BWWBBWWWWW".toCharArray(),
										 "BWBWWBWBBW".toCharArray(),
										 "BBBBWWWBBW".toCharArray() }, 0);
	}

	public void runTest(final char[][] picture, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { picture });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
	
}
