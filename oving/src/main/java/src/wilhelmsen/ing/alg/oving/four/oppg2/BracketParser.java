package src.wilhelmsen.ing.alg.oving.four.oppg2;

import java.util.Stack;

/**
 * Created by Harald on 14.9.16.
 */
public class BracketParser {
    public static boolean isValid(String input, char[] opening, char[] closing) {
        Stack<Character> brackets = new Stack<Character>();

        for (char c : input.toCharArray()) {
            if (!isBracket(c, opening, closing)) {
                continue;
            }
            int counterIndex = getIndex(opening, c);
            boolean isOpening = true;
            if (counterIndex < 0) {
                isOpening = false;
                counterIndex = getIndex(closing, c);
            }

            if (isOpening) {
                brackets.push(c);
                System.out.println(c + " pushed");
            } else {
                if (brackets.peek() == opening[counterIndex]) {
                    brackets.pop();
                    System.out.println(c + " popped");
                } else {
                    return false;
                }
            }
        }

        return brackets.isEmpty();
    }

    private static boolean isBracket(char c, char[] chars1, char[] chars2) {
        int index = getIndex(chars1, c);
        int index2 = getIndex(chars2, c);
        return index != -1 || index2 != -1;
    }

    private static int getIndex(char[] chars, char c) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c)
                return i;
        }
        return -1;
    }
}
