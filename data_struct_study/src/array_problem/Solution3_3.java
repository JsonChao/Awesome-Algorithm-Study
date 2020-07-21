package array_problem;

/**
 * l 每次可以向前飞跃，而不仅仅是 +1，以确保滑动窗口中不存在重复的字符串，
 * 但为了获得这个飞跃值，选要每次都遍历一次当前滑动窗口的大小。
 *
 * 时间复杂度：O(len(r-l+1) * len(len(s)))
 * 空间复杂度：O(1)
 */
public class Solution3_3 {

    public int lengthOfLongestSubstring(String s) {

        // 后面要使用 s.charAt(r)，所以 r 需要从 0 开始
        int l = 0, r = 0;
        int res = 0;

        // 注意这里是 r < s.length()
        while (r < s.length()) {

            int index = findInDuplicate(s, l, r);

            if (index != - 1) {
                l = index + 1;
            }

            res = Math.max(res, r - l + 1);

            r++;
        }

        return res;
    }

    /**
     * 找到当前窗口 r 是否重复出现在滑动窗口中，有则返回其下标
     * @param s
     * @param l
     * @param r
     * @return
     */
    private int findInDuplicate(String s, int l, int r) {
        for (int i = l; i < r; i++) {
            if (s.charAt(i) == s.charAt(r)) {
                return i;
            }
        }
        return -1;
    }
}
