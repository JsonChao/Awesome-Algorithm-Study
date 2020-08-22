package dynamic_problem;

public class Solution494 {

    public int findTargetSumWays(int[] nums, int S) {

        // 1、计算数组和
        int sum = countSum(nums);

        if (sum < S || (sum + S) % 2 == 1) {
            return 0;
        }

        int W = (sum + S) / 2;
        int[] dp = new int[W + 1];
        dp[0] = 1;
        for (int num:nums) {
            for (int i = W; i >= num; i--) {
                dp[i] = dp[i] +  dp[i - num];
            }
        }

        return dp[W];
    }

    private int countSum(int[] nums) {
        int res = 0;
        for (int i:nums) {
            res += i;
        }
        return res;
    }

}


