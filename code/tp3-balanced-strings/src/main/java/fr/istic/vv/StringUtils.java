package fr.istic.vv;

import java.util.ArrayDeque;
import java.util.Deque;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : str.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    // No matching open symbol
                    return false;
                }

                char openSymbol = stack.pop();
                if (!isMatching(openSymbol, ch)) {
                    // Symbols do not match
                    return false;
                }
            }
        }

        // Check if there are any unmatched open symbols left
        return stack.isEmpty();
    }

    public static boolean isMatching(char openSymbol, char closeSymbol) {
        return (openSymbol == '(' && closeSymbol == ')') ||
                (openSymbol == '[' && closeSymbol == ']') ||
                (openSymbol == '{' && closeSymbol == '}');
    }

}
