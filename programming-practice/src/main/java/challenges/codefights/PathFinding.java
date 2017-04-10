package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * Pathfinding
 * 
 * Given a graph as a list of its edges, your task is to find the shortest path that starts at a start and 
 * visits each of the markedNodes exactly once. If it's impossible to do this, return -1 instead.
 * 
 * Example :
 * 	
 * For 	start = 0,
 * 		edges = [[0,1],   [0,3],   [1,2],   [2,3], 
 *      		 [2,5],   [3,4],   [4,7],   [5,6], 
 *      		 [6,9],   [6,5],   [7,8],   [8,14], 
 *      		 [8,25],  [9,10],  [10,11], [11,12], 
 *      		 [12,13], [13,27], [14,13], [16,17], 
 *      		 [16,8],  [17,18], [18,19], [18,6], 
 *      		 [19,20], [19,9],  [19,22], [20,23], 
 *      		 [21,22], [21,27], [22,19], [22,21], 
 *      		 [23,24], [23,18], [24,20], [25,6], 
 *      		 [27,16]]
 *      
 *      and markedNodes = [24, 27, 5], the output should be 
 *      pathfinding(start, edges, markedNodes) = 17.
 *      
 * Visualizer : https://jsbin.com/howoxuzini/1/edit?html,js,output
 * 
 * @author Hxkandwal
 *
 */
public class PathFinding extends AbstractCustomTestRunner {
	
	private static PathFinding _instance = new PathFinding();
	
	public PathFinding() {}

	public static class Node {
		int id;
		int distance;
		boolean visited;
		
		
		public Node(int id) {
			this.id = id;
		}
		
		@Override
		public boolean equals(Object obj) {
			return ((Node) obj).id == this.id;
		}
		
		@Override
		public int hashCode() {
			return id;
		}
		
		@Override
		public String toString() {
			return "["  + id + "]";
		}
	}
	
	public static int _pathfinding(int start, int[][] edges, int[] markedNodes) {
		Map<Integer, List<Integer>> nodesMetamap = new HashMap<>();
		for (int row = 0; row < edges.length; row ++) {
			if (!nodesMetamap.containsKey(edges[row][0])) 
				nodesMetamap.put(edges[row][0], new ArrayList<>());
			
			nodesMetamap.get(edges[row][0]).add(edges[row][1]);	
		}
		
		Set<Integer> markedNodesSet = new HashSet<>();
		for (int idx = 0; idx < markedNodes.length; idx++) 
			markedNodesSet.add(markedNodes[idx]);
		
		return dfsRecusion(nodesMetamap, markedNodesSet, start, new HashSet<Integer>());
	}
	
	private static int dfsRecusion(Map<Integer, List<Integer>> nodesMetamap, Set<Integer> markedNodes, int start, Set<Integer> seenNodes) {
		boolean areAllCovered = true;
		for (Integer markedNode : markedNodes) 
			areAllCovered = areAllCovered && (seenNodes.contains(markedNode));
		
		if (areAllCovered) 
			return 0;
		
		List<Integer> connectedNodes = nodesMetamap.get(start);
		int distance = 1;
		for (Iterator<Integer> connectedNodeIterator = connectedNodes.iterator(); connectedNodeIterator.hasNext();) {
			int connectingNode = connectedNodeIterator.next();
			if (!seenNodes.contains(connectingNode)) {
				seenNodes.add(connectingNode);
				distance += dfsRecusion(nodesMetamap, markedNodes, connectingNode, seenNodes);
			}
		}
				
		for (Integer connectingNode : connectedNodes) 
			seenNodes.remove(connectingNode);
		
		return distance;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(0,
				new int[][] {{ 0, 1 }, { 0, 3 }, { 1, 2 }, { 2, 3 }, { 2, 5 }, { 3, 4 }, { 4, 7 }, { 5, 6 }, { 6, 9 },
							 { 6, 5 }, { 7, 8 }, { 8, 14 }, { 8, 25 }, { 9, 10 }, { 10, 11 }, { 11, 12 }, { 12, 13 },
							 { 13, 27 }, { 14, 13 }, { 16, 17 }, { 16, 8 }, { 17, 18 }, { 18, 19 }, { 18, 6 }, { 19, 20 },
							 { 19, 9 }, { 19, 22 }, { 20, 23 }, { 21, 22 }, { 21, 27 }, { 22, 19 }, { 22, 21 }, { 23, 24 },
							 { 23, 18 }, { 24, 20 }, { 25, 6 }, { 27, 16 }},
				
				new int[] { 24, 27, 5 }, 17);
	}

	public void runTest(final int start, final int[][] edges, final int[] markedNodes, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { start, edges, markedNodes });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
