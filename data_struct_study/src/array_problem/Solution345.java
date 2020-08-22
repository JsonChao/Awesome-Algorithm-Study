package array_problem;
//https://leetcode-cn.com/problems/reverse-vowels-of-a-string/:反转字符串中的元音字母

import java.util.Arrays;
import java.util.HashSet;

/**
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 *
 * 示例 1:
 *
 * 输入: "hello"
 * 输出: "holle"
 * 示例 2:
 *
 * 输入: "leetcode"
 * 输出: "leotcede"
 * 说明:
 * 元音字母不包含字母"y"。
 *
 * （元音aeiou）
 */
public class Solution345 {

    private final static HashSet<Character> vowels = new HashSet<>(Arrays.asList(
            'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'
    ));

    public String reverseVowels(String s) {

        if (s == null) {
            return null;
        }

        int i = 0, j = s.length() - 1;
        char[] res = new char[s.length()];
        while (i <= j) {
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            if (!vowels.contains(ci)) {
                res[i++] = ci;
            } else if (!vowels.contains(cj)) {
                res[j--] = cj;
            } else {
                res[i++] = cj;
                res[j--] = ci;
            }
        }
        return new String(res);
    }

}
