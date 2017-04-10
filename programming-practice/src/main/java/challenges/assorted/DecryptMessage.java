package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Alice-bob message transmission.
 * 
 * @author Hxkandwal
 *
 */
public class DecryptMessage extends AbstractCustomTestRunner {
	
	private static DecryptMessage _instance = new DecryptMessage();
	
	private DecryptMessage() {}

	public static class KeyCrypt {
		int c; // 0 - lowercase, 1 -uppercase
		int value;
		public KeyCrypt(int c, int value) {
			this.c = c; this.value = value;
		}
	}
	
	public static String _decode(String message) {
		
		// encryption for : "Your friend, Alice", as this is the signature (which will always be there)
		
		KeyCrypt[] knownText = new KeyCrypt[] { 
				new KeyCrypt(0, 101), new KeyCrypt(0, 99), new KeyCrypt(0, 105), new KeyCrypt(0, 108), new KeyCrypt(1, 65), 
				null, null, new KeyCrypt(0, 100), new KeyCrypt(0, 110), new KeyCrypt(0, 101), new KeyCrypt(0, 105), new KeyCrypt(0, 114), 
				new KeyCrypt(0, 102), null, new KeyCrypt(0, 114), new KeyCrypt(0, 117), new KeyCrypt(0, 111), new KeyCrypt(1, 89) };

		List<Integer> keyElements = new ArrayList<>();

		for (int idx = 17; idx >= 0; idx--) {
			char ch = message.charAt(message.length() - idx - 1);

			if (Character.isAlphabetic(ch)) {
				KeyCrypt text = knownText[idx];
				
				int diff = (int) ch - text.value;
				if (diff < 0) {
					diff = ((text.c == 1) ? ((90 - text.value) + (65 - ch)) : ((122 - text.value) + (97 - ch)));
					diff ++;
				}
				
				keyElements.add(diff);
			}
		}

		StringBuilder key = new StringBuilder();
		StringBuilder copykey = new StringBuilder();
		
		boolean foundTwice = false;
		for (Integer element : keyElements) {
			
			if (key.toString().contains("" + element)) {
				copykey.append(element);
				
				if (copykey.equals(key))
					break;
				
				foundTwice = true;
			} else {
				if (foundTwice) {
					key.append(copykey);
					foundTwice = false;
					copykey.setLength(0);
				}
				
				key.append(element);
			}
		}

		while (true) {
			copykey.setLength(0);
			
			for (int idx = 0, keyIdx = 0; idx < message.length(); idx++) {
				char ch = message.charAt(idx);

				if (Character.isAlphabetic(ch)) {
					int keyCh = Character.getNumericValue(key.charAt(keyIdx++));
					
					if (keyIdx >= key.length())
						keyIdx %= key.length();

					int diff = (int) ch - keyCh;
					
					// uppercase
					if (ch >= 65 && ch <= 90) {
						if (diff >= 65)
							copykey.append((char) diff);
						else
							copykey.append((char) (90 - (65 - diff - 1)));
					} else {
						// lowercase
						if (diff >= 97)
							copykey.append((char) diff);
						else
							copykey.append((char) (122 - (97 - diff - 1)));
					}
				} else
					copykey.append((char) ch);
			}
			
			if (copykey.indexOf("Your friend, Alice") > 0)
				break;
			else
				key.append(key.charAt(0)).deleteCharAt(0);
		}
		
		return copykey.toString();
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("Otjfvknou kskgnl, K mbxg iurtsvcnb ksgq hoz atv. Vje xcxtyqrl "
				+ "vt ujg smewfv vrmcxvtg rwqr ju vhm ytsf elwepuqyez. - Atvt hrqgse, Cnikg", 
				"Greetings friend, I have important info for you. The password to the secret "
				+ "treasure room is the word clocktower. - Your friend, Alice");
	}

	
	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
