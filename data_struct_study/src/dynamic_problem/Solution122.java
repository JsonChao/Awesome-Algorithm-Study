package dynamic_problem;


/**
 * 题目描述：给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 如果 k 为正无穷，那么就可以认为 k 和 k - 1 是一样的:
 * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
 * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
 *             = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])
 *
 * 我们发现数组中的 k 已经不会改变了，也就是说不需要记录 k 这个状态了：
 * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
 * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
 *
 * 第三题，k = +infinity with cooldown
 *
 * 每次 sell 之后要等一天才能继续交易。只要把这个特点融入上一题的状态转移方程即可：
 * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
 * dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
 * 解释：第 i 天选择 buy 的时候，要从 i-2 的状态转移，而不是 i-1 。
 *
 * 第四题，k = +infinity with fee
 *
 * 每次交易要支付手续费，只要把手续费从利润中减去即可。改写方程：
 *
 * 第五题，k = 2
 *
 * 这里 k 取值范围比较小，所以可以不用 for 循环，直接把 k = 1 和 2 的情况全部列举出来也可以：
 * dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][2][1] + prices[i])
 * dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])
 * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
 * dp[i][1][1] = max(dp[i-1][1][1], -prices[i])
 *
 * 第六题，k = any integer
 *
 * 一次交易由买入和卖出构成，至少需要两天。所以说有效的限制 k 应该不超过 n/2，如果超过，就没有约束作用了，相当于 k = +infinity。
 *
 * 关键就在于列举出所有可能的「状态」，然后想想怎么穷举更新这些「状态」。一般用一个多维 dp 数组储存这些状态，从 base case 开始向后推进，推进到最后的状态，就是我们想要的答案。想想这个过程，你是不是有点理解「动态规划」这个名词的意义了呢？
 *
 */
public class Solution122 {

    // 时间复杂度：O(n), 空间复杂度：O(1)
    public int maxProfit_k_inf(int[] prices) {
        // 1、初始化第i天未持有为0，第i天持有为负无穷
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;

        // 2、遍历价格数组
        for (int price : prices) {
            // 1）、初始化temp变量记录前一天未持有的价格
            int temp = dp_i_0;

            // 2）、当天未持有等于 前一天未持有和前天持有+当天价格 中的最大值
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);

            // 3）、当天持有等于 前一天持有和temp-当天价格 中的最大值
            dp_i_1 = Math.max(dp_i_1, temp - price);
        }

        // 3、返回最后一天未持有的价格
        return dp_i_0;
    }

    int maxProfit_with_cool(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0; // 代表 dp[i-2][0]
        for (int price : prices) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - price);
            dp_pre_0 = temp;
        }
        return dp_i_0;
    }

    int maxProfit_with_fee(int[] prices, int fee) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int price : prices) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);
            dp_i_1 = Math.max(dp_i_1, temp - price - fee);
        }
        return dp_i_0;
    }

    int maxProfit_k_2(int[] prices) {
        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
        for (int price : prices) {
            dp_i20 = Math.max(dp_i20, dp_i21 + price);
            dp_i21 = Math.max(dp_i21, dp_i10 - price);
            dp_i10 = Math.max(dp_i10, dp_i11 + price);
            dp_i11 = Math.max(dp_i11, -price);
        }
        return dp_i20;
    }

    int maxProfit_k_any(int max_k, int[] prices) {
        int n = prices.length;
        if (max_k > n / 2)
            return maxProfit_k_inf(prices);

        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++)
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) { /* 处理 base case */ }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
            }
        return dp[n - 1][max_k][0];
    }

}
