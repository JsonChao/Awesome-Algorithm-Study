package stack;

class Solution {
    public boolean isValid(String s) {
        // 1、注意 stack 中存的是字符： Character
        Stack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 2、在 Java 中字符以 '' 标记，如果当前字符是左括号则放入栈中
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                // 3、如果是当前字符是右括号而栈中没有左括号则匹配失败
                if (stack.isEmpty()) {
                    return false;
                }

                // 4、依次判断三种括号匹配的情况即可
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

        // 5、栈中没有元素才算匹配成功
        return stack.isEmpty();
    }
}