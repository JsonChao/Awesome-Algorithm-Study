package stack_problem;

import java.util.Stack;

/**
 * 题目描述：请写一个整数计算器，支持加减乘三种运算和括号。
 *
 * (2*(3-4))*5：不断递归，先计算最里面括号的值，并保存在栈中，然后再计算外面括号的。
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

    // (2*(3-4))*5：不断递归，先计算最里面括号的值，并保存在栈中，然后再计算外面括号的
    public int solve(String s) {
        // 1、创建一个栈保存各部分计算的和
        Stack<Integer> stack = new Stack<>();

        // 2、创建sum记录当前部分的和，number记录当前数字，sign记录运算符号，默认为+号
        int sum = 0, number = 0;
        char sign = '+';

        // 3、遍历字符数组
        char[] charArray = s.toCharArray();
        for (int i = 0, n = charArray.length; i < n; i++) {

            // 1）、如果当前字符是左括号时使用递归去掉最外面括号，并求这个括号里面表示式的值
            char c = charArray[i];
            if (c == '(') {
                // 1）、初始化变量j记录右括号的下一个位置与括号对变量counterPar
                int j = i + 1;
                int counterPar = 1;
                // 2）、当括号对数大于0时，如果当前位置元素为左括号则括号对数+1，否则-1，然后移动变量j的位置
                while (counterPar > 0) {
                    if (charArray[j] == '(') {
                        counterPar++;
                    }
                    if (charArray[j] == ')') {
                        counterPar--;
                    }
                    j++;
                }
                // 3）、递归计算括号里面表达式的值
                number = solve(s.substring(i + 1, j - 1));
                // 4）、更新当前遍历的下标i：直接定位到右括号位置j-1处
                i = j - 1;
            }

            // 2）、如果当前字符是数字，则使用number记录当前数字
            if (Character.isDigit(c)) {
                number = number * 10 + c - '0';
            }

            // 3）、如果当前字符是运算符或者到表达式末尾时，就去计算上一个运算符，
            // 并把计算结果push进栈，然后保存新的运算符
            if (!Character.isDigit(c) || i == n - 1) {
                // 1）、如果是+，不要计算，push进去
                if (sign == '+') {
                    stack.push(number);
                } else if (sign == '-') {
                    // 2）、如果是-，push进去负的当前数
                    stack.push(-1 * number);
                } else if (sign == '*') {
                    // 3）、如果是×、÷，pop出一个运算数和当前数作计算
                    stack.push(stack.pop() * number);
                } else if (sign == '/') {
                    stack.push(stack.pop() / number);
                }
                // 4）、重置number为0，sign为c
                number = 0;
                sign = c;
            }
        }

        // 4、最后把栈中的结果求和即可
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_2().solve("(2*(3-4))*5"));
    }

}
