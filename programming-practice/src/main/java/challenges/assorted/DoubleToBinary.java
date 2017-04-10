package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * How to convert double number to binary.
 * 
 * @author Hxkandwal
 */
public class DoubleToBinary extends AbstractCustomTestRunner {
	
	public static String _toBinary(double d, int precision) {
	    long wholePart = (long) d;
	    return wholeToBinary(wholePart) + '.' + fractionalToBinary(d - wholePart, precision);
	}

	private static String wholeToBinary(long l) {
	    return Long.toBinaryString(l);
	}

	private static String fractionalToBinary(double num, int precision) {
	    StringBuilder binary = new StringBuilder();
	    while (num > 0 && binary.length() < precision) {
	        double r = num * 2;
	        if (r >= 1) {
	            binary.append(1);
	            num = r - 1;
	        } else {
	            binary.append(0);
	            num = r;
	        }
	    }
	    return binary.toString();
	}
	
	// other way.
	public static String _printBinary(String n) {
		int intPart = Integer.parseInt(n.substring(0, n.indexOf('.')));
		double decPart = Double.parseDouble(n.substring(n.indexOf('.'), n.length()));
		String int_string = "";
		
		while (intPart > 0) {
			int r = intPart % 2;
			intPart >>= 1;
			int_string = r + int_string;
		}
		
		StringBuffer dec_string = new StringBuffer();
		while (decPart > 0) {
			if (dec_string.length() > 32)
				return "ERROR";
			if (decPart == 1) {
				dec_string.append((int) decPart);
				break;
			}
			double r = decPart * 2;
			if (r >= 1) {
				dec_string.append(1);
				decPart = r - 1;
			} else {
				dec_string.append(0);
				decPart = r;
			}
		}
		return int_string + "." + dec_string.toString();
	}
	
	// driver method
	public static void main(String[] args) throws Exception {
		assertThat(_printBinary("3.72")).isEqualTo("11.10011");
	}
	
	public void runTest(final String line, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { line });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
