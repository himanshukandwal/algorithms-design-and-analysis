package big.number.arithmetic.main;

import big.number.arithmetic.model.NumberNode;

/**
 * Driver Class for level 1.
 * 
 * @author rbk
 * @modifier G31
 *
 */
public class LevelOneDriver {

	public static void main(String[] args) {
		NumberNode a = new NumberNode("1234567890123456789012345678901234567890");
		NumberNode b = new NumberNode(999);
		NumberNode c = NumberNode.sum(a, b);
		NumberNode d = NumberNode.subtract(c, a);
		NumberNode e = NumberNode.product(c, a);
		NumberNode zero = new NumberNode(0);
		NumberNode f = NumberNode.product(a, zero);
		NumberNode two = new NumberNode(2);
		NumberNode g = NumberNode.power(two, 1025);
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("c=a+b = " + c);
		System.out.println("a+b-a = " + d);
		System.out.println("a*c = " + e);
		System.out.println("a*0 = " + f);
		System.out.println("2^1025 = " + g);
		System.out.println("Internal representation:");
		g.printList();
	}

}
