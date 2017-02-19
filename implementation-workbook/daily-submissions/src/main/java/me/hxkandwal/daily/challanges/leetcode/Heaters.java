package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import java.util.TreeSet;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 475. Heaters
 * 
 * Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.
 * 
 * Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could 
 * be covered by those heaters.
 * 
 * So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.
 * 
 * Note:
 * 		Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
 * 		Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
 * 		As long as a house is in the heaters' warm radius range, it can be warmed.
 * 		All the heaters follow your radius standard and the warm radius will the same.
 * 
 * 	Example 1:
 * 		Input: [1,2,3],[2]
 * 		Output: 1
 *  
 * 		Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
 * 	
 * 	Example 2:
 * 		Input: [1,2,3,4],[1,4]
 * 		Output: 1
 * 
 * 		Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
 * 
 * @author Hxkandwal
 */
public class Heaters extends AbstractCustomTestRunner {
	
	private static Heaters _instance = new Heaters();
	
	private Heaters() {}

    public int _findRadius(int[] houses, int[] heaters) {
    	TreeSet<Integer> set = new TreeSet<>();
        for (int heater : heaters) set.add (heater);
        
        int result = 0;
        for (int house : houses) {
            int left = set.floor(house) != null ? house - set.floor(house) : Integer.MAX_VALUE;
            int right = set.ceiling(house) != null ? set.ceiling(house) - house : Integer.MAX_VALUE;
            result = Math.max (result, Math.min(left, right));
        }
        
        return result;
    }	
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 2, 3 }, new int[] { 2 }, 1);
		_instance.runTest(new int[] { 1, 2, 3 }, new int[] { 1, 2, 3 }, 0);
		_instance.runTest(new int[] { 1, 5 }, new int[] { 2 }, 3);
		_instance.runTest(new int[] { 1 }, new int[] { 1, 2, 3, 4 }, 0);
		_instance.runTest(new int[] { 1, 2, 3, 5, 15 }, new int[] { 2, 30 }, 13);
	}

	public void runTest(final int[] houses, final int[] heaters, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { houses, heaters });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	} 
	
}
