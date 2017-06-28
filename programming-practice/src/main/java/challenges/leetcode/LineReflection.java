package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * 356. Line Reflection
 * 
 * Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.
 * 
 * Example 1: Given points = [[1,1],[-1,1]], return true.
 * Example 2: Given points = [[1,1],[-1,-1]], return false.
 * 
 * Follow up: Could you do better than O(n^2)?
 * 
 * @author Hxkandwal
 */
public class LineReflection extends AbstractCustomTestRunner {
	
	private static LineReflection _instance = new LineReflection();

	public boolean _isReflected(int[][] points) {
        if (points.length == 0) return true;
        int sum = 0;
        Map<Integer, List<Set<Integer>>> map = new HashMap<>();
        for (int [] point : points) { 
            map.computeIfAbsent(point [0], k -> new ArrayList<Set<Integer>> ()).addAll(Arrays.asList(new HashSet<>(), new HashSet<>()));
            if (map.get (point [0]).get(point [1] >= 0 ? 0 : 1).add(point [1])) sum += point [0];
        }
        double mid = sum * 1d/points.length;
        for (int key : map.keySet ()) {
            double dist = Math.abs (mid - key);
            if (!(map.containsKey ((int) (mid + dist)) 
                && map.containsKey ((int) (mid - dist)) 
                && map.get ((int) (mid + dist)).get(0).size() == map.get ((int) (mid - dist)).get(0).size()
                && map.get ((int) (mid + dist)).get(1).size() == map.get ((int) (mid - dist)).get(1).size())) return false;
        }
        return true;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [][] {{ 0, 0 }, { 1, 0 }}, true);
		_instance.runTest(new int [][] {{ 1, 1 }, { -1, 1 }, { -1, 1 }}, true);
	}

	public void runTest(final int[][] points, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { points });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
