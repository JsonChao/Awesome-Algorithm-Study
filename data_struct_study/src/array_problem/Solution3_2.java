package array_problem;

/**
 * 3:
 *      1、字符集？只有字母？数字+字母？ASCLL？
 *      2、大小写是否敏感？
 *
 * 滑动窗口
 * 时间复杂度：O(len(s))
 * 空间复杂度：O(len(charset))
 */
public class Solution3_2 {

    public int lengthOfLongestSubstring(String s) {

        int freq[] = new int[256];

        int l = 0, r = -1;
        int res = 0;

        // 除了可以使用 while (l < s.length()) 之外，也可以使用 while (r + 1 < s.length())
        while (r + 1 < s.length()) {

            if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) {
                freq[s.charAt(++r)] ++;
            } else {
                freq[s.charAt(l++)] --;
            }

            res = Math.max(res, r - l + 1);
        }

        return res;
    }

}
