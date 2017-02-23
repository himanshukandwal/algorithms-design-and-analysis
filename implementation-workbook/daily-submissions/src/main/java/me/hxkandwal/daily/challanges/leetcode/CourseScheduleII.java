package me.hxkandwal.daily.challanges.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 210. Course Schedule II
 * 
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
 * which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to 
 * finish all courses.
 * 
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return 
 * an empty array.
 * 
 * For example:
 * 		2, [[1,0]]
 * 		There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]
 * 
 * 		4, [[1,0],[2,0],[3,1],[3,2]]
 * 		There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should 
 * 		be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
 * 
 * Note:
 * 		1. The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * 		2. You may assume that there are no duplicate edges in the input prerequisites.
 * 
 * @author Hxkandwal
 */
public class CourseScheduleII extends AbstractCustomTestRunner {

	public static class Node {
        int val;
        List<Node> in = new ArrayList<>();
        List<Node> out = new ArrayList<>();
        int indegree;
        
        public Node (int val) { this.val = val; }
    }
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List <Node> nodes = new ArrayList<>();
        for (int idx = 0; idx < numCourses; idx ++) nodes.add (new Node (idx));
        
        for (int[] pre : prerequisites) {
            nodes.get (pre [0]).out.add (nodes.get (pre [1]));
            nodes.get (pre [1]).in.add (nodes.get (pre [0]));
        }
        
        Queue<Node> queue = new LinkedList<>();
        for (Node node : nodes) 
            if ((node.indegree = node.in.size()) == 0) queue.offer (node);
            
        int idx = numCourses - 1;
        int[] ans = new int [numCourses]; 
        while (!queue.isEmpty()) {
            Node node = queue.poll ();
            ans [idx --] = node.val;
            for (Node otherNode : node.out) 
                if (-- otherNode.indegree == 0) queue.offer (otherNode);
        }
        return (idx < 0) ? ans : new int[] {};
    }
    
}
