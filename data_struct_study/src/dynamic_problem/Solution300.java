package dynamic_problem;

import java.util.Arrays;


/**
 * 题目描述：给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * 最长上升子序列:
 *      1、暴力解法：选择所有的子序列进行判断。O((2^n)*n)
 *      2、LIS(i) 表示[0...i]的范围内，选择数字nums[i]可以获得的最长上升子序列的长度。
 *      LIS(i) = max(1+LIS(j) if nums[i] > nums[j])
 *      LIS(i) 表示以第i个数字为结尾的最长上升子序列的长度。O(n^2）
 *      3、O(nlogn)
 *
 * 记忆化搜索
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n)
 */
public class Solution300 {

    private int[] memo;

    public int lengthOfLIS(int[] nums) {
        // 1、异常处理：如果数组长度等于0，则返回0
        if (nums.length == 0) {
            return 0;
        }

        // 2、创建记忆数组并全部填充为-1
        memo = new int[nums.length];
        Arrays.fill(memo, -1);

        // 3、创建返回变量res，然后在for循环中不断计算当前位置的LIS
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res, getMaxLength(nums, i));
        }
        return res;
    }

    // 4、记录以 nums[index] 为结尾的最长上升子序列的长度
    private int getMaxLength(int[] nums, int index) {
        // 1）、如果记忆数组中的当前位置不等于-1，则返回记忆值
        if (memo[index] != -1) {
            return memo[index];
        }

        // 2）、创建返回变量res，然后遍历到index-1处的位置：
        // 如果index处的值大于前面位置i的值，则重新计算当前的res为
        // res 与 1+i处位置的LIS 中的最大值
        int res = 1;
        for (int i = 0; i <= index - 1; i++) {
            if (nums[index] > nums[i]) {
                res = Math.max(res, 1 + getMaxLength(nums, i));
            }
        }

        // 3）、保存index处的LIS并返回
        return memo[index] = res;
    }

}