package challenges.codefights;

import static org.junit.Assert.assertEquals;

/*
 * We have given an array of characters arr, which consists of sequences of characters (words) separated by space characters.
 * 
 * How can we most efficiently reverse the order of words in the sentence?
 * 
 * For example:
 * [ 'p', 'e', 'r', 'f', 'e', 'c', 't', ' ', 'm', 'a', 'k', 'e', 's', ' ', 'p', 'r', 'a', 'c', 't', 'i', 'c', 'e' ]
 * 
 * would turn into:
 * [ 'p', 'r', 'a', 'c', 't', 'i', 'c', 'e', ' ', 'm', 'a', 'k', 'e', 's', ' ', 'p', 'e', 'r', 'f', 'e', 'c', 't' ]
 * 
 */
public class ReverseOrderOfWords {

	public static void main(String[] args) {
		assertEquals("practice makes perfect", String.valueOf(reverse("perfect makes practice".toCharArray())));
		assertEquals("practice makes perfect,", String.valueOf(reverse("perfect, makes practice".toCharArray())));
		
		System.out.println("ok !");
	}
	
	public static char[] reverse(char[] input) {
		int len = input.length;
		
		char[] output = new char[len];
		
		int previousInputSavepoint = 0;
		int previousOutputSavepoint = len;
		boolean continueCopy = false;
		
		for (int idx = 0; idx < len; idx ++) {
			if (Character.isWhitespace(input[idx]) || continueCopy) {
				int effectiveLength = idx - previousInputSavepoint;
				System.arraycopy(input, previousInputSavepoint, output, (previousOutputSavepoint - effectiveLength) , effectiveLength);
				previousInputSavepoint = idx;
				previousOutputSavepoint -= effectiveLength;
				continueCopy = true;
			}
			
			if (Character.isLetter(input[idx]) && continueCopy) 
				continueCopy = false;
		}
	
		System.arraycopy(input, previousInputSavepoint, output, 0 , len - previousInputSavepoint);
		return output;
	}
}
