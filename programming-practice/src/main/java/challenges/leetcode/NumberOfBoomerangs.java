package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 447. Number of Boomerangs
 * 
 * Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that 
 * the distance between i and j equals the distance between i and k (the order of the tuple matters).
 * 
 * Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).
 * 
 * Example:
 * 		Input	: [[0,0],[1,0],[2,0]]
 * 		Output	: 2
 * 		Explanation: The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 * 
 * @author Hxkandwal
 */
public class NumberOfBoomerangs extends AbstractCustomTestRunner {

	private static NumberOfBoomerangs _instance = new NumberOfBoomerangs();
	
	public NumberOfBoomerangs() {}
	
	/**
	 * For every i, we capture the number of points equidistant from i. Now for this i, we have to calculate all possible permutations 
	 * of (j,k) from these equidistant points.
	 * 
	 * Total number of permutations of size 2 from n different points is nP2 = n!/(n-2)! = n * (n-1). hope this helps.
	 */
	public int _numberOfBoomerangs(int[][] points) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int[] point : points) {
        	for (int[] otherPoint : points) {
            	if (point == otherPoint) continue;
            	int dist = (point[0] - otherPoint [0]) * (point[0] - otherPoint [0]) + (point[1] - otherPoint [1]) * (point[1] - otherPoint [1]);
            	map.put(dist, map.getOrDefault(dist, 0) + 1);
            }
        	for (int value : map.values()) count += value * (value - 1);
        	map.clear();
        }
        
		return count;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [][] { new int [] { 0, 0 }, new int [] { 1, 0 }, new int [] { 2, 0 } }, 2);
	}

	public void runTest(final int[][] points, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { points });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}   	

}
