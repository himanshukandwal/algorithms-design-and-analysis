package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 261. Graph Valid Tree
 * 
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to 
 * check whether these edges make up a valid tree.
 * 
 * For example:
 * 		Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * 		Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 * 
 * @author Hxkandwal
 */
public class GraphValidTree extends AbstractCustomTestRunner {
	
	private static GraphValidTree _instance = new GraphValidTree();

	public static class Node {
        int value;
        Node parent;
        int rank;
        
        public Node(int value) { this.value = value; this.parent = this; }
        public String toString() { return String.valueOf(value); }
    }
    
    public boolean validTree(int n, int[][] edges) {
        List<Node> nodes = new ArrayList<>();
        for (int idx = 0; idx < n; idx ++)  nodes.add (new Node (idx));
        
        for (int [] edge : edges) {
            Node rs = find (nodes.get (edge [0]));
            Node re = find (nodes.get (edge [1]));
            if (rs != re) {
                n --;
                if (rs.rank > re.rank) { re.parent = rs; rs.rank ++; }
                else if (rs.rank < re.rank) { rs.parent = re; re.rank ++; }
                else { rs.parent = re; re.rank ++; }
            } else return false;
        }
        
        return (n > 1) ? false : true;
    }
    
    private Node find (Node node) {
        if (node.parent != node)
            node.parent = find (node.parent);
        return node.parent;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(5, new int[][] { new int [] { 0, 1 }, new int [] { 0, 2 }, new int [] { 0, 3 }, new int [] { 1, 4 } }, true);
		_instance.runTest(5, new int[][] { new int [] { 0, 1 }, new int [] { 1, 2 }, new int [] { 2, 3 }, new int [] { 1, 3 }, new int [] { 1, 4 } }, false);
	}
	
	public void runTest(final int n, final int[][] edges, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n, edges });
		
		for (Object answer : answers) 
			assertThat((Boolean) answer).isEqualTo (expectedOutput);
		
		System.out.println("ok!");
	}    
    
}
