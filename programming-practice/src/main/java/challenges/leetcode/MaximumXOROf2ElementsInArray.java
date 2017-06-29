package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 421. Maximum XOR of Two Numbers in an Array
 * 
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231. Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 * 
 * Could you do this in O(n) runtime?
 * 
 * Example:
 * 		Input: [3, 10, 5, 25, 2, 8]
 * 		Output: 28
 * 		Explanation: The maximum result is 5 ^ 25 = 28.
 * 
 * More info : https://threads-iiith.quora.com/Tutorial-on-Trie-and-example-problems
 *  
 * @author Hxkandwal
 */
public class MaximumXOROf2ElementsInArray extends AbstractCustomTestRunner {
	
	private static MaximumXOROf2ElementsInArray _instance = new MaximumXOROf2ElementsInArray();
	
	public class Node {
        private Node [] children = new Node [2];
       
        public void add (int num, int pos) {
           if (pos < 0) return;
           int child = (num >> pos) & 1;
           if (children [child] == null) children [child] = new Node();
           children [child].add (num,  pos - 1);
        }
       
        public int xorTree (int num, int pos, int ans) {
           if (pos < 0) return ans;
           Node node = null;
           if ((node = children [Math.abs(((num >> pos) & 1) - 1)]) != null) ans |= (1 << pos);
           else node = children [(num >> pos) & 1];
           return node != null ? node.xorTree (num, pos - 1, ans) : ans;
        }
	}

	// remember to walk from MSB to LSB, to attain right solution.
	public int _findMaximumXOR(int[] nums) {
        Node root = new Node ();
        int max = 0;
        for (int num : nums) {
               root.add(num, 31);			// add first so that we always have some path to fall back while processing XOR tree.
               max = Math.max (max, root.xorTree(num, 31, 0));
        }
        return max;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 5 }, 0);
		_instance.runTest(new int[] { 4, 6, 7 }, 3);
		_instance.runTest(new int[] { 5, 1, 4, 3, 0, 2 }, 7);
		_instance.runTest(new int[] { 2, 6, 1, 3, 5, 4, 8 }, 14);
	}

	public void runTest(final int[] input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
