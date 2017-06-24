package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 505. The Maze II
 * 
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, 
 * left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next 
 * direction.
 * 
 * Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at 
 * the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position 
 * (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.
 * 
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the 
 * borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
 * 
 * Example 1
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
 * 		Output: 12
 * 
 * 		Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
 * 					 The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
 * 
 * Example 2
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
 * 		Output: -1
 * 
 * 		Explanation: There is no way for the ball to stop at the destination.
 * 
 * Note:
 * 	-	There is only one ball and one destination in the maze.
 * 	-	Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 * 	-	The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the 
 * 		border of the maze are all walls.
 * 	-	The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 * 
 * @author Hxkandwal
 */
public class TheMazeII extends AbstractCustomTestRunner {
	
	private static TheMazeII _instance = new TheMazeII();

	public int _shortestDistance(int[][] maze, int[] start, int[] destination) {
		int [][] dp = new int [maze.length][maze [0].length];
        return dfs (dp, maze, start, destination);
    }
    
    private int [] rowdir =  { 0, 1, 0, -1 };
    private int [] coldir =  { 1, 0, -1, 0 };
    
    private int dfs (int [][] dp, int [][] maze, int [] start, int [] destination) {
        if (dp [start [0]][start [1]] != 0) return dp [start [0]][start [1]];
        
        int ans = Integer.MAX_VALUE;
        dp [start [0]][start [1]] = Integer.MIN_VALUE;
        for (int idx = 0; idx < 4; idx ++) {
            int [] next = { start [0] + rowdir [idx], start [1] + coldir [idx] };
            
            int distance = 0;
            while (next [0] >= 0 && next [0] < maze.length && next [1] >= 0 && next [1] < maze [0].length && maze [next [0]][next [1]] == 0) {
                        next [0] += rowdir [idx];
                        next [1] += coldir [idx];
                        distance ++;
                    }
            if (distance == 0) continue;
            
            next [0] -= rowdir [idx]; next [1] -= coldir [idx];
            if (next [0] == destination [0] && next [1] == destination [1]) { ans = distance; break; }
            
            int ahead = dfs (dp, maze, next, destination);
            if (ahead > 0) ans = Math.min (ans, distance + ahead);
        }
        
        return dp [start [0]][start [1]] = ans == Integer.MAX_VALUE ? -1 : ans;
    }	

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [][] {{ 0, 0, 1, 0, 0 }, 
										{ 0, 0, 0, 0, 0 }, 
										{ 0, 0, 0, 1, 0 }, 
										{ 1, 1, 0, 1, 1 }, 
										{ 0, 0, 0, 0, 0 }
									   }, new int[] { 0, 4 }, new int[] { 4, 4 }, 12);
		_instance.runTest(new int [][] {{ 0, 0, 1, 0, 0 }, 
										{ 0, 0, 0, 0, 0 },
										{ 0, 0, 0, 1, 0 },
										{ 1, 1, 0, 1, 1 },
										{ 0, 0, 0, 0, 0 }
									   }, new int[] { 0, 4 }, new int[] { 3, 2 }, -1);
	}

	public void runTest(final int[][] maze, int[] start, int[] destination, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { maze, start, destination });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
	    
}
