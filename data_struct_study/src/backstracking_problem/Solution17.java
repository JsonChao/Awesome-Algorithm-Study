package backstracking_problem;


import java.util.ArrayList;
import java.util.List;

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
 * O(2^len(s))
 * O(len(s)
 */
public class Solution17 {

    private String letterMap[] = {
            " ",    //0
            "",     //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };

    private ArrayList<String> res;

    public List<String> letterCombinations(String digits) {

        res = new ArrayList<>();

        if (digits == null || digits.equals("")) {
            return res;
        }

        findCombinations(digits, 0, "");
        return res;
    }

    private void findCombinations(String digits, int index, String s) {

        if (digits.length() == index) {
            res.add(s);
            return;
        }

        Character c = digits.charAt(index);
        assert c.compareTo('0') >= 0 &&
                c.compareTo('9') <= 0 &&
                c.compareTo('1') != 0;
        String letter = letterMap[c - '0'];
        for (int i = 0; i < letter.length(); i++) {
            findCombinations(digits, index + 1, s + letter.charAt(i));
        }
    }

}
