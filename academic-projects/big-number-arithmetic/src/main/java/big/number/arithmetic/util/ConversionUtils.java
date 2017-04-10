package big.number.arithmetic.util;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import big.number.arithmetic.model.NumberNode;

/**
 * Utility class for handing conversion operations.
 * 
 * @author G31
 *
 */
public class ConversionUtils {

	public static List<Long> convertToBase(String input, int base) {
		List<Long> resultingList = null;
		try {
			resultingList = convertToBase(Long.valueOf(input), base);
		} catch (NumberFormatException exception) {
			int len = input.length();
			String subString = input.substring(0, len/2);
			
			NumberNode temporaryNumberNode = new NumberNode(convertToBase(subString, base));
			StringBuffer sb = new StringBuffer().append(1);
			for (int loop = 0; loop < len - subString.length(); loop++)
				sb.append("0");
			
			temporaryNumberNode = NumberNode.product(temporaryNumberNode, new NumberNode(sb.toString()));
			
			subString = input.substring(len/2);
			NumberNode anotherTemporaryNumberNode = new NumberNode(convertToBase(subString, base));
			
			resultingList = NumberNode.sum(temporaryNumberNode, anotherTemporaryNumberNode).getValue();
		}
		return resultingList;
	}
	
	public static List<Long> convertToBase(long input, int base) {
		boolean isNegative = input < 0;

		if (isNegative)
			input *= -1;

		List<Long> resultingList = positiveConvertToBase(input, base);

		if (isNegative) {
			ListIterator<Long> listIterator = resultingList.listIterator();
			
			while (listIterator.hasNext())
				listIterator.next();
			
			listIterator.set(0l - resultingList.get(resultingList.size() - 1));
		}

		return resultingList;
	}
	
	private static List<Long> positiveConvertToBase(long input, int base) {
		List<Long> resultingList = new LinkedList<>();
		
		if (input > 0) {
			while (input / base > 0 || input % base > 0) {
				resultingList.add(input % base);
				input /= base;
			}
		} else {
			resultingList.add(0l);
		}
		
		return resultingList;
	}
	
	public static long shiftBase(int base, int times) {
		long extendedBase = 1;
		for (int counter = 0; counter < times; counter++)
			extendedBase *= base;
		
		return extendedBase;
	}
	
	public static boolean verifyBaseRepresentation(List<Long> list, long input, int base) {
		if (input > 0 && (list == null || list.size() == 0))
			return false;
		
		long resultingNumber = 0;
		int counter = 0;
		
		for (Long longVal : list)
			resultingNumber += longVal * Math.pow(base, counter ++); 

		return (resultingNumber == input ? true : false);
	}
	
	public static boolean verifyBaseRepresentation(List<Long> list, String input, int base) {
		StringBuffer buffer = new StringBuffer();
		
		boolean foundNegative = false;
		for (Long longVal : list) {
			if (longVal < 0) {
				foundNegative = true;
				longVal *= -1;
			}
			buffer.append(longVal);
		}
		String representation = buffer.reverse().toString();
		representation =  (foundNegative ? "-" + representation : representation);

		return representation.equals(input);
	}
	
}
