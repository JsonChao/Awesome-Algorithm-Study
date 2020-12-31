package array_problem;


/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 说明: 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *  
 * 示例:
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * 输出: [1,2,2,3,5,6]
 */
public class Solution88 {

    // 双指针：为防止元素被覆盖，双指针需从后往前遍历
    // 时间复杂度：O(m + n)，空间复杂度：O（1）
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        // 1、初始化数组1、2的指针：指向数组有效尾部 & 合并数组指针指向合并数组有效尾部
        int index1 = m - 1, index2 = n - 1;
        int indexMerge = m + n - 1;

        // 2、当数组1或2的指针有效，即大于等于0时
        while (index1 >= 0 || index2 >= 0) {
            if (index1 < 0) {
                // 3）、如果数组1处的指针小于0，则更新数组1合并指针位置的值为数组2指针处的值，
                // 并将数组2的指针和合并指针都-1
                nums1[indexMerge--] = nums2[index2--];
            } else if (index2 < 0) {
                // 4）、否则，更新数组1合并指针位置的值为数组1处指针的值，并将数组1的指针和合并指针都-1
                nums1[indexMerge--] = nums1[index1--];
            } else if (nums1[index1] > nums2[index2]) {
                // 1）、如果数组1的值大于数组2的值，则更新数组1合并指针位置的值为数组1指针处的值，
                // 并将数组1的指针和合并指针都-1
                nums1[indexMerge--] = nums1[index1--];
            } else {
                // 2）、否则，更新数组1合并指针位置的值为数组2指针处的值，并将数组2的指针和合并指针都-1
                nums1[indexMerge--] = nums2[index2--];
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] arr2 = new int[]{2, 5, 6};
        new Solution88().merge(arr1, 3, arr2, arr2.length);
    }

}
