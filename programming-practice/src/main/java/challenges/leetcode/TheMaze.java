package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 490. The Maze
 * 
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, 
 * left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 * 
 * Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the 
 * borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
 * 
 * Example 1:
 * 		Input 1: a maze represented by a 2D array
 * 			
 * 			0 0 1 0 0
 * 			0 0 0 0 0
 * 			0 0 0 1 0
 * 			1 1 0 1 1
 * 			0 0 0 0 0
 * 
 * 		Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * 		Input 3: destination coordinate (rowDest, colDest) = (4, 4)
 * 		
 * 		Output: true
 * 		
 * 		Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 * 
 * Example 2:
 * 		Input 1: a maze represented by a 2D array
 * 			
 * 			0 0 1 0 0
 * 			0 0 0 0 0
 * 			0 0 0 1 0
 * 			1 1 0 1 1
 * 			0 0 0 0 0
 * 
 * 		Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * 		Input 3: destination coordinate (rowDest, colDest) = (3, 2)
 * 		
 * 		Output: false
 * 
 * 		Explanation: There is no way for the ball to stop at the destination.
 * 
 * Note:
 * 	-	There is only one ball and one destination in the maze.
 * 	-	Both the ball and the destination exist on an empty space, and they will not be at the same position 
 * 		initially.
 * 	-	The given maze does not contain border (like the red rectangle in the example pictures), but you could 
 * 		assume the border of the maze are all walls.
 * 	-	The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 * 
 * @author Hxkandwal
 */
public class TheMaze extends AbstractCustomTestRunner {
	
	private static TheMaze _instance = new TheMaze();

	public boolean _hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][][] map = new boolean [maze.length][maze [0].length][4];
        return roll (map, maze, start, destination);
    }
    
	private int [] rowdir = { 0, 1,  0, -1 };
	private int [] coldir = { 1, 0, -1,  0 };
    
    private boolean roll (boolean[][][] map, int[][] maze, int[] start, int[] destination) {
        for (int idx = 0; idx < 4; idx ++) {
            if (!map [start [0]][start [1]][idx]) {
                map [start [0]][start [1]][idx] = true;
                if (maze [start [0]][start [1]] == 1) continue;
                int [] next = new int [] { start [0], start [1] };
                
                while (next [0] >= 0 && next [0] < maze.length && next [1] >= 0 && next [1] < maze[0].length 
                        && maze [next [0]][next [1]] == 0)  {
                	next [0] += rowdir [idx];  next [1] += coldir [idx];
                }
                next [0] -= rowdir [idx]; next [1] -= coldir [idx];
                
                if ((next [0] == destination [0] && next [1] == destination [1]) || (roll (map, maze, next, destination))) return true;
            }
        }
        return false;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [][] {{ 0, 0, 1, 0, 0 }, 
										{ 0, 0, 0, 0, 0 }, 
										{ 0, 0, 0, 1, 0 }, 
										{ 1, 1, 0, 1, 1 }, 
										{ 0, 0, 0, 0, 0 }
							}, new int[] { 0, 4 }, new int[] { 1, 2 }, true);
									
		_instance.runTest(new int [][] {{ 0, 0, 1, 0, 0 }, 
										{ 0, 0, 0, 0, 0 }, 
										{ 0, 0, 0, 1, 0 }, 
										{ 1, 1, 0, 1, 1 }, 
										{ 0, 0, 0, 0, 0 }
					}, new int[] { 0, 4 }, new int[] { 4, 4 }, true);
		
		_instance.runTest(new int[][] { { 0, 0, 0, 0, 1, 0, 0 }, 
										{ 0, 0, 1, 0, 0, 0, 0 }, 
										{ 0, 0, 0, 0, 0, 0, 0 },
										{ 0, 0, 0, 0, 0, 0, 1 }, 
										{ 0, 1, 0, 0, 0, 0, 0 }, 
										{ 0, 0, 0, 1, 0, 0, 0 },
										{ 0, 0, 0, 0, 0, 0, 0 }, 
										{ 0, 0, 1, 0, 0, 0, 1 }, 
										{ 0, 0, 0, 0, 1, 0, 0 } },
				new int[] { 0, 0 }, new int[] { 8, 6 }, true);
	}

	public void runTest(final int[][] maze, int[] start, int[] destination, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { maze, start, destination });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
	
}
