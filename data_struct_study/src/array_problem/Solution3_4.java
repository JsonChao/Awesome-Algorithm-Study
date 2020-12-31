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

    // 滑动窗口 + 优化：使用记录上一次滑动窗口右边界下标的数组，以实现找到重复元素时左边界的飞跃
    // 时间复杂度：O(len(s)) 空间复杂度：O(len(charset))
    public int lengthOfLongestSubstring(String s) {

        // 1、初始化记录上一次的滑动窗口右边界下标，以实现找到重复元素时左边界的飞跃
        int[] last = new int[256];
        Arrays.fill(last, -1);

        // 2、初始化滑动窗口：[0, -1] & 记录窗口最大值的变量
        int l = 0, r = -1;
        int res = 0;

        // 3、维护滑动窗口
        while (r + 1 < s.length()) {
            // 1）、右边界+1
            r++;

            // 2）、如果当前右边界的元素已经出现过，则更新左边界为 左边界 与 右边界元素上次出现的下标+1 两者中的最大值
            if (last[s.charAt(r)] != -1) {
                l = Math.max(l, last[s.charAt(r)] + 1);
            }

            // 3）、维护滑动窗口的大小
            res = Math.max(res, r - l + 1);

            // 4）、保存右边界的下标
            last[s.charAt(r)] = r;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println((new Solution3_4()).lengthOfLongestSubstring( "abcabcbb" ));
    }

}
