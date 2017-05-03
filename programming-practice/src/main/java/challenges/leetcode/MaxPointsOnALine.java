package challenges.leetcode;

import java.util.HashMap;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 149. Max Points on a Line
 * 
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * 
 * @author Hxkandwal
 */
public class MaxPointsOnALine extends AbstractCustomTestRunner {

	class Point {
		int x, y;
		Point() { x = 0; y = 0; }
		Point(int a, int b) { x = a; y = b; }
	}
	
	/**
	 * Since we are storing x and y (already divided by their gcd) as the map keys, the signs of x and y really matter. 
	 * For example, we hope (1, 1) and (-1, -1) are stored in the same map entry. The following lemma is essential for 
	 * the correctness of your code:
	 * 
	 * Lemma. If vectors (a, b) and (c, d) are collinear, i.e., there exists a none-zero t (not necessarily integer or 
	 * 		  positive) such that (c, d) = t * (a, b), then we have 
	 * 			
	 * 				gcd(c, d) = t * gcd(a, b).
	 * 
	 * While the lemma may seem apparent at the first sight, it may not be in our case as this gcd is not the gcd we 
	 * defined in math, but the one returned by Euclidean algorithm, allowing a, b, c, d, t and the returned value to be 
	 * negative. However, it can also be proved relatively easily.
	 * 
	 * Proof. We are going to prove that: in every recursion level of gcd(a, b) and gcd(c, d), we always have
	 *  
	 * 			(c, d) = t * (a, b); gcd(a, b) and gcd(c, d) always have the same recursion depth.
	 * 	
	 * 		  For the top level, we have (c, d) = t * (a, b).
	 *  
	 * If b != 0, then t * b != 0. The next level of recursion is gcd(b, a % b) and gcd(bt, at % bt). 
	 * Since int(a / b) = int(at / bt),
	 *  
	 * we have at % bt = at - int(at / bt) * bt = at - int(a / b) * bt = t * (a - int(a / b) * b) = t * (a % b), so point 1 
	 * also holds for the next level.
	 * 
	 * If b = 0, then t * b = 0. Both recursions stop here, proving point 2. Since the returned value is also the first parameter 
	 * of this recursion level, we have gcd(c, d) = t * gcd(a, b).
	 * 
	 * The lemma indicates that if we divide (a, b) with gcd(a, b), and divide (c, d) with gcd(c, d), the two vectors then become 
	 * codirectional instead of collinear, therefore mapped to the same entry.
	 */
	public int maxPoints(Point[] points) {
		if (points.length == 0) return 0;
        int result = 0;
        Map <String, Integer> map = new HashMap <>();
        for (int idx = 0; idx < points.length; idx ++) {
            map.clear (); int same = 1, max = 0;
            
            for (int iidx = idx + 1; iidx < points.length; iidx ++) {
                if (points [iidx].x == points [idx].x && points [iidx].y == points [idx].y) { same ++; continue; }
                int x = points [iidx].x - points [idx].x;
                int y = points [iidx].y - points [idx].y;
                
                int gcd = gcd (x, y);
                x /= gcd; y /= gcd;
                String key = x + ":" + y;
                
                map.put (key, map.getOrDefault (key, 0) + 1);
                max = Math.max (max, map.get (key));
            }
            result = Math.max (result, max + same);
        }
        return result;
    }
    
    private int gcd (int a, int b) {
        if (b == 0) return a;
        return gcd (b, a % b);
    }
}
