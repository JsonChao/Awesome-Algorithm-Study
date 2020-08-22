package dynamic_problem;

/**
 * DP：优化状态转移
 */
public class Solution198_6 {

    public int rob(int[] nums) {

        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] memo = new int[n];
        memo[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            memo[i] = Math.max(memo[i + 1], nums[i] + (i + 2 < n ? memo[i - 2] : 0));
        }
        return memo[0];
    }

}