package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 605. Can Place Flowers
 * 
 * Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers 
 * cannot be planted in adjacent plots - they would compete for water and both would die.
 * 
 * Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and 
 * a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.
 * 
 * Example 1:
 * 			Input: flowerbed = [1,0,0,0,1], n = 1
 * 			Output: True
 * 			
 * Example 2:
 * 			Input: flowerbed = [1,0,0,0,1], n = 2
 * 			Output: False
 * 	
 * Note:
 * 	-	The input array won't violate no-adjacent-flowers rule.
 * 	-	The input array size is in the range of [1, 20000].
 * 	-	n is a non-negative integer which won't exceed the input array size.
 * 
 * @author Hxkandwal
 */
public class CanPlaceFlowers extends AbstractCustomTestRunner {
	
	private static CanPlaceFlowers _instance = new CanPlaceFlowers();

	public boolean _canPlaceFlowers(int[] flowerbed, int n) {
		for (int idx = 0; idx < flowerbed.length && n > 0; idx ++)
            if (flowerbed [idx] == 0 && (idx == 0 || flowerbed [idx - 1] == 0) && (idx == flowerbed.length - 1 || flowerbed [idx + 1] == 0)) {
                n--; idx ++;    // or flowerbed [idx] = 1;
            }
        return n == 0;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 0, 0, 0, 1 }, 1, true);
		_instance.runTest(new int[] { 0, 0, 1, 0, 0 }, 1, true);
	}

	public void runTest(final int[] flowerbed, int n, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { flowerbed, n });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
	
}
