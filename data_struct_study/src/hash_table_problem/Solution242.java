package hash_table_problem;


/**
 * 242：
 *      1、空集
 *      2、字符集
 *
 * 可以用 HashMap 来映射字符与出现次数，然后比较两个字符串出现的字符数量是否相同。
 *
 * 由于本题的字符串只包含 26 个小写字符，因此可以使用长度为 26 的整型数组
 * 对字符串出现的字符进行统计，不再使用 HashMap。
 */
public class Solution242 {

    public boolean isAnagram(String s, String t) {
        int[] countForNum = new int[26];
        for(char c:s.toCharArray()) {
            countForNum[c - 'a']++;
        }

        for (char c:t.toCharArray()) {
            countForNum[c - 'a']--;
        }

        for (int count:countForNum) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

}
