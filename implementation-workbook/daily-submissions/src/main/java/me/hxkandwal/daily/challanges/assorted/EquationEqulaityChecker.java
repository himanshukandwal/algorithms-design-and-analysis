package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * link: https://careercup.com/question?id=5745616513662976
 * 
 * @author Hxkandwal
 *
 */
public class EquationEqulaityChecker extends AbstractCustomTestRunner {
	
	public static EquationEqulaityChecker _instance = new EquationEqulaityChecker();
	
	private EquationEqulaityChecker() {}
	
	public static class Node {
		Node parent;
		String value;
		int rank;
		
		public Node(String value) {
			this.value = value;
			this.parent = this;
		}
		
		@Override
		public String toString() {
			return "(" + value + ")";
		}
	}
	
	public static boolean _areEqual(String[] input1, String[] input2) {
		Map<String, Node> lookup = new HashMap<>();
		
		// construct from input1;
		for (String item : input1) {
			String value1 = item.split("=")[0].trim();
			String value2 = item.split("=")[1].trim();
			
			Node n1 = null, n2 = null;
			if (lookup.containsKey(value1))
				n1 = lookup.get(value1);
			else
				lookup.put(value1, n1 = new Node(value1));

			if (lookup.containsKey(value2))
				n2 = lookup.get(value2);
			else
				lookup.put(value2, n2 = new Node(value2));
			
			Node rn1 = find (n1);
			Node rn2 = find (n2);
			
			union(rn1, rn2);
		}
		
		for (String item : input2) {
			if (item.contains("!=")) {
				Node n1 = lookup.get (item.split("!=")[0].trim());
				Node n2 = lookup.get (item.split("!=")[1].trim());
				
				Node rn1 = find (n1);
				Node rn2 = find (n2);
				
				if (rn1 == rn2)
					return false;
			} else {
				Node n1 = lookup.get (item.split("=")[0].trim());
				Node n2 = lookup.get (item.split("=")[1].trim());
				
				Node rn1 = find (n1);
				Node rn2 = find (n2);
				
				if (rn1 != rn2)
					return false;
			}
		}
		
		return true;
	}
	
	private static Node find (Node node) {
		if (node != node.parent)
			node.parent = find (node.parent);
		return node.parent;
	}
	
	private static void union(Node node1, Node node2) {
		if (node1.rank > node2.rank) 
			node2.parent = node1;
		else if (node2.rank > node1.rank)
			node1.parent = node2;
		else {
			node2.parent = node1;
			node1.rank ++;
		}
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new String[] { "A = B", "B = D", "C = D", "F = G", "E = H", "H = C" },
						  new String[] { "A != C", "E = H" }, false);
	}

	
	public void runTest(final String[] input1, final String[] input2, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input1, input2 });
		
		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
