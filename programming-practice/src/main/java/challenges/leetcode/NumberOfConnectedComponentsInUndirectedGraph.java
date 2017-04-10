package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * 323. Number of Connected Components in an Undirected Graph
 * 
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find 
 * the number of connected components in an undirected graph.
 * 
 * Example 1:
 * 
 *      0          3
 *      |          |
 *      1 --- 2    4
 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
 * 
 * Example 2:
 *      0           4
 *      |           |
 *      1 --- 2 --- 3
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
 * 
 * Note: You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and 
 * 		 thus will not appear together in edges.
 * 
 * @author Hxkandwal
 */
public class NumberOfConnectedComponentsInUndirectedGraph extends AbstractCustomTestRunner {
	
	private static NumberOfConnectedComponentsInUndirectedGraph _instance = new NumberOfConnectedComponentsInUndirectedGraph();
	
	public static class Node {
        int value;
        int rank;
        Node parent;
        
        public Node (int value) { this.value = value; this.parent = this; }
        public String toString() { return String.valueOf(value); }
    }

	public int _countComponentsBetter(int n, int[][] edges) {
        if (n == 1) return 1;
        
        Map<Integer, Node> map = new HashMap<>();
        for (int idx = 0; idx < n; idx ++) map.put (idx, new Node (idx));
        
        for (int[] edge : edges) {
            Node s = map.get (edge [0]), e = map.get (edge [1]);
            Node rs = findRepresentative (s), re = findRepresentative (e);
            if (rs != re) {
                n --;
                if (rs.rank > re.rank) { re.parent = rs; rs.rank ++; }
                else if (rs.rank < re.rank) { rs.parent = re; re.rank ++; }
                else { rs.parent = re; re.rank ++;} 
            }
        }
        return n;
    }
	
	// original idea, used set in the end and also called path compression logic again. (time and memory consuming!)  
    public int countComponents(int n, int[][] edges) {
        if (edges.length == 0) return 0;
        if (n == 1) return 1;
        
        Map<Integer, Node> map = new HashMap<>();
        for (int idx = 0; idx < n; idx ++) map.put (idx, new Node (idx));
        
        for (int[] edge : edges) {
            Node s = map.get (edge [0]), e = map.get (edge [1]);
            Node rs = findRepresentative (s), re = findRepresentative (e);
            if (rs != re) {
                if (rs.rank > re.rank) { re.parent = rs; rs.rank ++; }
                else if (rs.rank < re.rank) { rs.parent = re; re.rank ++; }
                else { rs.parent = re; re.rank ++;} 
            }
        }
        
        Set<Node> components = new HashSet<>();
        for (Node node : map.values()) components.add(findRepresentative(node));
        return components.size();
    }
    
    private Node findRepresentative (Node node) {
        if (node.parent != node) 
            node.parent = findRepresentative (node.parent);
        return node.parent;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(3, new int[][] { new int [] { 0, 1 },  new int [] { 1, 2 } }, 1);
		_instance.runTest(5, new int[][] { new int [] { 0, 1 },  new int [] { 1, 2 },  new int [] { 3, 4 } }, 2);
	}
	
	public void runTest(final int n, final int[][] edges, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n, edges });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo (expectedOutput);
		
		System.out.println("ok!");
	}

}