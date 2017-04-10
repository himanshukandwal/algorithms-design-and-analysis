package big.number.arithmetic.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import big.number.arithmetic.util.ConversionUtils;

/**
 * Main Class implementing all of the features of Level 1 problems.
 * 
 * @author G31
 */
public class NumberNode extends AbstractNumberNode implements Comparable<NumberNode> {

	// Required base in which operations to be performed.
	protected static final Integer BASE = 15;
	
	protected static final NumberNode ZERO = new NumberNode(0);
	
	protected static final NumberNode ONE = new NumberNode(1);
	
	protected static final NumberNode TWO = new NumberNode(2);

	private boolean useDecimalBase;
	
	/**
	 * Default Constructor
	 */
	public NumberNode() {
	}

	/**
	 * Decimal base Constructor
	 */
	public NumberNode(boolean useDecimalBase) {
		this.useDecimalBase = useDecimalBase;
	}
	
	/**
	 * Constructor with the long Parameter
	 * 
	 * Convert to required base and calls add to "this" List method(fillNumber).
	 * 
	 * @param stringVal
	 */
	public NumberNode(long longVal) {
		setValue(ConversionUtils.convertToBase(longVal, getBaseValue()));
	}
	
	/**
	 * Constructor with the long Parameter and Decimal Base.
	 * 
	 * Convert to required base and calls add to "this" List method(fillNumber).
	 * 
	 * @param stringVal
	 */
	public NumberNode(long longVal, boolean useDecimalBase) {
		this(useDecimalBase);
		setValue(ConversionUtils.convertToBase(longVal, getBaseValue()));
	}

	/**
	 * Constructor with the String Parameter
	 * 
	 * @param stringVal
	 */
	public NumberNode(String stringVal) {
		setValue(ConversionUtils.convertToBase(stringVal, getBaseValue()));
	}
	
	/**
	 * Constructor with the Direct value
	 * 
	 * @param stringVal
	 */
	public NumberNode(List<Long> value) {
		setValue(value);
	}
	
	/**
	 * overridden abstract method
	 */
	@Override
	protected int getBaseValue() {
		if (isUseDecimalBase())
			return 10;
		return BASE;
	}
	
	/**
	 * getter function for field useDecimalBase.
	 * 
	 * @return
	 */
	public boolean isUseDecimalBase() {
		return useDecimalBase;
	}
	
	/**
	 * setter function for field useDecimalBase.
	 * 
	 * @return
	 */
	public NumberNode setUseDecimalBase(boolean useDecimalBase) {
		this.useDecimalBase = useDecimalBase;
		return this;
	}
	
	/**
	 * helper function providing the copy with the Direct value
	 * 
	 * @param stringVal
	 */
	public NumberNode copy() {
		return new NumberNode(copyValue());
	}
	
	/**
	 * helper function returning the negative value of a number node.
	 * 
	 * @param stringVal
	 */
	public NumberNode negate() {
		negateValue();
		return this;
	}
	
	/**
	 * helper function to remove the trailing zeroes.
	 * 
	 * @return
	 */
	public NumberNode removeTrailingZeros() {
		removeTrailingZerosFromValue();
		return this;
	}
	
	/**
	 * helper function to know whether a number is negative or not.
	 * 
	 * @return
	 */
	public boolean isNegative() {
		return getValue().get(getValue().size() - 1) < 0;
	}
	
	/**
	 * compares two NumberNodes
	 */
	@Override
	public int compareTo(NumberNode o) {
		Long returnVal = 0l;
		
		if (getValue().size() > o.getValue().size()) {
			returnVal =  1l;
		} else if (getValue().size() < o.getValue().size()) {
			returnVal = -1l;	
		} else {
			returnVal = compareValues(o.getValue());
		}
		
		return returnVal.intValue(); 
	}
	
	/**
	 * function to print the list.
	 */
	public void printList() {
		StringBuffer sb = new StringBuffer();
		sb.append(getBaseValue() + " : ");

		for (Long element : getValue())
			sb.append(element).append(" ");
			
		System.out.println(sb.toString());
	}

