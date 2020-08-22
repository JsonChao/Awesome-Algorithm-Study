package dynamic_problem;

/**
 * 1、穷举框架-状态与选择：
 * for 状态1 in 状态1的所有取值：
 *     for 状态2 in 状态2的所有取值：
 *         for ...
 *             dp[状态1][状态2][...] = 择优(选择1，选择2...)
 *  这里每天都有三种「选择」：买入、卖出、无操作。
 *  「状态」有三个，第一个是天数，第二个是允许交易的最大次数，第三个是当前的持有状态（0、1）
 * 2、状态转移框架与 base case：
 * 状态转移方程：
 * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
 * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
 *
 * base case：
 * dp[-1][k][0] = dp[i][0][0] = 0
 * dp[-1][k][1] = dp[i][0][1] = -infinity
 */
public class Solution121 {

    // k == 1
    int maxProfit_k_1(int[] prices) {
        int n = prices.length;
        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // dp[i][1] = max(dp[i-1][1], -prices[i])
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }

}
