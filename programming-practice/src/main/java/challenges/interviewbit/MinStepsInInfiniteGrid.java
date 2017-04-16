package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Min Steps in Infinite Grid
 * 
 * You are in an infinite 2D grid where you can move in any of the 8 directions :
 *  (x,y) to
 *  		(x+1, y),
 *  		(x - 1, y),
 *  		(x, y+1),
 *  		(x, y-1),
 *  		(x-1, y-1),
 *  		(x+1,y+1),
 *  		(x-1,y+1),
 *  		(x+1,y-1)
 *  
 * You are given a sequence of points and the order in which you need to cover the points. 
 * Give the minimum number of steps in which you can achieve it. You start from the first point.
 * 
 * Example :
 * 		Input : [(0, 0), (1, 1), (1, 2)]
 * 		Output : 2
 * 		
 * 		It takes 1 step to move from (0, 0) to (1, 1). It takes one more step to move from (1, 1) to (1, 2).
 * 
 * @author Hxkandwal
 */
public class MinStepsInInfiniteGrid extends AbstractCustomTestRunner {
	
	private static MinStepsInInfiniteGrid _instance = new MinStepsInInfiniteGrid();

	class Point implements Comparable<Point> {
        int x, y;
        Point (int x, int y) { this.x = x; this.y = y; }
        
        public int compareTo (Point p) {
            return (x - p.x == 0) ? y - p.y : x - p.x;
        }
    }
    
    private int minDistance (Point p1, Point p2) {
        int xdiff = Math.abs (p1.x - p2.x);
        int ydiff = Math.abs (p1.y - p2.y);
        return Math.min (xdiff, ydiff) + Math.abs (xdiff - ydiff);
    }
    
    // push sideways to extend the walls [left <--min-distance-- idx --min-distance--> right]
    public int _coverPoints(List<Integer> X, List<Integer> Y) {
        Point [] p = new Point [X.size()];
        Point start = null;
        for (int idx = 0; idx < X.size(); idx ++)  {
            p [idx] = new Point (X.get (idx), Y.get (idx));
            if (idx == 0)  start = p [idx];
        }
        
        Arrays.sort (p);
        int idx = Arrays.binarySearch (p, start);
        int steps = 0, left = idx - 1, right = idx + 1;
        
        while (left >= 0 || right < p.length) {
            int leftDist = (left >= 0) ? minDistance (p [idx], p [left]) : Integer.MAX_VALUE;
            int rightDist = (right < p.length) ? minDistance (p [idx], p [right]) : Integer.MAX_VALUE;
            
            if (leftDist > rightDist) {
                steps += rightDist;
                idx = right;
                right ++;
            } else {
                steps += leftDist;
                idx = left;
                left --;
            }
        }
        
        return steps;
    }
    
    // when order is well defined.
    private int minDistance (int x1, int y1, int x2, int y2) {
        int xdiff = Math.abs (x1 - x2);
        int ydiff = Math.abs (y1 - y2);
        return Math.min (xdiff, ydiff) + Math.abs (xdiff - ydiff);
    }
    
    public int coverPoints(List<Integer> X, List<Integer> Y) {
        int steps = 0;
        
        for (int idx = 0; idx < X.size() - 1; idx ++)
            steps += minDistance (X.get (idx), Y.get (idx), X.get (idx + 1), Y.get (idx + 1));
        
        return steps;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(Arrays.asList(0, 1, 1), Arrays.asList(0, 1, 2), 2);
	}

	public void runTest(final List<Integer> X, final List<Integer> Y, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { X, Y });

		for (Object answer : answers)
			assertThat((int) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
