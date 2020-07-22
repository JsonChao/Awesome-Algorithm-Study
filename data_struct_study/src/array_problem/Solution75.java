package array_problem;
//https://leetcode-cn.com/problems/sort-colors/:颜色分类

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 *
 * 示例:
 *
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 *
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 *  1、计数排序：分别统计0、1、2元素的个数。
 *  2、三路快排。
 *
 * 计数排序思路，对整个数组遍历了两遍
 * O(n)
 * O(K)，k为元素的取值范围
 */
public class Solution75 {

    public void sortColors(int[] nums) {

        // 使用一个数组统计每个值出现的频率
        int[] count = {0, 0, 0};
        for (int i = 0; i < nums.length; i++) {
            assert nums[i] >= 0 && nums[i] <= 2;
            count[nums[i]] ++;
        }

        // 拼装成 0、1、2 排序的新数组
        int k = 0;
        for (int i = 0; i < count[0]; i++) {
            nums[k++] = 0;
        }
        for (int i = 0; i < count[1]; i++) {
            nums[k++] = 1;
        }
        for (int i = 0; i < count[2]; i++) {
            nums[k++] = 2;
        }
    }
}
