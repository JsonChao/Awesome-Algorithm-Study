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

    // 时间复杂度：O(len(s))，空间复杂度：O(charset)
    public int lengthOfLongestSubstring(String s) {

        // 1、初始化一个存储ascill字符集的数组用来记录元素的出现频次
        int[] freq = new int[256];

        // 2、初始化滑动窗口：[0, -1] & 记录窗口最大值的变量
        int l = 0, r = -1;
        int res = 0;

        // 3、维护滑动窗口：左边界小于数组长度 || 右边界+1小于数组长度
//        while (l < s.length())
        while (r + 1 < s.length()) {
            // 1)、如果右边界+1小于数组长度 & 右边界+1的出现频次为0，
            // 则将右边界+1，并将其出现频次+1
            if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) {
                freq[s.charAt(++r)]++;
            } else {
                // 2）、否则，将左边界的出现频次-1，并将左边界+1
                freq[s.charAt(l++)]--;
            }

            // 3）、更新滑动窗口的最大值
            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println((new Solution3_2()).lengthOfLongestSubstring( "abcabcbb" ));
    }

}
