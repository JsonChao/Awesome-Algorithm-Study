package array_problem;

/**
 * l 每次可以向前飞跃，而不仅仅是 +1，以确保滑动窗口中不存在重复的字符串，
 * 但为了获得这个飞跃值，每次都要遍历一次当前滑动窗口的大小。
 *
 * 时间换空间：
 *      时间复杂度：O(r-l+1) * len(s))
 *      空间复杂度：O(1)
 */
public class Solution3_3 {

    // 时间换空间的解法：时间复杂度：O(len(s)) * O(r-l+1)，空间复杂度：O(1)
    public int lengthOfLongestSubstring(String s) {

        // 1、初始化滑动窗口：[0，0) 由于后面要使用 s.charAt(r)，所以 r 需要从 0 开始 & 记录窗口最大值的变量
        int l = 0, r = 0;
        int res = 0;

        // 2、注意：这里的r不需要+1了
        while (r < s.length()) {

            // 1)、当前滑动窗口右边界的元素是否在滑动窗口中存在重复元素，
            // 有则返回离左边界最近的重复元素下标
            int index = findInDuplicate(s, l, r);

            // 2）、如果存在重复元素，则更新滑动窗口左边界为重复元素下标+1，以减少可能的冗余遍历（核心）
            if (index != -1) {
                l = index + 1;
            }

            // 3）、更新滑动窗口最大值
            res = Math.max(res, r - l + 1);

            // 4）、滑动窗口右边界+1
            r++;
        }

        return res;
    }

    /**
     * 判读当前窗口新的右边界是否已现在滑动窗口中，有则返回离左边界最近的下标（即飞跃值）
     */
    private int findInDuplicate(String s, int l, int r) {
        for (int i = l; i < r; i++) {
            if (s.charAt(i) == s.charAt(r)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println((new Solution3_3()).lengthOfLongestSubstring( "abcabcbb" ));
    }
}
