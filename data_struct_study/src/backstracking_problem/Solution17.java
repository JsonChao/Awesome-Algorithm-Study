package backstracking_problem;


/**
 * 递归与回溯
 * 树形问题 17：
 *      1、字符串的合法性
 *      2、空字符串（null）
 *      3、多个解的顺序（无）
 *      4、digits 是数字字符串，s(digits) 是digits所能代表的字母字符串，
 *      s(digital[0...n-1])
 *          = letter(digital[0]) + letter(digital[1...n-1])
 *          = letter(digital[0]) + letter(digital[1]) + letter(digital[2...n-1])
 *          = ...
 *      5、递归调用的一个重要特征：要返回——回溯，它是暴力解法的一个主要实现手段。
 *      6、3^n = O(2^n)
 */
public class Solution17 {
}
