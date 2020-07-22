package array_problem;
//https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/:两数之和 II - 输入有序数组

/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 *
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 *
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 *
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 * 1、如果没有解？保证有解。
 * 2、如果有多个解？返回任意解。
 * 3、双层遍历 O(n^2)。（没有利用有序）
 * 4、循环 + 二分查找 O(nlogn)：在有序数组中寻找 target - nums[i]。
 * 5、对撞指针：使用两个索引，两个索引在中间的位置靠近。
 *
 * O(n ^ 2)
 * O(1)
 */
public class Solution167 {

    public int[] twoSum(int[] numbers, int target) {

        if (numbers.length < 2) {
            throw new IllegalArgumentException("length of numbers is illegal!");
        }

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    int[] res = {i + 1, j + 1};
                    return res;
                }
            }
        }

        throw new IllegalArgumentException("no target!");
    }
}
