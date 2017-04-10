package big.number.arithmetic.model;

import java.util.Iterator;
import java.util.List;

import big.number.arithmetic.util.ConversionUtils;

/**
 * Class implementing level 2 features.
 * 
 * @author G31
 *
 */
public class ComplexNumberNode extends NumberNode {
	
	/**
	 * Default Constructor
	 */
	public ComplexNumberNode() {
		super();
	}

	/**
	 * Constructor with the long Parameter
	 * 
	 * Convert to required base and calls add to "this" List method(fillNumber).
	 * 
	 * @param stringVal
	 */
	public ComplexNumberNode(long longVal) {
		super(longVal);
	}

	/**
	 * Constructor with the String Parameter
	 * 
	 * @param stringVal
	 */
	public ComplexNumberNode(String stringVal) {
		super(stringVal);
	}
	
	/**
	 * Constructor with the Direct value
	 * 
	 * @param stringVal
	 */
	public ComplexNumberNode(List<Long> value) {
		super(value);
	}
	
	/**
	 * helper function that checks whether a NumberNode is zero or not..
	 * 
	 * NumberNode c = NumberNode a ^ NumberNode b
	 * 		
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static boolean isZero(NumberNode a) {
		return (a.getValue().size() == 1 && a.getLast() == 0);
	}
	
	/**
	 * wrapper function that performs the sum of two NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a + NumberNode b
	 * 
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode sum(NumberNode a, NumberNode b) {
		NumberNode c = null;
		
		if (a.isNegative() && b.isNegative()) {
			c = NumberNode.sum(a.negate(), b.negate()).negate();
		} 
		else if (a.isNegative() || b.isNegative()) {
			boolean isBiggerNumberNegative = (a.abs().compareTo(b.abs()) < 0 ? b.isNegative() : a.isNegative());
			
			c = NumberNode.subtract(a.isNegative() ? a.negate() : a, b.isNegative() ? b.negate() : b);
			
			if ((isBiggerNumberNegative && !c.isNegative()) || (!isBiggerNumberNegative && c.isNegative()))
				c.negate();
		}
		else {
			c = NumberNode.sum(a, b);
		}
		
		return c;
	}
	
	/**
	 * wrapper function that performs the difference of two NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a - NumberNode b
	 * 
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode subtract(NumberNode a, NumberNode b) {
		NumberNode c = null;
		
		if (a.isNegative() && b.isNegative()) {
			c = NumberNode.subtract(a.negate(), b.negate()).negate();
		} 
		else if (a.isNegative() || b.isNegative()) {
			boolean toNegate = a.isNegative();
			
			c = NumberNode.sum(a.isNegative() ? a.copy().negate() : a, b.isNegative() ? b.copy().negate() : b);
			
			if (toNegate)
				c.negate();
		}
		else {
			c = NumberNode.subtract(a, b);
		}
		
		return c;
	}
	
	
	/**
	 * wrapper function that performs the multiplication of two NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a * NumberNode b
	 * 
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode product(NumberNode a, NumberNode b) {
		NumberNode c = null;
		
		if (a.isNegative() && b.isNegative()) {
			c = NumberNode.product(a.negate(), b.negate());
		} 
		else if (a.isNegative() || b.isNegative()) {
			c = NumberNode.product(a.isNegative() ? a.negate() : a, b.isNegative() ? b.negate() : b).negate();
		}
		else {
			c = NumberNode.product(a, b);
		}
		
		return c;
	}
	
	/**
	 * wrapper function that performs the division operation on the two NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a / NumberNode b
	 * 
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode divide(NumberNode a, NumberNode b) {
		boolean toNegate = false;
		
		if ((a.isNegative() || b.isNegative()) && !(a.isNegative() && b.isNegative()))
			toNegate = true;
		
		NumberNode c = NumberNode.divide(a.isNegative() ? a.negate() : a, b.isNegative() ? b.negate() : b);
		
		if (toNegate) 
			c.negate();
		
		return c;
	}

	/**
	 * function that performs the power operation on two NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a ^ NumberNode b
	 * 		
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode power(NumberNode a, NumberNode b) {
		boolean toNegate = a.isNegative();
		
		a = (a.isNegative() ? a.negate() : a);
		NumberNode c = new NumberNode(1);
		
		Iterator<Long> powerNumberNodeIterator = b.getValue().iterator();
		int loopcounter = 0;
		while (powerNumberNodeIterator.hasNext()) {
			Long powerVal = powerNumberNodeIterator.next();
			powerVal *= ConversionUtils.shiftBase(b.getBaseValue(), loopcounter ++);
			
			// c *= a ^ powerVal 
			c = NumberNode.product(c, NumberNode.power(a, powerVal));
		}
		
		if (toNegate) 
			c.negate();
		
		return c;
	}
	
	/**
	 * function that performs the modulus operation on two NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a % NumberNode b
	 * 		
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode mod(NumberNode a, NumberNode b) {
		if ((b.getValue().size() == 1 && b.getValue().get(0) == 0))
			throw new IllegalArgumentException("Argument 'divisor' is 0");
		
		if (a.compareTo(b) == 0 || isZero(a))
			return new NumberNode(0);
		
		NumberNode intermediateDifference = a; 
		while (!intermediateDifference.isNegative())
			intermediateDifference = subtract(intermediateDifference, b);
		
		NumberNode c = (intermediateDifference.isNegative() && (intermediateDifference.abs().compareTo(b.abs()) != 0) ? sum(b, intermediateDifference) : new NumberNode(0));		
		return c;
	}
	
	/**
	 * function that performs the squareRoot operation on two NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a ~ 
	 * 		
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode squareRoot(NumberNode a) {
		if (isZero(a))
			return a;
		
		NumberNode low = new NumberNode(1l);
		NumberNode high = a.copy();
		NumberNode sqrtVal = null;
		
		while (sum(low, ONE).compareTo(high) < 0) {
			NumberNode mid = divide(sum(low, high), TWO);
			
			if (a.compareTo(product(mid, mid)) < 0)
				high = mid;
			else if (a.compareTo(product(mid, mid)) > 0)
				low = mid;
			else {
				sqrtVal = mid;
				break;
			}
		}
		
		sqrtVal = (sqrtVal == null ? low : sqrtVal);
		return sqrtVal;
	}
	
	/**
	 * function that computes the factorial operation on the NumberNode passed.
	 * 
	 * NumberNode c = NumberNode a ! 
	 * 		
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode factorial(NumberNode a) {
		if (isZero(a) || a.isNegative())
			return ONE.copy();
		
		return product(a, factorial(subtract(a, ONE)));
	}
}
