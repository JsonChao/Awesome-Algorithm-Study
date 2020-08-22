package dynamic_problem;

import java.util.Arrays;

/**
 * DP
 * O(n ^ 2)
 * O(n)
 */
public class Solution300_2 {

    public int lengthOfLIS(int[] nums) {

        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    memo[i] = Math.max(memo[i], 1 + memo[j]);
                }
            }
        }

        int res = memo[0];
        for (int i = 1; i < n; i++) {
            res = Math.max(res, memo[i]);
        }

        return res;
    }

}