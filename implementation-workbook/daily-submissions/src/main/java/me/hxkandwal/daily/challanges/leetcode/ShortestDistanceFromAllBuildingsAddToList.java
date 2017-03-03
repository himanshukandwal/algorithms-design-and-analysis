package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 317. Shortest Distance from All Buildings Add to List
 * 
 * 
 * @author Hxkandwal
 */
public class ShortestDistanceFromAllBuildingsAddToList extends AbstractCustomTestRunner {
	
	private static ShortestDistanceFromAllBuildingsAddToList _instance = new ShortestDistanceFromAllBuildingsAddToList();

    public int _shortestDistance(int[][] grid) {
        if (grid.length == 0) return 0;
        int[][] dist = new int [grid.length][grid[0].length];
        
        List<int[]> buildings = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int row = 0; row < grid.length; row ++) {
            for (int col = 0; col < grid [0].length; col ++) {
                if (grid [row][col] == 1)
                    buildings.add (new int[] { row, col });
                grid [row][col] = -grid [row][col];
            }
        }
        
        for (int k = 0; k < buildings.size(); k ++) 
            bfsVisit (grid, dist, buildings.get (k)[0], buildings.get (k)[1], k);
            
        for (int row = 0; row < grid.length; row ++)
            for (int col = 0; col < grid [0].length; col ++)
                if (grid[row][col] == buildings.size())
                    min = Math.min (min, dist [row][col]);
        
        return (min == Integer.MAX_VALUE) ? -1 : min;
    }
    
    private void bfsVisit (int[][] grid, int[][] dist, int row, int col, int k) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer (new int[] { row, col });
        int layer = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size -- > 0) {
                int[] coord = queue.poll ();
                
                if (coord[0] + 1 < grid.length && grid [coord[0] + 1][coord[1]] == k) {
                    grid [coord[0] + 1][coord[1]] = k + 1;
                    queue.offer (new int[] { coord[0] + 1, coord[1] });
                    dist [coord[0] + 1][coord[1]] += layer;
                }
                if (coord[0] - 1 >= 0 && grid [coord[0] - 1][coord[1]] == k) {
                    grid [coord[0] - 1][coord[1]] = k + 1;
                    queue.offer (new int[] { coord[0] - 1, coord[1] });
                    dist [coord[0] - 1][coord[1]] += layer;
                }
                if (coord[1] + 1 < grid[0].length && grid [coord[0]][coord[1] + 1] == k) {
                    grid [coord[0]][coord[1] + 1] = k + 1;
                    queue.offer (new int[] { coord[0], coord[1] + 1 });
                    dist [coord[0]][coord[1] + 1] += layer;
                } 
                if (coord[1] - 1 >= 0 && grid [coord[0]][coord[1] - 1] == k) {
                    grid [coord[0]][coord[1] - 1] = k + 1;
                    queue.offer (new int[] { coord[0], coord[1] - 1 });
                    dist [coord[0]][coord[1] - 1] += layer;
                }
            }
            layer ++;
        }
    }
	
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[][] { new int[] {1,0,2,0,1},
										new int[] {0,0,0,0,0},
										new int[] {0,0,1,0,0}}, 7);
	}

	
	public void runTest(final int[][] grid, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { grid });
		
		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
