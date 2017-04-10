package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 418. Sentence Screen Fitting
 * 
 * Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.
 * 
 * Note:
 * 
 * A word cannot be split into two lines. The order of words in the sentence must remain unchanged.
 * Two consecutive words in a line must be separated by a single space.
 * Total words in the sentence won't exceed 100.
 * Length of each word is greater than 0 and won't exceed 10.
 * 
 * 	1 ≤ rows, cols ≤ 20,000.
 * 
 * Example 1:
 * 		Input: rows = 2, cols = 8, sentence = ["hello", "world"]
 * 		Output: 1
 * 
 * Explanation:
 * 		hello---	
 * 		world---
 * 
 * The character '-' signifies an empty space on the screen.
 * 
 * @author Hxkandwal
 */
public class SentenceScreenFitting extends AbstractCustomTestRunner {
	
	private static SentenceScreenFitting _instance = new SentenceScreenFitting();

	// highly optimized approach.
	public int wordsTyping(String[] sentence, int rows, int cols) {
		 
		String s = String.join (" ", sentence) + " ";
        int start = 0, l = s.length();
        
        for (int i = 0; i < rows; i++) {
            start += cols;
            
            if (s.charAt(start % l) == ' ') start ++;
            else while (start > 0 && s.charAt((start - 1) % l) != ' ') start --;
        }
        
        return start / s.length();
	}
	
	// my take : naive approach.
	public int _wordsTyping(String[] sentence, int rows, int cols) {
		int idx = 0, count = 0, col = cols;
        for (String word : sentence) if (word.length() > cols) return 0;
        
        String str = sentence [idx];
        while (rows > 0) {
            col -= str.length();
            if (idx + 1 >= sentence.length) {
                count ++;
                str = sentence [idx = 0];
            } else str = sentence [++ idx];
            
            if (str.length() > (col - 1) || col == 0) {
                col = cols; rows --;
            } else col --;
        }
        return count;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new String [] { "a", "bcd", "e" } , 3, 6, 2);
		_instance.runTest(new String [] { "a", "b", "e" } , 20000, 20000, 66666666);
	}

	public void runTest(final String[] sentence, int rows, int cols, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { sentence, rows, cols });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	} 
	
}
