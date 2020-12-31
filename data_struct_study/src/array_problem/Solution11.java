package array_problem;


/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，
 * 容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 */
public class Solution11 {

    public int maxArea(int[] height) {

        // 1、初始化记录窗口左右边界的位置 & 最大面积
        int l = 0, r = height.length - 1;
        int max = 0;

        // 2、当左边界小于右边界时
        while (l < r) {
            // 1）、统计当前窗口的面积大小：左右边界中较低的高 * 窗口大小
            int cur = Math.min(height[l], height[r]) * (r - l);

            // 2）、维护窗口最大面积
            max = Math.max(max, cur);

            // 3）、如果当前窗口左边界的高度小于右边界的高度，则左边界往里移动1步，否则右边界往里移动一步
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(new Solution11().maxArea(arr));
    }
}
