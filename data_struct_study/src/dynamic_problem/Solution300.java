package dynamic_problem;


import java.util.Arrays;

/**
 * 最长上升子序列:
 *      1、暴力解法：选择所有的子序列进行判断。O((2^n)*n)
 *      2、LIS(i) 表示[0...i]的范围内，选择数字nums[i]可以获得的最长上升子序列的长度。
 *      LIS(i) = max(1+LIS(j) if nums[i] > nums[j])
 *      LIS(i) 表示以第i个数字为结尾的最长上升子序列的长度。O(n^2）
 *      3、O(nlogn)
 *
 * 记忆化搜索
 * O(n^2)
 * O(n)
 */
public class Solution300 {

    private int[] memo;

    public int lengthOfLIS(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        memo = new int[nums.length];
        Arrays.fill(memo, -1);

        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res, getMaxLength(nums, i));
        }
        return res;
    }

    // 记录以 nums[index] 为结尾的最长上升子序列的长度
    private int getMaxLength(int[] nums, int index) {

        if (memo[index] != -1) {
            return memo[index];
        }

        int res = 1;
        for (int i = 0; i <= index - 1; i++) {
            if (nums[index] > nums[i]) {
                res = Math.max(res, 1 + getMaxLength(nums, i));
            }
        }

        return memo[index] = res;
    }

}