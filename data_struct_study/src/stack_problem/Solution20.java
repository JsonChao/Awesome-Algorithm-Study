package stack_problem;

import java.util.Stack;


/**
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class Solution20 {

    public boolean isValid(String s) {
        // 1、创建一个栈，注意 stack 中存的是字符：Character
        Stack<Character> stack = new Stack<>();

        // 2、遍历字符串
        for (int i = 0; i < s.length(); i++) {
            // 1）、在 Java 中字符以 '' 标记，如果当前字符是左括号则放入栈中
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                // 2）、如果当前字符是右括号而栈中没有左括号则匹配失败,
                // 这里依次判断三种括号匹配的情况即可
                if (stack.isEmpty()) {
                    return false;
                }

                Character topChar = stack.pop();
                if (c == ')' && topChar != '(') {
                    return false;
                } else if (c == ']' && topChar != '[') {
                    return false;
                } else if (c == '}' && topChar != '{') {
                    return false;
                }
            }
        }

        // 3、栈中没有元素才算匹配成功
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new Solution20().isValid("(({[{}]}))"));
    }

}