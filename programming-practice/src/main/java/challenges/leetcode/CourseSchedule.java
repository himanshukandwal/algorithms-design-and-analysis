package challenges.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * 207. Course Schedule
 * 
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * 
 * For example:
 * 		2, [[1,0]]
 * 		There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 * 
 * 		2, [[1,0],[0,1]]
 * 		There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 
 * 		you should also have finished course 1. So it is impossible.
 * 
 * Note:
 * 		The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * 		You may assume that there are no duplicate edges in the input prerequisites.
 * 
 * @author Hxkandwal
 */
public class CourseSchedule extends AbstractCustomTestRunner {
	
	// DFS solution
	public static class Node {
        int val;
        Set<Node> in = new HashSet<>();
        Set<Node> out = new HashSet<>();
        boolean seen, visited;
        int indegree;       // needed as dont want to remove the links directly.
        
        public Node (int val) { this.val = val; }
    }
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Node> nodes = new ArrayList<>();
        for (int idx = 0; idx < numCourses; idx ++) nodes.add (new Node (idx));
        
        for (int[] pre : prerequisites) {
            // checking immediate dependencies.
            if (nodes.get (pre [0]).in.contains (nodes.get (pre [1]))) return false;
            nodes.get (pre [0]).out.add (nodes.get (pre [1]));
            nodes.get (pre [1]).in.add (nodes.get (pre [0]));
        }
        
        // check cyclicity.
        for (Node node : nodes) {
            if (node.in.size() == 0) {
                node.visited = node.seen = true;
                if (checkCycle (node)) return false;
                node.seen = false;
            }
        }
        for (Node node : nodes) if (!node.visited) return false;
        return true;
    }
    
    public boolean checkCycle (Node node) {
        for (Node other : node.out) {
            if (other.seen) return true;
            other.visited = other.seen = true;
            if (checkCycle (other)) return true;
            other.seen = false;
        }
        return false;
    }

    // BFS Solution
    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        List<Node> nodes = new ArrayList<>();
        for (int idx = 0; idx < numCourses; idx ++) nodes.add (new Node (idx));
        
        for (int[] pre : prerequisites) {
            // checking immediate dependencies.
            if (nodes.get (pre [0]).in.contains (nodes.get (pre [1]))) return false;
            nodes.get (pre [0]).out.add (nodes.get (pre [1]));
            nodes.get (pre [1]).in.add (nodes.get (pre [0]));
        }
        
        for (Node node : nodes) node.indegree = node.in.size();
        Queue<Node> queue = new LinkedList<>();
        for (Node node : nodes) if (node.indegree == 0) queue.offer (node);
        
        int processedNodes = 0;
        while (! queue.isEmpty()) {
            Node node = queue.poll ();
            processedNodes ++;
            
            for (Node outNode : node.out)
                if (-- outNode.indegree == 0) queue.offer (outNode);
        }
        
        return processedNodes == numCourses;
    }
    
}
