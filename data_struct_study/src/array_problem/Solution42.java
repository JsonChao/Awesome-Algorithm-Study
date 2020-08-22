package array_problem;

/**
 * 每一个柱子的高度方向可以接的雨水的数量 =
 * min(从当前柱子向左看的最高柱子高度, 从当前柱子向右看的最高柱子高度) - 当前柱子高度
 * 1）两个数组left、right分别保存：从左往右遍历时下标i最高柱子高度，和从右往左遍历时下标i最高柱子高度。
 * 2）再遍历一遍每个位置，只有当height[i]的高度，比left[i]和right[i]都要小的时候才能接住雨水（否则总有一边会漏，接不到雨水）
 * 3）将所有可以接到雨水的柱子的数量加起来
 */
public class Solution42 {

    public int trap(int[] height) {
        int length = height.length;
        int[] left = new int[length];//保存从左往右遍历时，每一个下标位置当前的最高柱子高度
        int[] right = new int[length];//保存从右往左遍历时，每一个下标位置当前的最高柱子高度
        int leftMax = 0;
        int rightMax = 0;
        int sum = 0;

        //计算left和right数组
        for (int i = 0; i < length; i++) {
            if (height[i] > leftMax) {
                leftMax = height[i];
            }
            left[i] = leftMax;
            if (height[length-1-i] > rightMax) {
                rightMax = height[length-1-i];
            }
            right[length-1-i] = rightMax;
        }

        //遍历，只有当前柱子往左看、往右看的最高柱子都比当前柱子高，才能接住雨水
        for (int j = 0; j < length; j++) {
            if (height[j] < left[j] && height[j] < right[j]) {
                sum = sum + Math.min(left[j], right[j]) - height[j];
            }
        }
        return sum;
    }

}
