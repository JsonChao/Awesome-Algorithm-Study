package array_problem;

import java.util.Arrays;

/**
 * 除自身以外数组的乘积: 对于每一个元素而言，左边的所有元素
 * 乘以右边的所有元素就能得到答案。注意，两次循环不能合并！
 */
public class Solution238 {

    public int[] productExceptSelf(int[] nums) {

        int n = nums.length;
        int[] product = new int[n];
        Arrays.fill(product, 1);

        int left = 1;
        for (int i = 1; i < n; i++) {
            left *= nums[i - 1];
            product[i] *= left;
        }

        int right = 1;
        for (int i = n - 2; i >= 0; i--) {
            right *= nums[i + 1];
            product[i] *= right;
        }

        return product;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(new Solution238().productExceptSelf(nums));
    }

}
