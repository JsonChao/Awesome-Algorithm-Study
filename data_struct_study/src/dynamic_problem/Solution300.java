package dynamic_problem;


/**
 * 最长上升子序列:
 *      1、暴力解法：选择所有的子序列进行判断。O((2^n)*n)
 *      2、LIS(i) 表示[0...i]的范围内，选择数字nums[i]可以获得的最长上升子序列的长度。
 *      LIS(i) = max(1+LIS(j) if nums[i] > nums[j])
 *      LIS(i) 表示以第i个数字为结尾的最长上升子序列的长度。O(n^2）
 *      3、O(nlogn)
 */
public class Solution300 {
}
