package array_problem;

import java.util.Arrays;

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
