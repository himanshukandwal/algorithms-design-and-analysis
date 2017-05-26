package challenges.codefights;

import challenges.AbstractCustomTestRunner;

/**
 * 223. Rectangle Area
 * 
 * Find the total area covered by two rectilinear rectangles in a 2D plane. Each rectangle is defined by its bottom 
 * left corner and top right corner.
 * 
 * Assume that the total area is never beyond the maximum possible value of int.
 * 
 * @author Hxkandwal
 */
public class RectangleArea extends AbstractCustomTestRunner {

	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int left = Math.max (A, E), right = Math.max (left, Math.min (C, G));
        int bottom = Math.max (B, F), top = Math.max (bottom, Math.min (D, H));
        
        return (C-A)*(D-B) + (G-E)*(H-F) - (right - left) * (top - bottom);
    }
	
}
