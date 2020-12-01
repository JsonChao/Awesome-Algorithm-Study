package stack_problem;

import java.util.Stack;

/**
 * 1.用栈保存各部分计算的和
 * 2.遍历表达式
 * 3.遇到数字时继续遍历求这个完整的数字的值
 * 4.遇到左括号时递归求这个括号里面的表达式的值
 * 5.遇到运算符时或者到表达式末尾时，就去计算上一个运算符并把计算结果push进栈，然后保存新的运算符
 *      如果是+，不要计算，push进去
 *      如果是-，push进去负的当前数
 *      如果是×、÷，pop出一个运算数和当前数作计算
 * 6.最后把栈中的结果求和即可
 */
public class Solution_2 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 返回表达式的值
     * @param s string字符串 待计算的表达式
     * @return int整型
     */
    public int solve(String s) {
        Stack<Integer> stack = new Stack<>();
        int sum = 0, number = 0;
        char sign = '+';
        char[] charArray = s.toCharArray();
        for (int i = 0, n = charArray.length; i < n; i++) {
            char c = charArray[i];
            if (c == '(') {
                int j = i + 1;
                int counterPar = 1;
                while (counterPar > 0) {
                    if (charArray[j] == '(') {
                        counterPar++;
                    }
                    if (charArray[j] == ')') {
                        counterPar--;
                    }
                    j++;
                }
                number = solve(s.substring(i + 1, j - 1));
                i = j - 1;
            }
            if (Character.isDigit(c)) {
                number = number * 10 + c - '0';
            }
            if (!Character.isDigit(c) || i == n - 1) {
                if (sign == '+') {
                    stack.push(number);
                } else if (sign == '-') {
                    stack.push(-1 * number);
                } else if (sign == '*') {
                    stack.push(stack.pop() * number);
                } else if (sign == '/') {
                    stack.push(stack.pop() / number);
                }
                number = 0;
                sign = c;
            }
        }
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }
}
