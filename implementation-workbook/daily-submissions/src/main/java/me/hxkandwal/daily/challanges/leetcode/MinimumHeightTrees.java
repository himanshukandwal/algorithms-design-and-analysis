package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 310. Minimum Height Trees
 * 
 * For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. 
 * Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, 
 * write a function to find all the MHTs and return a list of their root labels.
 * 
 * Format :  The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected 
 * 			 edges (each edge is a pair of labels).
 * 
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and 
 * thus will not appear together in edges.
 * 
 * Example 1:
 * 		Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 * 		
 * 		        0
 * 		        |
 * 		        1
 * 		       / \
 * 		      2   3
 * 		      
 * 		return [1]
 * 
 * Example 2:
 * 		Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 * 		
 * 		     0  1  2
 * 		      \ | /
 * 		        3
 * 		        |
 * 		        4
 * 		        |
 * 		        5
 * 		        
 * 		return [3, 4]
 * 
 * @author Hxkandwal
 */
@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class MinimumHeightTrees extends AbstractCustomTestRunner {
	
	private static MinimumHeightTrees _instance = new MinimumHeightTrees();
	
	// better, faster, cheaper
	public List<Integer> _findMinHeightTreesBetter(int n, int[][] edges) {
	    if (n == 1) return Collections.singletonList(0);

	    List<Set<Integer>> adj = new ArrayList<>(n);							// <<<<<<<<<<<<<<<<<<<<< no need to create node class, just storing adj list in list will do.
	    for (int i = 0; i < n; ++i) adj.add(new HashSet<>());
	    for (int[] edge : edges) {
	        adj.get(edge[0]).add(edge[1]);
	        adj.get(edge[1]).add(edge[0]);
	    }

	    List<Integer> leaves = new ArrayList<>();
	    for (int i = 0; i < n; ++i)
	        if (adj.get(i).size() == 1) leaves.add(i);

	    while (n > 2) {
	        n -= leaves.size();
	        List<Integer> newLeaves = new ArrayList<>();
	        for (int i : leaves) {
	            int j = adj.get(i).iterator().next();
	            adj.get(j).remove(i);
	            if (adj.get(j).size() == 1) newLeaves.add(j);
	        }
	        leaves = newLeaves;
	    }
	    return leaves;
	}
	
	// time consuming DFS from every node.
	public static class Node {
        int val;
        List<Node> adj = new ArrayList<>();
        boolean seen;
        
        public Node (int val) { this.val = val; }
       
        @Override
        public String toString() {
        	return String.valueOf(val + " : " + adj.size());
        }
    }
    
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        List<Node> nodes = new ArrayList<>();
        for (int idx = 0; idx < n; idx ++) nodes.add (new Node (idx));
        
        for (int[] edge : edges) {
            nodes.get (edge [0]).adj.add (nodes.get (edge [1]));
            nodes.get (edge [1]).adj.add (nodes.get (edge [0]));
        }
        
        int minHeight = Integer.MAX_VALUE;
        for (Node node : nodes) {
            node.seen = true;
            if (minHeight == Integer.MAX_VALUE) {
                minHeight = findHeight (node);
                ans.add (node.val);
            } else {
            	Integer thatHeight = findHeight (node);
            	if (minHeight > thatHeight) {
            		minHeight = thatHeight;
            		ans.clear();
            		ans.add (node.val);
            	} else if (minHeight == thatHeight)
            		ans.add (node.val);
            }
            node.seen = false;    
        }
            
        return ans;
    }
    
    public int findHeight (Node node) {
        if (node.adj.size() == 0) return 0;
        int maxh = 0;
        for (Node other : node.adj) {
            if (!other.seen) {
                other.seen = true;
                maxh = Math.max (maxh, findHeight (other) + 1);
                other.seen = false;
            }
        }
        return maxh;
    }
    
	// driver method
	public static void main(String[] args) {
//		_instance.runTest(4, new int[][] { new int [] { 1, 0 }, new int [] { 1, 2 }, new int [] { 1, 3 } }, new ArrayList() {{ add (1); }});
		_instance.runTest(6, new int[][] { new int [] { 3, 0 }, new int [] { 3, 1 }, new int [] { 3, 2 }, new int [] { 3, 4 }, 
							 new int [] { 5, 4 } }, new ArrayList() {{ add (3); add (4); }});
		_instance.runTest(6, new int[][] { new int [] { 0, 1 }, new int [] { 0, 2 }, new int [] { 0, 3 }, new int [] { 3, 4 }, 
			 				 new int [] { 5, 4 } }, new ArrayList() {{ add (3); }});
	}
	
	public void runTest(final int n, final int[][] edges, final List<Integer> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n, edges });
		
		for (Object answer : answers) 
			assertThat((List<Integer>) answer).isEqualTo (expectedOutput);
		
		System.out.println("ok!");
	}     
    
}