	/**
	 * helper function that computes the absolute list.
	 * 
	 * @param list
	 * @return
	 */
	public NumberNode abs() {
		if (!isNegative()) {
			return this;
		} else {
			List<Long> copyList = new LinkedList<>(getValue());
			NumberNode node = new NumberNode(copyList);
			return node.negate();
		}
	}
	
	/**
	 * helper function that left shifts once the number node.
	 * 
	 * @param list
	 * @return
	 */
	public NumberNode shift(int n) {
		for (int counter = 0; counter < n; counter++) {
			shift();
		}
		return this;
	}
	
	/**
	 * helper function that left shifts once the number node.
	 * 
	 * @param list
	 * @return
	 */
	public NumberNode shift() {
		getValue().add(0, 0l);
		return this;
	}
	
	/**
	 * function that performs the sum of two positive NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a + NumberNode b
	 * 
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode sum(NumberNode a, NumberNode b) {
		List<Long> x = a.getValue();
		List<Long> y = b.getValue();

		NumberNode c = new NumberNode(a.isUseDecimalBase());
		List<Long> z = c.getValue();
		
		long carry = 0;
		
		Iterator<Long> xIterator = x.iterator();
		Iterator<Long> yIterator = y.iterator();
		
		while (xIterator.hasNext() || yIterator.hasNext()) {
			
			long addedSum = getNext(xIterator) + getNext(yIterator) + carry;
			
			if (addedSum > 0) {
				List<Long> innerIntermediateSumList = ConversionUtils.convertToBase(addedSum, a.getBaseValue());
				if (innerIntermediateSumList.size() > 1) {
					carry = innerIntermediateSumList.get(1);
					addedSum = innerIntermediateSumList.get(0);
				} else {
					carry = 0;
				}
			}
			
			z.add(addedSum);
		}
		
		if (carry != 0)
			z.add(carry);
		
		return c;
	}

	/**
	 * function that performs the difference of two positive NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a - NumberNode b
	 * 
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode subtract(NumberNode a, NumberNode b) {
		a.removeTrailingZeros();
		b.removeTrailingZeros();
		
		if (a.getValue().size() == 1 && a.getValue().get(0) == 0)
			return b.copy().negate();
		else if (b.getValue().size() == 1 && b.getValue().get(0) == 0)
			return a;
		
		boolean negative  = (a.compareTo(b) < 0);
		
		List<Long> x = (negative ? b.getValue() : a.getValue());
		List<Long> y = (negative ? a.getValue() : b.getValue());
		
		NumberNode c = new NumberNode(a.isUseDecimalBase());
		List<Long> z = c.getValue();
		
		int borrow = 0;
		
		Iterator<Long> xIterator = x.iterator();
		Iterator<Long> yIterator = y.iterator();
		
		while (xIterator.hasNext()) {
			Long firstValue = getNext(xIterator);
			if (borrow > 0) {
				firstValue = firstValue - borrow;
				borrow = 0;
			}

			Long secondValue = getNext(yIterator);
			if (secondValue > firstValue) {
				borrow ++;
				firstValue = firstValue + a.getBaseValue();
			}
			
			Long intermediateResult = firstValue - secondValue;
			
			if (intermediateResult > 0)
				intermediateResult = ConversionUtils.convertToBase(intermediateResult, a.getBaseValue()).get(0);
			
			z.add(intermediateResult);
		}
		
		c.removeTrailingZeros();
		return (negative ? c.negate() : c);
	}

	/**
	 * function that performs the multiplication of two NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a * NumberNode b
	 * 
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode product(NumberNode a, NumberNode b) {
		
		List<Long> x = a.getValue();
		List<Long> y = b.getValue();

		NumberNode c = new NumberNode(0, a.isUseDecimalBase());
		
		Iterator<Long> xIterator = x.iterator();
		
		long globalCarry = 0;
		long rowOffset = 0;
		
		while (xIterator.hasNext()) {
			long carry = globalCarry;
			
			NumberNode productRowRepresentation = new NumberNode();

			Long xvalue = xIterator.next();
			Iterator<Long> yIterator = y.iterator();
			
			long currentRowOffset = rowOffset;
			
			// add the row offset
			while (currentRowOffset != 0) {
				productRowRepresentation.getValue().add(0l);
				currentRowOffset --;
			}
			
			while (yIterator.hasNext()) {
				long rowIntermediateProduct = yIterator.next() * xvalue + carry;
			
				if (rowIntermediateProduct > 0) {
					List<Long> rowIntermediateBaseProductList = ConversionUtils.convertToBase(rowIntermediateProduct, a.getBaseValue());	
					if (rowIntermediateBaseProductList.size() > 1) {
						carry = rowIntermediateBaseProductList.get(1);
						rowIntermediateProduct = rowIntermediateBaseProductList.get(0);
					} else {
						carry = 0;
					}
				}
				
				productRowRepresentation.getValue().add(rowIntermediateProduct);
			}
			
			if (carry > 0)
				productRowRepresentation.getValue().add(carry);
			
			/* c += productRowRepresentation */
			c = NumberNode.sum (c, productRowRepresentation).setUseDecimalBase(a.isUseDecimalBase());
			rowOffset ++;
		}
		
		return c.removeTrailingZeros();
	}
	
	/**
	 * inner function that performs the division operation on the two positive NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a / NumberNode b
	 * 
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode divide(NumberNode a, NumberNode b) {

		NumberNode c = null;
		
		List<Long> x = a.removeTrailingZeros().getValue();
		List<Long> y = b.removeTrailingZeros().getValue();
		
		boolean negative = a.isNegative() || a.isNegative();
		
		if (y.isEmpty() || (y.size() == 1 && y.get(0) == 0)) {
			throw new IllegalArgumentException("Argument 'divisor' is 0");
		}
		else if (x.isEmpty() || (x.size() == 1 && x.get(0) == 0)) {
			c = new NumberNode(0);
		} 
		else {
			int count = 0;
			NumberNode intermediateDifference = a; 
				
			while (!intermediateDifference.isNegative()) {
				count++;
				intermediateDifference = subtract(intermediateDifference, b);
			}
			
			if (intermediateDifference.isNegative())
				count --;

			c = new NumberNode(count, a.isUseDecimalBase());
		}
		
		return (negative ? c.negate() : c);
	}
	
	/**
	 * function that performs the power operation on a NumberNode.
	 * 
	 * NumberNode c = NumberNode a ^ int b
	 * 
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode power(NumberNode x, long i) {
		if (i <= 0)
			return new NumberNode(1);
		else if (i == 1)
			return x;
		else {
			// Logic to get half of the list.
			NumberNode half = power (x, i / 2);
			NumberNode res = product(half, half);
			
			if ((i % 2) == 0)
				return res;
			else
				return (product(res, x)).removeTrailingZeros();
		}
	}
	
	/**
	 * helper function to Iterate and return the next value.
	 * 
	 * Returns 0 if list has ended.
	 * 
	 * @param iterator
	 * @return
	 */
	private static Long getNext(Iterator<Long> iterator) {
		return (iterator.hasNext() ? iterator.next() : 0l);
	}

	/**
	 * helper function that computes the base10 string representation of the numberNode.
	 * 
	 * @return
	 */
	private String base10Representation() {
		NumberNode resultingNode = ZERO.copy().setUseDecimalBase(true);
		NumberNode powerNode = ONE.copy().setUseDecimalBase(true);
		
		NumberNode baseValueNumberNode = new NumberNode(getBaseValue(), true);
		
		for (int loop = 0; loop < getValue().size(); loop++) {
			if (loop > 0)
				powerNode = product(powerNode, baseValueNumberNode).setUseDecimalBase(true);
			
			NumberNode intermediateProduct = product(powerNode, new NumberNode(getValue().get(loop), true)).setUseDecimalBase(true);
			resultingNode = sum(resultingNode, intermediateProduct).setUseDecimalBase(true);
		}
		
		StringBuffer buffer = new StringBuffer();
		boolean foundNegative = false;
		
		for (Long longVal : resultingNode.getValue()) {
			if (longVal < 0) {
				foundNegative = true;
				longVal *= -1;
			}
			buffer.append(longVal);
		}
		String representation = buffer.reverse().toString();
		return (foundNegative ? "-" + representation : representation);
	}
	
	/**
	 * overridden toString method.
	 */
	@Override
	public String toString() {
		return base10Representation();
	}
	
}