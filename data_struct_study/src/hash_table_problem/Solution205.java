package hash_table_problem;


/**
 * 205：
 *      1、字符集
 *      2、空串
 *      3、一个字符是否可以映射到自己
 *
 * 记录一个字符上次出现的位置，如果两个字符串中的字符上次出现的位置一样，那么就属于同构。
 */
public class Solution205 {

    public boolean isIsomorphic(String s, String t) {
        int[] preIndexOfS = new int[256];
        int[] preIndexOfT = new int[256];
        for (int i = 0; i < s.length(); i++) {
            char cs = s.charAt(i), ct = t.charAt(i);
            if (preIndexOfS[cs] != preIndexOfT[ct]) {
                return false;
            }
            preIndexOfS[cs] = i + 1;
            preIndexOfT[ct] = i + 1;
        }

        return true;
    }

}
