package challenges.hackerrank;

import challenges.AbstractCustomTestRunner;

/**
 * Tries: Contacts
 * 
 * 
 * link: https://www.hackerrank.com/challenges/ctci-contacts?h_r=next-challenge&h_v=zen
 * 
 * @author Hxkandwal
 */
public class CountMatchingContacts extends AbstractCustomTestRunner {
	
	static class Trie {
        char ch;
        int words;
        Trie [] children = new Trie [26];
        
        Trie (char ch) { this.ch = ch; }
        
        void add (String word) {
            Trie t = this;
            for (char ch : word.toCharArray()) {
                if (t.children [ch - 'a'] == null) t.children [ch - 'a'] = new Trie (ch);
                t = t.children [ch - 'a'];
                t.words ++;
            }
        }
        
        int prefix (String word) {
            Trie t = this;
            for (char ch : word.toCharArray()) {
                if (t.children [ch - 'a'] == null) return 0;
                t = t.children [ch - 'a'];
            }
            return t.words;
        }
    }

	// driver method
	public static void main(String[] args) {
		Trie root = new Trie (' ');
		root.add("hack");
		root.add("hackerrank");
		root.add("hackerrankhackerrank");
		System.out.println(root.prefix("hac"));
		System.out.println(root.prefix("hak"));
	}	
	
}
