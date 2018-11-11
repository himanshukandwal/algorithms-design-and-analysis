package challenges.hackerrank;

import challenges.AbstractCustomTestRunner;

import java.util.Scanner;
import java.util.Stack;

/**
 * Simple Text Editor
 *
 * link: https://www.hackerrank.com/challenges/simple-text-editor/problem
 */
public class SimpleTextEditor extends AbstractCustomTestRunner {

    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        int operations = sc.nextInt();
        Stack<String> stack = new Stack<>();
        String s = "";
        while (operations -- > 0) {
            int type = sc.nextInt();
            switch (type) {
                case 1: stack.push(s);
                    s = s + sc.next();
                    break;
                case 2: stack.push(s);
                    int del = sc.nextInt();
                    s = s.substring(0, s.length() - del);
                    break;
                case 3: int pr = sc.nextInt();
                    System.out.println(s.charAt(pr - 1));
                    break;
                case 4: if (!stack.isEmpty()) s = stack.pop();
                    break;
            }
        }
    }
}
