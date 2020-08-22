package stack_problem;


import java.util.Stack;

/**
 * O(n)
 * O(n)
 */
public class Solution20 {

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {

                if (stack.size() == 0) {
                    return false;
                }

                Character cur = stack.pop();
                Character match = null;
                if (c == ')') {
                    match = '(';
                } else if (c == ']') {
                    match = '[';
                } else if (c == '}') {
                    match = '{';
                }

                if (cur != match) {
                    return false;
                }
            }
        }

        return stack.size() == 0;
    }

}