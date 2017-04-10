package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Program to mask Email and phone numbers.
 * 
 * email : 	mask pattern <first-valid-char>*****<last-valid-char>@domain.com
 * 		   	no two or more than two consecutive dots allowed anywhere.
 * 		   	can't start and end username in dots.
 * phone :	not mask last 4 digits. Make it in pattern xxx-xxx-1234			
 * 
 * @author Hxkandwal
 *
 */
public class MaskingEmailAndPhone extends AbstractCustomTestRunner {
	
	private static MaskingEmailAndPhone _instance = new MaskingEmailAndPhone();
	
	public MaskingEmailAndPhone() {}
	
	public static String _mask(String input) {
		return (input.startsWith("E:") ? maskEmail(input.substring(2)) : maskPhone(input.substring(2)));
	}
	
	private static String maskEmail(String input) {
		char[] charArr = input.toCharArray();
		StringBuilder sb = new StringBuilder();
		
		boolean foundStart = false;
		boolean lastCharDot = false;
		int lastUsedIdx = -1;
		
		for (int idx = 0; idx < charArr.length; idx++) {
			if (charArr[idx] == '@' && !foundStart) {
				foundStart  = true;
				sb.setCharAt(sb.length() - 1, (charArr[lastUsedIdx] == '.' ? charArr[lastUsedIdx - 1] : charArr [lastUsedIdx]));
				while (sb.length() > 7) 
					sb.deleteCharAt(sb.length()/2);
				
				while (sb.length() < 7) 
					sb.insert(sb.length()/2, '*');

			}
			
			if (foundStart)
				sb.append((idx == charArr.length && charArr[idx] == '.') ? "" : charArr[idx]);
			else {
				if (!(idx == 0 && charArr[idx] == '.') && !(lastCharDot && charArr[idx] == '.') &&
						("" + charArr[idx]).matches("[a-zA-Z0-9!#$%&'*+-/=?^_`{|}~.]")) {
					sb.append((sb.length() == 0) ? charArr[idx] : "*");
					lastUsedIdx = idx;
				} else {
					if (lastCharDot && (charArr[idx] == '.') && sb.length() > 1 && sb.charAt(sb.length() - 1) == '.')
						sb.deleteCharAt(sb.length() - 1);
				}
				
				lastCharDot = (charArr[idx] == '.');
			}
		}
		
		return sb.toString();
	}
	
	private static String maskPhone(String input) {
		char[] charArr = input.toCharArray();
		StringBuilder sb = new StringBuilder();
		
		for (int idx = 0; idx < charArr.length; idx++)
			if (Character.isDigit(charArr[idx]))
				if (idx < charArr.length - 4)
					sb.append("*");
				else
					sb.append(charArr[idx]);
		
		sb.insert(sb.length() - 4, '-');
		sb.insert(sb.length() - 8, '-');
		
		if (sb.length() > 12)
			sb.insert(sb.length() - 12, '-');
		
		if (input.charAt(0) == '+')
			sb.insert(0, '+');
		
		return sb.toString();
	}	
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("E:jd@twitter.com", "j*****d@twitter.com");
		_instance.runTest("E:jackAndJill@twitter.com", "j*****l@twitter.com");
		_instance.runTest("E:...jackAndJill@twitter.com", "j*****l@twitter.com");
		_instance.runTest("E:...jackAndJill.....@twitter.com", "j*****l@twitter.com");
		_instance.runTest("E:...jackAnd.....Jill.....@twitter.com", "j*****l@twitter.com");
		_instance.runTest("E:...jackAnd121Jill.....@twitter.com", "j*****l@twitter.com");
		_instance.runTest("E:...1jackAnd121Jill.....@twitter.com", "1*****l@twitter.com");
		_instance.runTest("P:(333)456-7890", "***-***-7890");
		_instance.runTest("P:3334567890", "***-***-7890");
		_instance.runTest("P:+1(333) 456-7890", "+*-***-***-7890");
		_instance.runTest("P:+13334567890", "+*-***-***-7890");
	}

	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
}
