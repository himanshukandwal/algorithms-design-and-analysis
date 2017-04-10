package big.number.arithmetic.model;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import big.number.arithmetic.util.ConversionUtils;

/**
 * Abstract class containing all the background code for the NumberNode class.
 * 
 * @author G31
 *
 */
public abstract class AbstractNumberNode {
	
	private List<Long> value;
	
	/**
	 * getter function for input list
	 * 
	 * lazy initialization of value.
	 * 
	 */
	public List<Long> getValue() {
		if (value == null)
			value = new LinkedList<>();
		return value;
	}

	/**
	 * setter function for input list
	 */
	protected void setValue(List<Long> input) {
		this.value = input;
	}
	
	/**
	 * helper function to fetch the last element of a list.
	 * 
	 * @return
	 */
	protected Long getLast() {
		return (getValue() != null && getValue().size() > 0 ? getValue().get(getValue().size() - 1) : null);
	}
	
	/**
	 * Constructor with the Direct value
	 * 
	 * @param stringVal
	 */
	protected List<Long> copyValue() {
		return new LinkedList<>(getValue());
	}
	
	/**
	 * helper function returning the negative value of a number node.
	 * 
	 * @param stringVal
	 */
	protected void negateValue() {
		ListIterator<Long> valueListIterator = getValue().listIterator();
		while (valueListIterator.hasNext())
			valueListIterator.next();
		
		valueListIterator.set(getLast() * -1);
	}
	
	/**
	 * helper function to remove the trailing zeroes.
	 * 
	 * @return
	 */
	protected void removeTrailingZerosFromValue() {
		ListIterator<Long> valueListIterator = getValue().listIterator();
		while (valueListIterator.hasNext())
			valueListIterator.next();
		
		while (valueListIterator.hasPrevious() && (valueListIterator.previous() == 0)) 
			valueListIterator.remove();
		
		if (getValue().size() == 0)
			getValue().add(0l);
	}

	protected abstract int getBaseValue();
	
	/**
	 * for (unit) testing purpose only. function does not appear anywhere in other source classes. 
	 * 
	 * helper function that computes the long value of the numberNode representation.
	 * 
	 * @return
	 */
	public long baseRepresentation() {
		long resultingNumber = 0;
		int counter = 0;
		
		boolean foundNegative = false;
		for (Long longVal : getValue()) {
			if (longVal < 0) {
				foundNegative = true;
				longVal *= -1;
			}
			resultingNumber += longVal * ConversionUtils.shiftBase(getBaseValue(), counter ++);
		}
		return (foundNegative ? resultingNumber * -1 : resultingNumber) ; 
	}
		
	/**
	 * helper function to compare two values.
	 *  
	 * @param otherValue
	 * @return
	 */
	protected Long compareValues(List<Long> otherValue) {
		Long returnVal = 0l;
		ListIterator<Long> firstValueListIterator = getValue().listIterator();
		ListIterator<Long> secondValueListIterator = otherValue.listIterator();
		
		while (firstValueListIterator.hasNext())
			firstValueListIterator.next();
		
		while (secondValueListIterator.hasNext())
			secondValueListIterator.next();

		while (firstValueListIterator.hasPrevious() && secondValueListIterator.hasPrevious()) {
			Long firstValue = firstValueListIterator.previous();
			Long secondValue = secondValueListIterator.previous();
			
			if (firstValue > secondValue) { 
				returnVal = 1l;
				break;
			} else if (firstValue < secondValue) { 
				returnVal = -1l;
				break;
			}
		}
		
		return returnVal;
	}
	
}
