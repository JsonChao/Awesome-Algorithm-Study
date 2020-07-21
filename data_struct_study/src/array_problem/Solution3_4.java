package array_problem;

import java.util.Arrays;

/**
 * 3:
 *      1、字符集？只有字母？数字+字母？ASCLL？
 *      2、大小写是否敏感？
 *
 * 滑动窗口
 * 时间复杂度：O(len(s))
 * 空间复杂度：O(len(charset))
 */
public class Solution3_4 {

    public int lengthOfLongestSubstring(String s) {

        // 用于记录字符 s.charAt(r) 上一次出现的位置，以实现
        // 一次遍历 s 就移动 l 到 上一次出现位置 + 1 的位置
        int[] last = new int[256];
        Arrays.fill(last, -1);

        int l = 0, r = -1;
        int res = 0;

        while (r + 1 < s.length()) {

            r++;
            if (last[s.charAt(r)] != -1) {
                l = Math.max(l, last[s.charAt(r)] + 1);
            }

            res = Math.max(res, r - l + 1);

            last[s.charAt(r)] = r;
        }

        return res;
    }

}
